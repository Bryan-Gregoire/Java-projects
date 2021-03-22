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

    private int[] array;
    private long durationMilli;
    private long nbOperations;

    public static String ARRAY_SORT = "sort array";

    private Sort sorter;

    public MyThreads(int[] arrayToSort, SortType typeSort) {
        this.pcs = new PropertyChangeSupport(this);

        array = arrayToSort;
        nbOperations = 0;
        durationMilli = 0;

        switch (typeSort) {
            case BUBBLE:
                this.sorter = new BubbleSort();
                break;
            case TRI_FUSION:
                this.sorter = new MergeSort();
        }
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();

        nbOperations += sorter.sort(array);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        durationMilli = duration.toMillis();

        ArrayData data = new ArrayData(sorter.toString(), array.length,
                nbOperations, durationMilli);

        pcs.firePropertyChange(ARRAY_SORT, null, data);

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
