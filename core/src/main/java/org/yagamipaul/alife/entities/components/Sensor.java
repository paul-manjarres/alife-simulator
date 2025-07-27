package org.yagamipaul.alife.entities.components;

import java.util.List;
import org.yagamipaul.alife.entities.BaseEntity;

public interface Sensor {
    boolean isTriggered();

    default void check(List<BaseEntity> entities) {}
}
