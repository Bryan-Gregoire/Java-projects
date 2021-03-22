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

        int step = array.length / 10;
        int sizeArraySort = 0;
        for (int i = 0; sizeArraySort <= sizeArray && i < nbThread; i++) {
            int[] arrayToSort = new int[sizeArraySort];
            for (int j = 0; j < step; j++) {
                if (sizeArraySort != 0) {
                    arrayToSort[j] = array[j];
                }
            }
            listThreads.add(new MyThreads(arrayToSort, sortType));
            sizeArraySort += step;
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
