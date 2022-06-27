package org.yagamipaul.alife.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import org.yagamipaul.alife.MyGdxGame;
import org.yagamipaul.alife.entities.Ant;
import org.yagamipaul.alife.entities.BaseEntity;
import org.yagamipaul.alife.manager.Simulator;

public class GameScreen implements Screen {

  final MyGdxGame game;

  OrthographicCamera camera;

  private Simulator simulator;

  private SecureRandom secureRandom;

  public GameScreen(MyGdxGame game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1280, 720);

    simulator = new Simulator();

    this.secureRandom = new SecureRandom();

    for (int i = 0; i < 20; i++) {
      Ant newAnt =
          new Ant(new Vector2(secureRandom.nextInt(700), secureRandom.nextInt(500)), Vector2.Zero);
      simulator.addEntity(newAnt);
      newAnt.addObserver(simulator);
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

    SpriteBatch batch = game.getBatch();
    BitmapFont font = game.font;
    int fps = (int) Gdx.graphics.getFramesPerSecond();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    font.draw(batch, "FPS: " + fps, 3, Gdx.graphics.getHeight() - 3);

    game.font.draw(
        batch, "Entities: " + simulator.getEntities().size(), 3, Gdx.graphics.getHeight() - 30);

    for (BaseEntity entity : simulator.getEntities()) {
      entity.update();
      if (entity.isAlive()) {
        entity.render(batch);

        font.draw(
            batch,
            "Life: " + entity.getHealth(),
            entity.getPosition().x,
            entity.getPosition().y + 100);
      }
    }

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
