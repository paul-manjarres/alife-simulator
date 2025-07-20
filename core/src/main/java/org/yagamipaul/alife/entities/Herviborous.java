package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.Vector2;
import org.yagamipaul.alife.manager.TextureManager;

public class Herviborous extends Organism {
    public Herviborous(Vector2 position, Vector2 direction) {
        super(position, direction);
        this.texture = TextureManager.HERVIVOROUS_TEXTURE;
    }
}
