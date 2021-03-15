package esi.atl.model;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface Model {

    public void sortArrays(int nb, int size, SortType sort,
            PropertyChangeListener listener);

    public void addPropertyChangeListenerToAll(PropertyChangeListener listener);

    public void removePropertyChangeListenerToAll(
            PropertyChangeListener listener);

}
