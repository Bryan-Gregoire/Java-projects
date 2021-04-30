package esi.atl.model;

import esi.atl.dto.FavoriteDto;
import esi.atl.dto.StationDto;
import esi.atl.dto.StopDto;
import esi.atl.exception.RepositoryException;
import esi.atl.repository.FavoriteRepository;
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

    public static String SHORT_PATH = "SHORTEST_PATH";
    public static String INSERT_FAV = "INSERT";
    public static String DELETE_FAV = "DELETE";
    public static String UPDATE_FAV = "UPDATE";

    private final StationRepository stationRepo;
    private final FavoriteRepository favRepo;

    private List<StationDto> stations;

    public Facade() throws RepositoryException {
        this.pcs = new PropertyChangeSupport(this);
        this.stationRepo = new StationRepository();
        this.favRepo = new FavoriteRepository();
        getFullStation();
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
    public void insertFavorite(FavoriteDto dto) throws RepositoryException {
        favRepo.add(dto);
        pcs.firePropertyChange(INSERT_FAV, null, dto);
    }

    @Override
    public void deleteFavorite(FavoriteDto dto) throws RepositoryException {
        favRepo.remove(dto.getKey());
        pcs.firePropertyChange(DELETE_FAV, null, dto);
    }

    @Override
    public void updateFavorite(FavoriteDto oldDto, FavoriteDto newDto)
            throws RepositoryException {
        favRepo.update(newDto, oldDto.getKey());
        pcs.firePropertyChange(UPDATE_FAV, oldDto, newDto);
    }

    @Override
    public List<FavoriteDto> getAllFavorites() throws RepositoryException {
        List<FavoriteDto> favorites = favRepo.getAll();
        return favorites;
    }

    private void getFullStation() throws RepositoryException {
        stations = stationRepo.getAll();
        List<StopDto> stops = stationRepo.getAllStops();

        for (int i = 0; i < stations.size(); i++) {
            StationDto stt = stations.get(i);
            for (int j = 0; j < stops.size(); j++) {
                StopDto stop = stops.get(j);
                if (stt.getKey() == stop.getStation()) {
                    stt.getStops().add(stops.get(j));
                }
            }
        }
    }

    private void getStibDatas(List<Node> path, Node destination) {
        List<StationData> datas = new ArrayList<>();
        path.add(destination);
        for (int i = 0; i < path.size(); i++) {
            Node origin = path.get(i);

            for (int j = 0; j < stations.size(); j++) {
                StationDto target = stations.get(j);

                if (origin.getName().equals(target.getName())) {
                    List lines = new ArrayList();
                    for (StopDto stop : target.getStops()) {
                        lines.add(stop.getLine());
                    }
                    datas.add(new StationData(target.getName(), lines));
                }
            }
        }
        pcs.firePropertyChange(SHORT_PATH, null, datas);
    }

    private Node getNode(Graph graph, String nameStation) {
        for (Node node : graph.getNodes()) {
            if (node.getName().equals(nameStation)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void calculateItinerary(String nameOrigin, String nameDest) throws RepositoryException {
        Graph graph = new Graph();
        fillGraph(graph, stations);
        Node source = getNode(graph, nameOrigin);
        graph = Dijkstra.calculateShortestPathFromSource(graph, source);
        Node destination = getNode(graph, nameDest);
        getStibDatas(destination.getShortestPath(), destination);
    }

    private void fillGraph(Graph graph, List<StationDto> stations) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            nodes.add(new Node(stations.get(i).getName()));
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
