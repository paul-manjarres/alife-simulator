package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.Vector2;
import org.yagamipaul.alife.manager.TextureManager;

public class Ant extends BaseEntity{

    public Ant(Vector2 position, Vector2 direction) {
        super(TextureManager.ANT_TEXTURE, position, direction);
        this.velocity = 4.0f;
    }

    @Override
    public void update() {
        this.decreaseHealth(1);

    }
}
