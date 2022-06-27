package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import org.yagamipaul.alife.manager.TextureManager;

public class Ant extends BaseEntity {

  public Ant(Vector2 position, Vector2 direction) {
    super(TextureManager.CIRCLE_TEXTURE, position, direction);
    this.velocity = 4.0f;
  }

  @Override
  public void update() {

    var rnd = new SecureRandom();

    //this.position.add(rnd.nextFloat(20) - 10, rnd.nextFloat(20) - 10);

    int random = new SecureRandom().nextInt(100);
    if (random < 30) {
      this.decreaseHealth(1);
    }
  }

  @Override
  public String toString() {
    return "Ant[" + this.id + "]";
  }
}
