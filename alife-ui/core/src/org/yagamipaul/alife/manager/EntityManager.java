package org.yagamipaul.alife.manager;

import com.badlogic.gdx.utils.Array;
import lombok.extern.slf4j.Slf4j;
import org.yagamipaul.alife.entities.BaseEntity;

@Slf4j
public class EntityManager {

  private Array<BaseEntity> entities;

  public EntityManager() {
    this.entities = new Array<BaseEntity>();
  }

  public Array<BaseEntity> getEntities() {
    return entities;
  }

  /**
   * @param entity
   */
  public void addEntity(BaseEntity entity) {
    log.info("Added entity: {}", entity.getId());
    this.entities.add(entity);
  }

  public void removeEntity(BaseEntity entity) {
    log.info("Removed entity: {}", entity.getId());
  }
}
