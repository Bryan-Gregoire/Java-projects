package esi.atl.dto;

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

    public FavoriteDto(String origin, String destination) {
        super(null);
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

}
