package esi.atl.dto;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteDto extends Dto<Integer> {

    private String name;
    private String origin;
    private String destination;

    public FavoriteDto(String name, String origin, String destination) {
        super(-1);
        this.name = name;
        this.origin = origin;
        this.destination = destination;
    }

    public FavoriteDto(Integer key, String name, String origin, String destination) {
        super(key);
        this.name = name;
        this.origin = origin;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "FavoriteDto{" + "key=" + getKey() + "name=" + name + ", origin=" + origin + ", destination=" + destination + '}';
    }

}
