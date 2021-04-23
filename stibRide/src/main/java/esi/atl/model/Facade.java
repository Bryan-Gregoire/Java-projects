package esi.atl.model;

import esi.atl.dto.StationDto;
import esi.atl.dto.StopDto;
import esi.atl.exception.RepositoryException;
import esi.atl.repository.StationRepository;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Facade implements Model {

    private final PropertyChangeSupport pcs;

    private static String FULL_STATION = "STATIONS";
    private static String SHORTPATH = "SHORTEST_PATH";

    private final StationRepository repo;

    private List<StationDto> stations;

    public Facade() throws RepositoryException {
        this.pcs = new PropertyChangeSupport(this);
        this.repo = new StationRepository();
        stations = getFullStation(); // FIRE or no ?
    }

    @Override
    public List getAllStationsName() throws RepositoryException {
        List nameStation = new ArrayList();
        for (StationDto dto : stations) {
            String station = dto.getName();
            nameStation.add(station);
        }
        return nameStation;
    }

    @Override
    public List<StationDto> getFullStation() throws RepositoryException {
        List<StationDto> stations = repo.getAll();
        List<StopDto> stops = repo.getAllStops();

        for (int i = 0; i < stations.size(); i++) {
            StationDto stt = stations.get(i);
            for (int j = 0; j < stops.size(); j++) {
                StopDto stop = stops.get(j);
                if (stt.getKey() == stop.getStation()) {
                    stt.getStops().add(stops.get(j));
                }
            }
        }
        //pcs.firePropertyChange(FULL_STATION, null, stations);  GOOD ?
        return stations;
    }

    @Override
    public Graph itinerary(int source_Id_Station) throws RepositoryException {
        Graph graph = new Graph();
        fillGraph(graph, stations);
        //Node source = graph.getNodes().
        //Dijkstra.calculateShortestPathFromSource(graph, source_Id_Station);
        return null;
    }

    private void fillGraph(Graph graph, List<StationDto> stations) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            nodes.add(new Node(stations.get(i).getKey()));
        }

        for (int i = 0; i < nodes.size(); i++) { // on parcourt toutes les station.
            StationDto origin = stations.get(i);

            for (int j = 0; j < nodes.size(); j++) { // on parcourt toutes les station.

                if (i != j) {
                    StationDto target = stations.get(j);

                    for (int k = 0; k < origin.getStops().size(); k++) { // on cherche les adjacent parmi toutes les stations.
                        StopDto originStop = origin.getStops().get(k);

                        for (int l = 0; l < target.getStops().size(); l++) { // on cherche les adjacent parmi toutes les stations.
                            StopDto targetStop = target.getStops().get(l);

                            if (originStop.getLine() == targetStop.getLine()) { // Si les stations sont sur la même ligne.
                                if (originStop.getOrder() - 1
                                        == targetStop.getOrder()
                                        || originStop.getOrder() + 1
                                        == targetStop.getOrder()) { // Si la station d'origine a un voisin.
                                    nodes.get(i)
                                            .addDestination(nodes.get(j), 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        graph.getNodes().addAll(nodes);
    }

    /**
     * Add listener
     *
     * @param listener the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove listener.
     *
     * @param listener the listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
