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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.line;
        hash = 67 * hash + this.station;
        hash = 67 * hash + this.order;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StopDto other = (StopDto) obj;
        if (this.line != other.line) {
            return false;
        }
        if (this.station != other.station) {
            return false;
        }
        if (this.order != other.order) {
            return false;
        }
        return true;
    }
}
