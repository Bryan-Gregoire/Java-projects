package esi.atl.view;

import esi.atl.exception.RepositoryException;
import esi.atl.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class InsertHandler implements EventHandler<ActionEvent> {

    final Presenter presenter;

    public InsertHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            presenter.insertUpdateFav();
        } catch (RepositoryException ex) {
            System.out.println("Repository error " + ex.getMessage());
        }
    }
}
