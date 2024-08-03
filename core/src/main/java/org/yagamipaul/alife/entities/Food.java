package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.Vector2;
import org.yagamipaul.alife.manager.TextureManager;

import java.util.Collections;

public class Food extends NonLiving{

    public Food(Vector2 position, Vector2 direction) {
        super(TextureManager.POTATO_TEXTURE, position, direction);
    }

    @Override
    public void update() {

    }
}
