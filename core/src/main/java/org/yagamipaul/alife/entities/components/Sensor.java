package org.yagamipaul.alife.entities.components;

import org.yagamipaul.alife.entities.BaseEntity;

public interface Sensor {

    boolean isTriggered();

    void trigger(BaseEntity triggerEntity);
}
