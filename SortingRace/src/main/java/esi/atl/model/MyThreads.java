package esi.atl.model;

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

    private long durationMilli;
    private long nbOperations;

    public static String ARRAY_SORT = "array_sorted";
    public static String ACTIVE = "active thread";

    private Sort sorter;
    private JobManager manager;

    public MyThreads(SortType typeSort, JobManager manager) {
        this.pcs = new PropertyChangeSupport(this);

        nbOperations = 0;
        durationMilli = 0;

        switch (typeSort) {
            case BUBBLE:
                this.sorter = new BubbleSort();
                break;
            case TRI_FUSION:
                this.sorter = new MergeSort();
        }

        this.manager = manager;
    }

    @Override
    public void run() {
        pcs.firePropertyChange(ACTIVE, 0, 1);
        int[] array = manager.getNext();
        while (array != null) {
            LocalDateTime start = LocalDateTime.now();

            nbOperations = sorter.sort(array);

            LocalDateTime end = LocalDateTime.now();
            durationMilli = Duration.between(start, end).toMillis();

            ArrayData data = new ArrayData(sorter.toString(), array.length,
                    nbOperations, durationMilli);

            pcs.firePropertyChange(ARRAY_SORT, null, data);
            array = manager.getNext();
        }

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
