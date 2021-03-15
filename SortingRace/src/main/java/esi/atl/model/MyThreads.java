package esi.atl.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

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
    public static String MILLI_SECOND = "Milli_seconds";
    public static String OPERATIONS = "Operations";

    private Sort sorter;

    public MyThreads(int[] arrayToSort, SortType typeSort) {
        this.pcs = new PropertyChangeSupport(this);
        array = arrayToSort;
        nbOperations = 0;
        durationMilli = 0;

        switch (typeSort) {
            case BUBBLE:
                this.sorter = new BubbleSort();
            case TRI_FUSION:
                this.sorter = new MergeSort();
        }
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();

        int[] oldArray = Arrays.copyOf(array, array.length);
        System.out.println("Mon tableau avant de trier : ");
        System.out.println(Arrays.toString(oldArray));
        nbOperations += sorter.sort(array);
        System.out.println("Mon tableau après avoir été trier : ");
        System.out.println(Arrays.toString(array));

        pcs.firePropertyChange(ARRAY_SORT, oldArray, this.array);
        pcs.firePropertyChange(OPERATIONS, 0, nbOperations);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        durationMilli = duration.toMillis();
        pcs.firePropertyChange(MILLI_SECOND, 0, durationMilli);
        pcs.firePropertyChange(MILLI_SECOND, 0, durationMilli);
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
