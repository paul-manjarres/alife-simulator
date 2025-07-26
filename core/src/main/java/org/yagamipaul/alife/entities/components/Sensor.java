package org.yagamipaul.alife.entities.components;

import java.util.List;
import org.yagamipaul.alife.entities.BaseEntity;

public interface Sensor {

    boolean isTriggered();

    //    void trigger(BaseEntity triggerEntity);

    default void check(List<BaseEntity> entities) {}
}
