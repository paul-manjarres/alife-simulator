package org.yagamipaul.alife.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import lombok.Getter;
import org.yagamipaul.alife.screen.hud.FloatingEntityInfo;

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

    @Getter
    protected Rectangle rect;

    protected int size;

    @Getter
    protected FloatingEntityInfo info;

    protected BaseEntity(Texture texture, Vector2 position, Vector2 direction) {
        super();
        this.texture = texture;
        this.position = position;
        this.direction = direction;
        this.id = EntityIdGenerator.createId();
        this.pcSupport = new PropertyChangeSupport(this);
        this.rect = new Rectangle(
                this.position.x - texture.getWidth() / 2f,
                this.position.y - texture.getHeight() / 2f,
                texture.getWidth(),
                texture.getHeight());

        this.info = new FloatingEntityInfo(this);
    }

    /** Updates the state of the entity. */
    public abstract void update(float delta);

    /** */
    public void render(SpriteBatch sb) {

        float x = this.position.x - texture.getWidth() / 2f;
        float y = this.position.y - texture.getHeight() / 2f;

        int width = this.texture.getWidth();
        int height = this.texture.getHeight();
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        float originX = 0f;
        float originY = 0f;

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

    public void renderRect(ShapeRenderer sr) {
        sr.setColor(Color.CYAN);
        sr.rect(rect.x, rect.y, rect.width, rect.height);
        sr.setColor(Color.WHITE);
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
