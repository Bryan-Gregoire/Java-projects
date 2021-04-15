package esi.atl.dto;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StationDto extends Dto<Integer> {

    private String name;

    public StationDto(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
