package esi.atl.model;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface Model extends PropertyChangeListener {

    public void sortArrays(int nb, int size, SortType sort);

    /**
     * Add listener
     *
     * @param listener the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove listener.
     *
     * @param listener the listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

}
