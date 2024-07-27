package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Renderable {

  void render(SpriteBatch sb);

  void render(ShapeRenderer sr);
}
