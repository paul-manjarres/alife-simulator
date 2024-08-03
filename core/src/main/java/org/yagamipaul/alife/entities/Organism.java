package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import org.yagamipaul.alife.entities.components.ProximitySensor;
import org.yagamipaul.alife.entities.components.Sensor;
import org.yagamipaul.alife.manager.TextureManager;

public class Organism extends BaseEntity {

    /**
     * Health indicator of the entity
     */
    @Getter
    protected int health = 0;

    /**
     * Flag indicator of the entity's status
     */
    @Getter
    protected boolean alive = true;
    @Getter
    protected List<Sensor> sensors;

    protected boolean move = false;
    protected Vector2 targetPosition = Vector2.Zero;



    public Organism(Vector2 position, Vector2 direction) {
        super(TextureManager.TRIANGLE_TEXTURE, position, direction);
        this.velocity = 4.0f;
        var rnd = new SecureRandom();
        var angle = rnd.nextInt(360);
        this.direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));

        this.health = 100;
        this.sensors = new ArrayList<>();
        this.sensors.add(new ProximitySensor(this, this.position));

    }

    @Override
    public void update() {

        this.position.add(this.direction);
        this.rect.setPosition(this.position.x-this.texture.getWidth()/2,
          this.position.y - texture.getHeight()/2);
        // this.rotation-=2;

        int random = new SecureRandom().nextInt(100);
        if (random < 10) {
            this.decreaseHealth(1);
        }
    }


    public int decreaseHealth(int value) {
        if (!alive) {
            return this.health;
        }
        if (value < 0) {
            value = 0;
        }
        this.health -= value;
        if (this.health <= 0) {
            die();
        }
        return this.health;
    }

    private void die() {
        this.health = 0;
        this.alive = false;
        pcSupport.firePropertyChange("alive", true, false);
    }

    public int increaseHealth(int value) {
        if (!alive) {
            return this.health;
        }

        if (value < 0) {
            value = 0;
        }
        this.health += value;
        return this.health;
    }

    @Override
    public String toString() {
        return "Organism[" + this.id + "]";
    }
}
