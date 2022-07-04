package org.yagamipaul.alife.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import org.yagamipaul.alife.MyGdxGame;
import org.yagamipaul.alife.entities.BaseEntity;
import org.yagamipaul.alife.entities.Organism;
import org.yagamipaul.alife.entities.Renderable;
import org.yagamipaul.alife.entities.components.Sensor;
import org.yagamipaul.alife.manager.Simulator;

public class GameScreen implements Screen {

  final MyGdxGame game;

  OrthographicCamera camera;

  private Simulator simulator;

  private SecureRandom secureRandom;
  Matrix4 uiMatrix;

  public GameScreen(MyGdxGame game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1280, 720);

    uiMatrix = camera.combined.cpy();
    uiMatrix.setToOrtho2D(0, 0, 1280, 720);

    simulator = new Simulator();

    this.secureRandom = new SecureRandom();

    for (int i = 0; i < 4; i++) {
      Organism newOrganism =
          new Organism(
              new Vector2(secureRandom.nextInt(700), secureRandom.nextInt(500)), Vector2.Zero);
      simulator.addEntity(newOrganism);
      newOrganism.addObserver(simulator);
    }
  }

  @Override
  public void show() {}

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
    sr.begin(ShapeRenderer.ShapeType.Filled);

    for (BaseEntity entity : simulator.getEntities()) {
      for (Sensor s : entity.getSensors()) {
        ((Renderable) s).render(sr);
      }
    }
    sr.end();

    SpriteBatch batch = game.getBatch();
    BitmapFont font = game.font;
    int fps = (int) Gdx.graphics.getFramesPerSecond();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    for (BaseEntity entity : simulator.getEntities()) {
      entity.update();
      if (entity.isAlive()) {
        entity.render(batch);

        font.draw(
            batch,
            "Life: " + entity.getHealth(),
            entity.getPosition().x,
            entity.getPosition().y + 100);

        float angle =
            MathUtils.radiansToDegrees
                * MathUtils.atan2(entity.getDirection().y, entity.getDirection().x);

        font.draw(batch, "Angle: " + angle, entity.getPosition().x, entity.getPosition().y + 80);
      }
    }

    batch.end();

    batch.begin();
    batch.setProjectionMatrix(uiMatrix);
    font.draw(batch, "FPS: " + fps, 3, Gdx.graphics.getHeight() - 3);

    font.draw(
        batch, "Entities: " + simulator.getEntities().size(), 3, Gdx.graphics.getHeight() - 30);
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
}
