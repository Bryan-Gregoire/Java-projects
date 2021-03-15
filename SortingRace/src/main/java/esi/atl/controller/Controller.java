package esi.atl.controller;

import java.util.Objects;
import esi.atl.model.Model;
import esi.atl.model.SortType;
import esi.atl.view.InterfaceView;

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
     * @param facade represent the model.
     * @param ViewFX represent the view in javaFX.
     */
    public Controller(Model facade, InterfaceView ViewFX) {
        Objects.requireNonNull(facade, "Model is required");
        Objects.requireNonNull(ViewFX, "View is required");
        this.model = facade;
        this.view = ViewFX;
    }

    public void sortNbArrays(int nbThreads, int sizeArray, SortType typeSort) {
        model.sortArrays(nbThreads, sizeArray, typeSort, this.view);
    }
}
