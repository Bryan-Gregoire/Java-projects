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

    public void sortArrays(int nb, int size) {
        fillWithThread(nb, size);
        startThreads();
    }

    private void startThreads() {
        for (MyThreads listThread : listThreads) {
            listThread.start();
        }
    }

    private void fillWithThread(int nb, int size) {
        for (int i = 0; i < nb; i++) {
            int[] array = new int[size];
            for (int j = 0; j < nb; j++) {
                array[j] = rnd.nextInt(100);
            }
            listThreads.add(new MyThreads(array));
        }
    }

    public void addPropertyChangeListenerToAll(
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
