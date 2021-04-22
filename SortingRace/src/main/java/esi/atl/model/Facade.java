package esi.atl.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Facade implements Model {

    private final PropertyChangeSupport pcs;

    public static String ARRAY_SORT = "ARRAY_SORTED";
    public static String ACTIVE_THREAD = "ACTIVE";

    private List<MyThreads> listThreads;
    private final Random rnd;

    public Facade() {
        this.pcs = new PropertyChangeSupport(this);
        this.listThreads = new ArrayList<>();
        rnd = new Random();
    }

    /**
     * Start threads that will sort an array of random values.
     *
     * @param nbThread number of threads to create.
     * @param size size of the array to sort.
     * @param sort type of sort.
     */
    @Override
    public void sortArrays(int nbThread, int size, SortType sort) {
        fillThreadWithArray(nbThread, size, sort);
        addPropertyChangeListenerToAll(this);
        startThreads();
    }

    /**
     * Create an array of random values, an object that will manage the array to
     * sort and the type of sorting will be passed as a parameter to all
     * threads.
     *
     * @param nbThread number of threads to create.
     * @param sizeArray size of the array.
     * @param sortType type of sort.
     */
    private void fillThreadWithArray(int nbThread, int sizeArray,
            SortType sortType) {
        listThreads.clear();
        int[] array = new int[sizeArray];

        for (int i = 0; i < sizeArray; i++) {
            array[i] = rnd.nextInt(sizeArray);
        }

        JobManager manager = new JobManager(array);

        for (int i = 0; i < nbThread; i++) {
            listThreads.add(new MyThreads(sortType, manager));
        }
    }

    /**
     * Add the given listener to all my list of threads.
     *
     * @param listener the listener.
     */
    private void addPropertyChangeListenerToAll(
            PropertyChangeListener listener) {
        for (MyThreads thread : listThreads) {
            thread.addPropertyChangeListener(listener);
        }
    }

    /**
     * Start all the threads.
     *
     */
    private void startThreads() {
        for (MyThreads listThread : listThreads) {
            listThread.start();
        }
    }

    /**
     * remove the given listener to all my list of threads.
     *
     * @param listener the listener.
     */
    private void removePropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : listThreads) {
            thread.removePropertyChangeListener(listener);
        }
    }

    /**
     * Add listener
     *
     * @param listener the listener.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove listener.
     *
     * @param listener the listener.
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(MyThreads.ACTIVE)) {
            pcs.firePropertyChange(ACTIVE_THREAD, evt.getOldValue(),
                    evt.getNewValue());
        }
        if (evt.getPropertyName().equals(MyThreads.ARRAY_SORT)) {
            pcs.firePropertyChange(ARRAY_SORT, evt.getOldValue(),
                    evt.getNewValue());
        }
    }
}
