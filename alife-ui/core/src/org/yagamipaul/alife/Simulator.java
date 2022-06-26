package org.yagamipaul.alife;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Slf4j
public class Simulator implements PropertyChangeListener {


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        log.info("Change: {}", evt);

    }
}
