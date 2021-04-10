package esi.atl.presenter;

import esi.atl.model.Model;
import esi.atl.view.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Presenter implements PropertyChangeListener {

    private Model model;
    private View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO
    }

}
