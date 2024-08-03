package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseEntity implements Observable {

    @Getter
    protected String id;
    protected String name;
    protected Texture texture;
    @Getter
    protected Vector2 position;
    @Getter
    protected Vector2 direction;
    protected float velocity;
    protected float rotation = 0.0f;
    protected PropertyChangeSupport pcSupport;
    @Getter protected Rectangle rect;


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
        this.id = EntityIdGenerator.createId();
        this.pcSupport = new PropertyChangeSupport(this);
        this.rect = new Rectangle(this.position.x - texture.getWidth() / 2,
          this.position.y - texture.getHeight() / 2,
          texture.getWidth(), texture.getHeight());
    }

    /**
     * Updates the state of the entity.
     */
    public abstract void update();

    /**
     *
     */
    public void render(SpriteBatch sb) {

        float x = this.position.x - texture.getWidth() / 2;
        float y = this.position.y - texture.getHeight() / 2;
        int width = this.texture.getWidth();
        int height = this.texture.getHeight();
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        float originX = (float) width / 2;
        float originY = (float) height / 2;

        //sb.setColor(Color.CYAN);
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


    @Override
    public void addObserver(PropertyChangeListener pcl) {
        this.pcSupport.addPropertyChangeListener(pcl);
    }

    @Override
    public void removeObserver(PropertyChangeListener pcl) {
        this.pcSupport.removePropertyChangeListener(pcl);
    }

}
