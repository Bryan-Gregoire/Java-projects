package esi.atl.model;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class BubbleSort implements Sort {

    /**
     * Source : https://www.baeldung.com/java-bubble-sort
     *
     * @param arr the given array to sort.
     *
     * @return the number of operation.
     */
    @Override
    public int sort(int[] arr) {
        int count = 0;
        int i = 0, n = arr.length;
        boolean swapNeeded = true;
        count = count + 3;

        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            count++;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapNeeded = true;
                    count = count + 4;
                }
            }
            if (!swapNeeded) {
                break;
            }
            i++;
            count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return "Tri a bulle";
    }
}
