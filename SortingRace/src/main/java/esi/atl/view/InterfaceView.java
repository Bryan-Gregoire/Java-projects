package esi.atl.view;

import esi.atl.controller.Controller;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface InterfaceView extends PropertyChangeListener {
    
    public void setController(Controller controller);
}
