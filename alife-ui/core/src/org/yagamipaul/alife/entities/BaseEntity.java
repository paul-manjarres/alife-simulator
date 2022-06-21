package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseEntity {

    protected Texture texture;

    protected Vector2 position;

    protected Vector2 direction;

    protected float velocity;

    protected float rotation = 0.0f;

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
    }

    /**
     *
     */
    public abstract void update();

    /**
     *
     */
    public void render(SpriteBatch sb) {

        float x = this.position.x;
        float y = this.position.y;
        int width = this.texture.getWidth();
        int height = this.texture.getHeight();
        float scaleX = 1f;
        float scaleY = 1f;
        float originX = width / 2;
        float originY = height / 2;



        sb.draw(this.texture, x, y, originX, originY, width, height, scaleX, scaleY, this.rotation,
                0, 0, width, height, false, false);
    }
}
