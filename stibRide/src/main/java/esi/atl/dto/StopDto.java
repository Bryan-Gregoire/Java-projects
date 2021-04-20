package esi.atl.dto;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StopDto {

    private final int line;
    private final int station;
    private final int order;

    public StopDto(int line, int station, int order) {
        this.line = line;
        this.station = station;
        this.order = order;
    }

    public int getLine() {
        return line;
    }

    public int getStation() {
        return station;
    }

    public int getOrder() {
        return order;
    }

}
