package esi.atl.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Facade implements Model {

    private List<MyThreads> listThreads;
    private final Random rnd;

    public Facade() {
        this.listThreads = new ArrayList<>();
        rnd = new Random();
    }

    @Override
    public void sortArrays(int nb, int size, SortType sort,
            PropertyChangeListener listener) {
        fillThreadWithArray(nb, size, sort);
        addPropertyChangeListenerToAll(listener);
        startThreads();
    }

    private void startThreads() {
        for (MyThreads listThread : listThreads) {
            listThread.start();
        }
    }

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

    @Override
    public void addPropertyChangeListenerToAll(
            PropertyChangeListener listener) {
        for (MyThreads thread : listThreads) {
            thread.addPropertyChangeListener(listener);
        }
    }

    @Override
    public void removePropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : listThreads) {
            thread.removePropertyChangeListener(listener);
        }
    }

}
