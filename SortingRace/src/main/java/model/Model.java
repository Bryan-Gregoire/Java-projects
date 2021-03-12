package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Model {

    private List<MyThreads> threads;

    public Model() {
        this.threads = new ArrayList<>();
    }

    public void addPropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : threads) {
            thread.addPropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : threads) {
            thread.removePropertyChangeListener(listener);
        }
    }

}
