package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import org.yagamipaul.alife.manager.TextureManager;

public class Organism extends BaseEntity {

  public Organism(Vector2 position, Vector2 direction) {
    super(TextureManager.TRIANGLE_TEXTURE, position, direction);
    this.velocity = 4.0f;
    var rnd = new SecureRandom();
    var angle = rnd.nextInt(360);
    this.direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));
    // this.rotation = angle;
    // *MathUtils.degreesToRadians;
  }

  @Override
  public void update() {

    this.position.add(this.direction);
    // this.rotation-=2;

    int random = new SecureRandom().nextInt(100);
    if (random < 25) {
      this.decreaseHealth(1);
    }
  }

  @Override
  public String toString() {
    return "Ant[" + this.id + "]";
  }
}
