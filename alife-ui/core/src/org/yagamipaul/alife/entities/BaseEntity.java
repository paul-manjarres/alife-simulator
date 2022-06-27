package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import lombok.Getter;

public abstract class BaseEntity implements Observable {

  protected String name;

  @Getter protected String id;

  protected Texture texture;

  protected Vector2 position;

  protected Vector2 direction;

  protected float velocity;

  protected float rotation = 0.0f;

  /** Health indicator of the entity */
  protected int health = 0;

  /** Flag indicator of the entity's status */
  protected boolean alive = true;

  private PropertyChangeSupport pcSupport;

  /**
   * @param texture
   * @param position
   * @param direction
   */
  public BaseEntity(Texture texture, Vector2 position, Vector2 direction) {
    super();
    this.texture = texture;
    this.position = position;
    this.direction = direction;
    this.health = 100;
    this.id = EntityIdGenerator.createId();
    this.pcSupport = new PropertyChangeSupport(this);
  }

  /** Updates the state of the entity. */
  public abstract void update();

  /** */
  public void render(SpriteBatch sb) {

    float x = this.position.x;
    float y = this.position.y;
    int width = this.texture.getWidth();
    int height = this.texture.getHeight();
    float scaleX = 0.5f;
    float scaleY = 0.5f;
    float originX = width / 2;
    float originY = height / 2;

    sb.setColor(Color.CYAN);
    sb.draw(
        this.texture,
        x,
        y,
        originX,
        originY,
        width,
        height,
        scaleX,
        scaleY,
        this.rotation,
        0,
        0,
        width,
        height,
        false,
        false);
    sb.setColor(Color.WHITE);
  }

  public int increaseHealth(int value) {
    if (!alive) {
      return this.health;
    }

    if (value < 0) {
      value = 0;
    }
    this.health += value;
    return this.health;
  }

  public int decreaseHealth(int value) {
    if (!alive) {
      return this.health;
    }
    if (value < 0) {
      value = 0;
    }
    this.health -= value;
    if (this.health <= 0) {
      die();
    }
    return this.health;
  }

  private void die() {
    this.health = 0;
    this.alive = false;
    pcSupport.firePropertyChange("alive", true, false);
  }

  @Override
  public void addObserver(PropertyChangeListener pcl) {
    this.pcSupport.addPropertyChangeListener(pcl);
  }

  @Override
  public void removeObserver(PropertyChangeListener pcl) {
    this.pcSupport.removePropertyChangeListener(pcl);
  }

  public int getHealth() {
    return health;
  }

  public Vector2 getPosition() {
    return position;
  }

  public boolean isAlive() {
    return alive;
  }
}
