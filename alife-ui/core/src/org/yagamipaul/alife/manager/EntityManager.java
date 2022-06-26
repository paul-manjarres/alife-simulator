package org.yagamipaul.alife.manager;

import com.badlogic.gdx.utils.Array;
import org.yagamipaul.alife.entities.BaseEntity;

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
    this.entities.add(entity);
  }
}
