package model;

import java.util.stream.IntStream;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        IntStream.range(0, n - 1)
                .flatMap(i -> IntStream.range(1, n - i))
                .forEach(j -> {
                    if (arr[j - 1] > arr[j]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                    }
                });
    }
}
