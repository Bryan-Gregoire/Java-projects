package model;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class MergeSort implements Sort {

    /**
     * Source : https://www.baeldung.com/java-merge-sort.
     *
     * @param a the given array to sort.
     *
     * @return The number of operations.
     */
    @Override
    public int sort(int[] a) {
        int count = 0;
        int n = a.length;
        count++;
        if (n < 2) {
            return count;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        count = count + 3;

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            count++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            count++;
        }

        count += sort(l);
        count += sort(r);

        count = count + merge(a, l, r, mid, n - mid);

        return count;
    }

    private int merge(int[] a, int[] l, int[] r, int left, int right) {
        int count = 0;
        int i = 0, j = 0, k = 0;
        count = count + 4;

        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                count++;
            } else {
                a[k++] = r[j++];
                count++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            count++;
        }
        while (j < right) {
            a[k++] = r[j++];
            count++;
        }

        return count;
    }

}
