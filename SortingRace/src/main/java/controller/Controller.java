package controller;

import java.beans.PropertyChangeListener;
import java.util.Objects;
import model.Model;
import view.InterfaceView;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Controller {

    private final Model model;
    private final InterfaceView view;

    /**
     * Constructor.
     *
     * @param model represent the model.
     * @param ViewFX represent the view in javaFX.
     */
    public Controller(Model model, InterfaceView ViewFX) {
        Objects.requireNonNull(model, "Model is required");
        Objects.requireNonNull(ViewFX, "View is required");
        this.model = model;
        this.view = ViewFX;
    }

    public void sortNbArrays(int nbThreads, int sizeArray) {
        model.sortArrays(nbThreads, sizeArray, this.view);
    }
}
