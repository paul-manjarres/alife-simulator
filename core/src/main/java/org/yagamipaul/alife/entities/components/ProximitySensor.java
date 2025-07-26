package org.yagamipaul.alife.entities.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.yagamipaul.alife.entities.BaseEntity;
import org.yagamipaul.alife.entities.Renderable;

@Slf4j
public class ProximitySensor implements Sensor, Renderable {

    private float radius = 50f;
    private boolean triggered;
    private BaseEntity entity;
    protected Vector2 origin;

    public ProximitySensor(BaseEntity entity, Vector2 origin) {
        this.entity = entity;
        this.origin = origin;
        this.triggered = false;
    }

    @Override
    public boolean isTriggered() {
        return this.triggered;
    }

    @Override
    public void check(List<BaseEntity> entities) {
        Circle c = new Circle(origin, radius);
        for (BaseEntity e : entities) {
            Vector2 center = new Vector2();
            center = e.getRect().getCenter(center);
            if (!triggered && !this.entity.getId().equals(e.getId()) && c.contains(center)) {
                log.debug("Proximity sensor on entity: {} triggered by entity: {}", this.entity.getId(), e.getId());
                triggered = true;
                break;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {}

    @Override
    public void render(ShapeRenderer sr) {
        sr.setColor(this.triggered ? Color.RED : Color.LIGHT_GRAY);
        sr.circle(this.origin.x, this.origin.y, this.radius);
        sr.setColor(Color.WHITE);
    }
}
