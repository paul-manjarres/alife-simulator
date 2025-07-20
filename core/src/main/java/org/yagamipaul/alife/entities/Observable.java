package org.yagamipaul.alife.entities;

import java.beans.PropertyChangeListener;

/** Observable implementation. */
public interface Observable {

    void addObserver(PropertyChangeListener pcl);

    void removeObserver(PropertyChangeListener pcl);
}
