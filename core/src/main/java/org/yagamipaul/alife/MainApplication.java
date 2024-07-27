package org.yagamipaul.alife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.yagamipaul.alife.screen.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainApplication extends Game {
  private SpriteBatch batch;

  private ShapeRenderer shapeRenderer;
  public BitmapFont font;

  Texture img;

  @Override
  public void create() {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    font = new BitmapFont();
    this.setScreen(new MainMenuScreen(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
    font.dispose();
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public ShapeRenderer getShapeRenderer() {
    return shapeRenderer;
  }
}
