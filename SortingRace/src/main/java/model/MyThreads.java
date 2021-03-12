package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class MyThreads extends Thread {

    private final PropertyChangeSupport pcs;
    private int[] array;
    private long durationMilli;

    public MyThreads(int[] arrayToSort) {
        this.pcs = new PropertyChangeSupport(this);
        array = arrayToSort;
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        durationMilli = duration.toMillis();
    }

    public long getDurationMilli() {
        return durationMilli;
    }

    /**
     * Add listener
     *
     * @param listener the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove listener.
     *
     * @param listener the listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

}
