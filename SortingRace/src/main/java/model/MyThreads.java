package model;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class MyThreads {

    private final PropertyChangeSupport pcs;

    public MyThreads() {
        this.pcs = new PropertyChangeSupport(this);
    }

}
