package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Model {

    private List<MyThreads> listThreads;
    private final Random rnd;

    public Model() {
        this.listThreads = new ArrayList<>();
        rnd = new Random();
    }

    public void sortArrays(int nb, int size, PropertyChangeListener listener) {
        fillThreadWithArray(nb, size);
        addPropertyChangeListenerToAll(listener);
        startThreads();
    }

    private void startThreads() {
        for (MyThreads listThread : listThreads) {
            listThread.start();
        }
    }

    private void fillThreadWithArray(int nbThread, int sizeArray) {
        int[] array = new int[sizeArray];

        for (int i = 0; i < sizeArray; i++) {
            array[i] = rnd.nextInt(sizeArray);
        }

        int increment = sizeArray / 10;
        int[] arrayToSort = new int[increment];
        for (int i = 0; i < nbThread; i++) {
            for (int j = 0; j < increment; j++) {
                arrayToSort[j] = array[i];
            }
            listThreads.add(new MyThreads(arrayToSort));
            increment += 10;
        }
    }

    private void addPropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : listThreads) {
            thread.addPropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListenerToAll(
            PropertyChangeListener listener) {

        for (MyThreads thread : listThreads) {
            thread.removePropertyChangeListener(listener);
        }
    }

}
