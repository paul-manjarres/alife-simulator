package org.yagamipaul.alife.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.security.SecureRandom;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yagamipaul.alife.MainGameApplication;
import org.yagamipaul.alife.entities.*;
import org.yagamipaul.alife.entities.components.Sensor;
import org.yagamipaul.alife.manager.Simulator;
import org.yagamipaul.alife.utils.VectorUtils;

public class GameScreen implements Screen {

    private static final Logger log = LoggerFactory.getLogger(GameScreen.class);

    private final MainGameApplication game;
    private final OrthographicCamera camera;
    private final Simulator simulator;
    private final SecureRandom secureRandom;
    private static final boolean DEBUG_MODE = true;
    private boolean showLabels = true;

    Matrix4 uiMatrix;

    public GameScreen(MainGameApplication game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera.translate(-camera.viewportWidth / 2, -camera.viewportHeight / 2);
        uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, 1280, 720);

        simulator = new Simulator();
        this.secureRandom = new SecureRandom();

        for (int i = 0; i < 1; i++) {
            Organism newOrganism = null;
            if (secureRandom.nextDouble() <= 0.5d) {
                newOrganism = new Carnivorous(
                        new Vector2(secureRandom.nextInt(700) - 350, secureRandom.nextInt(500) - 250), Vector2.Zero);
            } else {
                newOrganism = new Herviborous(
                        new Vector2(secureRandom.nextInt(700) - 350, secureRandom.nextInt(500) - 250), Vector2.Zero);
            }
            simulator.addEntity(newOrganism);
            newOrganism.addObserver(simulator);
        }

        IntStream.range(0, 5).forEach(i -> {
            Food f = new Food(new Vector2(secureRandom.nextInt(700), secureRandom.nextInt(500)), Vector2.Zero);
            simulator.addEntity(f);
        });
    }

    @Override
    public void show() {}

    boolean isTouched = false;

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.

        camera.update();
        // camera.zoom += 0.05f * Gdx.graphics.getDeltaTime();
        ShapeRenderer sr = game.getShapeRenderer();
        sr.setColor(Color.WHITE);
        sr.setProjectionMatrix(camera.combined);

        if (DEBUG_MODE) {
            drawDebugLine(Vector2.Zero, new Vector2(500, 500), camera.combined, sr);
        }

        if (!Gdx.input.isTouched() && isTouched) {
            isTouched = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            Organism newOrganism = null;
            if (secureRandom.nextDouble() <= 0.5d) {
                newOrganism = new Carnivorous(
                        new Vector2(secureRandom.nextInt(700) - 350, secureRandom.nextInt(500) - 250), Vector2.Zero);
            } else {
                newOrganism = new Herviborous(
                        new Vector2(secureRandom.nextInt(700) - 350, secureRandom.nextInt(500) - 250), Vector2.Zero);
            }

            simulator.addEntity(newOrganism);
            newOrganism.addObserver(simulator);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            this.showLabels = !this.showLabels;
        }

        if (Gdx.input.isTouched() && !isTouched) {
            isTouched = true;
            Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            simulator.addEntity(new Food(VectorUtils.to2D(pos), Vector2.Zero));
            // log.debug("Position x:{} - y:{}- Delta: {}", pos.x, pos.y, Gdx.graphics.getDeltaTime());
        }

        sr.begin(ShapeRenderer.ShapeType.Filled);

        // Check collisions
        //        for (int i = 0; i < simulator.getEntities().size(); i++) {
        //            BaseEntity e1 = simulator.getEntities().get(i);
        //            for (int j = i + 1; j < simulator.getEntities().size(); j++) {
        //                BaseEntity e2 = simulator.getEntities().get(j);
        //                if (e1.getRect().overlaps(e2.getRect())) {
        //                    if (e1 instanceof Organism o) {
        //                        o.getSensors().forEach(s -> s.trigger(e2));
        //                    }
        //                    if (e2 instanceof Organism o) {
        //                        o.getSensors().forEach(s -> s.trigger(e1));
        //                    }
        //                }
        //            }
        //        }
        int numberOfEntities = simulator.getEntities().size();
        for (int i = 0; i < numberOfEntities; i++) {
            BaseEntity e1 = simulator.getEntities().get(i);
            if (e1 instanceof Organism o) {
                o.getSensors().forEach((type, sensor) -> sensor.check(simulator.getEntities()));
            }
        }

        // Draw sensors
        if (DEBUG_MODE) {
            for (BaseEntity entity : simulator.getEntities()) {
                if (entity instanceof Organism o) {
                    for (Sensor s : o.getSensors().values()) {
                        ((Renderable) s).render(sr);
                    }
                }
                entity.renderRect(sr);
            }
        }
        sr.end();

        SpriteBatch batch = game.getBatch();
        BitmapFont font = game.font;

        if (showLabels) {
            for (BaseEntity entity : simulator.getEntities()) {
                entity.getInfo().renderShape(sr, font, camera);
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (BaseEntity entity : simulator.getEntities()) {
            entity.update(delta);
            if (showLabels) {
                entity.getInfo().renderText(batch, font);
            }

            if (entity instanceof NonLiving) {
                entity.render(batch);
            } else if (entity instanceof Organism org) {
                if (org.isAlive()) {
                    entity.render(batch);
                }
            }
        }

        batch.setProjectionMatrix(uiMatrix);
        int fps = Gdx.graphics.getFramesPerSecond();
        font.draw(batch, "FPS: " + fps, 3, Gdx.graphics.getHeight() - 3f);
        font.draw(batch, "Entities: " + simulator.getEntities().size(), 3, Gdx.graphics.getHeight() - 30f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    public static void drawDebugLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix, ShapeRenderer renderer) {
        Gdx.gl.glLineWidth(2);
        renderer.setProjectionMatrix(projectionMatrix);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        renderer.line(start, end);
        renderer.end();
        Gdx.gl.glLineWidth(1);
    }
}
