package esi.atl.dto;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteDto extends Dto<String> {

    private  String origin;
    private  String destination;

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

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    

}
