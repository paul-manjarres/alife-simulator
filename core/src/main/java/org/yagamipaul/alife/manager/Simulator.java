package org.yagamipaul.alife.manager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.yagamipaul.alife.entities.BaseEntity;

@Slf4j
public class Simulator implements PropertyChangeListener {

    private final Map<String, BaseEntity> entities;

    public Simulator() {
        this.entities = new ConcurrentHashMap<>();
    }

    public void addEntity(BaseEntity entity) {
        log.info("Added entity: {}", entity.getId());
        this.entities.put(entity.getId(), entity);
    }

    public void removeEntity(BaseEntity entity) {
        log.info("Removed entity: {}", entity.getId());
        this.entities.remove(entity.getId());
    }

    public List<BaseEntity> getEntities() {
        return new ArrayList<>(this.entities.values());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        log.info("Change: {}", evt);

        // If entity died, remove listener and remove it from entity list.
        if (evt.getPropertyName().equals("alive") && evt.getNewValue().equals(false)) {
            BaseEntity entity = (BaseEntity) evt.getSource();
            entity.removeObserver(this);
            this.entities.remove(entity.getId());
        }
    }
}
