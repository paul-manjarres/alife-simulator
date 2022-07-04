package org.yagamipaul.alife.entities.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import org.yagamipaul.alife.entities.Renderable;

public class ProximitySensor implements Sensor, Renderable {

  private float radius = 50f;
  private boolean triggered;

  protected Vector2 origin;

  public ProximitySensor(Vector2 origin) {
    this.origin = origin;
    this.triggered = true;
  }

  @Override
  public boolean isTriggered() {
    return this.triggered;
  }

  @Override
  public void render(SpriteBatch sb) {}

  @Override
  public void render(ShapeRenderer sr) {

    sr.setColor(this.triggered ? Color.RED : Color.GRAY);
    sr.circle(this.origin.x, this.origin.y, this.radius);
    sr.setColor(Color.WHITE);
  }
}
