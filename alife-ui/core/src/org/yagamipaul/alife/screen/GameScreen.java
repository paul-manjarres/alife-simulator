package org.yagamipaul.alife.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import org.yagamipaul.alife.MyGdxGame;
import org.yagamipaul.alife.entities.Ant;
import org.yagamipaul.alife.entities.BaseEntity;
import org.yagamipaul.alife.manager.EntityManager;

public class GameScreen implements Screen {

  final MyGdxGame game;

  OrthographicCamera camera;

  private EntityManager entityManager;

  public GameScreen(MyGdxGame game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1280, 720);

    entityManager = new EntityManager();

    for (int i = 0; i < 7; i++) {
      Ant newAnt = new Ant(new Vector2(100 + i * 100, 20 + i * 100), Vector2.Zero);
      entityManager.addEntity(newAnt);
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

    // tell the SpriteBatch to render in the
    // coordinate system specified by the camera.
    game.getBatch().setProjectionMatrix(camera.combined);

    game.getBatch().begin();
    game.font.draw(
        game.getBatch(),
        (int) Gdx.graphics.getFramesPerSecond() + " fps",
        3,
        Gdx.graphics.getHeight() - 3);
    game.font.draw(game.getBatch(), "Entities: " + entityManager.getEntities().size, 0, 480);

    // game.getBatch().draw(TextureManager.ANT_TEXTURE,
    // 0,0,TextureManager.ANT_TEXTURE.getWidth(),TextureManager.ANT_TEXTURE.getHeight());

    for (BaseEntity entity : entityManager.getEntities()) {
      entity.update();
      if (entity.isAlive()) {
        entity.render(game.getBatch());
        game.font.draw(
            game.getBatch(),
            "Life: " + entity.getHealth(),
            entity.getPosition().x,
            entity.getPosition().y + 100);
      }
    }

    game.getBatch().end();
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
