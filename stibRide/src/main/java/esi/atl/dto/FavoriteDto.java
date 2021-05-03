package esi.atl.dto;

import java.util.Objects;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteDto extends Dto<String> {

    private final String origin;
    private final String destination;

    public FavoriteDto(String key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.origin);
        hash = 67 * hash + Objects.hashCode(this.destination);
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
        final FavoriteDto other = (FavoriteDto) obj;
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        return super.equals(obj);
    }

}
