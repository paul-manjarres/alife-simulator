package org.yagamipaul.alife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.yagamipaul.alife.screen.MainMenuScreen;

public class MyGdxGame extends Game {
  private SpriteBatch batch;
  public BitmapFont font;

  Texture img;

  @Override
  public void create() {
    batch = new SpriteBatch();
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
}
