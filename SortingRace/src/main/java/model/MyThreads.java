package model;

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

    private final MergeSort merge;
    private final BubbleSort bubble;

    public static String ARRAY_SORT;
    public static String MILLI_SECOND;
    public static String OPERATIONS;

    public MyThreads(int[] arrayToSort) {
        this.pcs = new PropertyChangeSupport(this);
        
        array = arrayToSort;
        merge = new MergeSort();
        bubble = new BubbleSort();
    }

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();

        int[] oldArray = this.array;
        System.out.print("Mon tableau avant de trier : ");
        System.out.println(Arrays.toString(oldArray));
        nbOperations = merge.sort(array);
        System.out.print("Mon tableau après avoir été trier : ");
        System.out.println(Arrays.toString(array));
        
        pcs.firePropertyChange(ARRAY_SORT, null, array);
        pcs.firePropertyChange(OPERATIONS, 0, nbOperations);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        durationMilli = duration.toMillis();
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
