package esi.atl.model;

import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StationData {

    private String name;
    private List lines;

    public StationData(String name, List lines) {
        this.name = name;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public List getLines() {
        return lines;
    }
        
}
