package esi.atl.view;

import esi.atl.presenter.Presenter;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class MouseClickedHandler implements EventHandler<MouseEvent> {

    private final Presenter presenter;

    public MouseClickedHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(MouseEvent t) {
        this.presenter.setSelectedFavToFavField();
    }

}
