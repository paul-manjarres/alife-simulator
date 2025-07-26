package org.yagamipaul.alife.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.yagamipaul.alife.entities.components.ProximitySensor;
import org.yagamipaul.alife.entities.components.Sensor;
import org.yagamipaul.alife.entities.components.SensorType;
import org.yagamipaul.alife.manager.TextureManager;

public class Organism extends BaseEntity {

    /** Health indicator of the entity */
    @Getter
    protected int health = 0;

    /** Flag indicator of the entity's status */
    @Getter
    protected boolean alive = true;

    @Getter
    protected Map<SensorType, Sensor> sensors;

    public Organism(Vector2 position, Vector2 direction) {
        super(TextureManager.CARNIVOROUS_TEXTURE, position, direction);
        this.velocity = 4.0f;
        var rnd = new SecureRandom();
        var angle = rnd.nextInt(360);
        this.direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));

        this.size = 128;
        this.health = 100;
        this.sensors = new HashMap<>();
        this.sensors.put(SensorType.PROXIMITY, new ProximitySensor(this, this.position));
    }

    @Override
    public void update(float delta) {

        float factor = 50f * delta;
        // Se debe conectar el sensor.
        // Si hay detectado algo en la cercania, dirigirse a el
        // en colision, se elimina el obstaculo si es comida

        this.position.add(this.direction.x * factor, this.direction.y * factor);
        this.rect.setPosition(this.position.x - getRect().width / 2, this.position.y - getRect().height / 2);

        int random = new SecureRandom().nextInt(100);
        if (random < 10) {
            this.decreaseHealth(1);
        }

        ProximitySensor ps = (ProximitySensor) this.sensors.get(SensorType.PROXIMITY);
        if (ps.isTriggered()) {
            // Stop movement.

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

    //    public int increaseHealth(int value) {
    //        if (!alive) {
    //            return this.health;
    //        }
    //
    //        if (value < 0) {
    //            value = 0;
    //        }
    //        this.health += value;
    //        return this.health;
    //    }

    @Override
    public String toString() {
        return "Organism[" + this.id + "]";
    }
}
