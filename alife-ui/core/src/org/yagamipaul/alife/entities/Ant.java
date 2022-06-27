package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import org.yagamipaul.alife.manager.TextureManager;

public class Ant extends BaseEntity {

  public Ant(Vector2 position, Vector2 direction) {
    super(TextureManager.TRIANGLE_TEXTURE, position, direction);
    this.velocity = 4.0f;
    var rnd = new SecureRandom();
    this.direction = new Vector2(rnd.nextFloat(1) - 0.5f, rnd.nextFloat(1) - 0.5f);
  }

  @Override
  public void update() {

    this.position.add(this.direction);

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
