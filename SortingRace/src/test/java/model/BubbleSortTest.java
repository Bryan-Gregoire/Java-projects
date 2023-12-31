package model;

import esi.atl.model.BubbleSort;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class BubbleSortTest {

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSort() {
        int[] array = {2, 1, 4, 6, 3, 5};
        int[] sortedArray = {1, 2, 3, 4, 5, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortDuplicatesValues() {
        int[] array = {2, 2, 1, 1, 4, 4, 6, 6, 3, 3, 5, 5};
        int[] sortedArray = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortDuplicatesValues_2() {
        int[] array = {2, 5, 1, 4, 4, 1, 3, 6, 3, 6, 5, 2};
        int[] sortedArray = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortAlreadySort() {
        int[] array = {4, 5, 6, 7, 8};
        int[] sortedArray = {4, 5, 6, 7, 8};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortDesc() {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] sortedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortOnlyNegativeValues() {
        int[] array = {-2, -1, -4, -6, -3, -5};
        int[] sortedArray = {-6, -5, -4, -3, -2, -1};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSortPositiveAndNegativeValues() {
        int[] array = {2, 1, -4, -6, -3, 5};
        int[] sortedArray = {-6, -4, -3, 1, 2, 5};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSameValue() {
        int[] array = {5, 5, 5, 5, 5, 5};
        int[] sortedArray = {5, 5, 5, 5, 5, 5};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);

        assertArrayEquals(array, sortedArray);
    }

}
