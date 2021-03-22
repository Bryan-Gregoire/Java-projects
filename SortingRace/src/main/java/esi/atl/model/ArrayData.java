package esi.atl.model;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class ArrayData {

    private final String name;
    private final int size;
    private final long nbOperationsSort;
    private final long timeToSort;

    public ArrayData(String name, int size, long nbOperationsSort,
            long timeToSort) {
        this.name = name;
        this.size = size;
        this.nbOperationsSort = nbOperationsSort;
        this.timeToSort = timeToSort;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public long getNbOperationsSort() {
        return nbOperationsSort;
    }

    public long getTimeToSort() {
        return timeToSort;
    }

}
