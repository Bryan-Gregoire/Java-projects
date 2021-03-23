package esi.atl.model;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class JobManager {

    private final int[] arraySort;
    private int step;

    public JobManager(int[] arraySort) {
        this.arraySort = arraySort;
        this.step = 0;
    }

    public synchronized int[] getNext() {
        if (step > this.arraySort.length) {
            return null;
        }
        int[] arrayThread = new int[step];
        for (int i = 0; i < arrayThread.length; i++) {
            arrayThread[i] = arraySort[i];
        }
        step = step + (arraySort.length / 10);
        return arrayThread;
    }
}
