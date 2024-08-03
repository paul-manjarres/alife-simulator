package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class NonLiving extends BaseEntity{

    public NonLiving(Texture texture, Vector2 position, Vector2 direction) {
        super(texture, position, direction);
    }
}
