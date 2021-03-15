package esi.atl.model;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public enum SortType {

    BUBBLE, TRI_FUSION;

    @Override
    public String toString() {
        switch (this) {
            case BUBBLE:
                return "Tri a bulle";
            case TRI_FUSION:
                return "Tri fusion";
        }
        return "";
    }
}
