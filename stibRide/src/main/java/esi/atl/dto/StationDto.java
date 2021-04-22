package esi.atl.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StationDto extends Dto<Integer> {

    private final String name;
    private final List<StopDto> stops;

    public StationDto(Integer key, String name) {
        super(key);
        this.name = name;
        stops = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public List<StopDto> getStops() { // bof.
        return stops;
    }

    public void addStops(StopDto stop) {
        stops.add(stop);
    }

}
