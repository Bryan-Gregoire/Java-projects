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

    private final StationRepository repo;

    //
    public Facade() throws RepositoryException {
        this.pcs = new PropertyChangeSupport(this);
        this.repo = new StationRepository();
    }

    @Override
    public List getAllStationsName() throws RepositoryException {
        List<StationDto> dtos = repo.getAll(); // After this better FIRE or let the FOR ?
        List nameStation = new ArrayList();
        for (StationDto dto : dtos) {
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
        return stations;
    }

    public List<StationDto> itinerary(List<StationDto> stations) {
        Graph graph = new Graph();
        fillGraph(graph, stations);

        return null;
    }

    private void fillGraph(Graph graph, List<StationDto> stations) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            nodes.add(new Node(stations.get(i).getName()));
        }
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    for (int k = 0; k < stations.get(i).getStops().size(); k++) {
                        for (int l = 0; l < stations.get(k).getStops().size(); l++) {
                            if (stations.get(i).getStops().get(k).getLine() == stations.get(j).getStops().get(k).getLine()) {
                                if (stations.get(i).getStops().get(k).getOrder() - 1 == stations.get(j).getStops().get(k).getOrder()
                                        || stations.get(i).getStops().get(k).getOrder() + 1 == stations.get(j).getStops().get(k).getOrder()) {
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    private void addStationsAdjacent(Graph graph, List<StationDto> stations) {
//        for (int i = 0; i < graph.getNodes().size(); i++) {
//            for (int j = 1; j < graph.getNodes().size(); j++) {
//                for (int k = 0; k < stations.get(i).getStops().size(); k++) {
//                    if (stations.get(i).getStops().get(k).getLine() == stations.get(j).getStops().get(k).getLine()) {
//                        if (stations.get(i).getStops().get(k).getOrder() - 1 == stations.get(j).getStops().get(k).getOrder()
//                                || stations.get(i).getStops().get(k).getOrder() + 1 == stations.get(j).getStops().get(k).getOrder()) {
//
//                        }
//                    }
//                }
//            }
//        }
//    }
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
