package view;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public enum Level {

    VERY_EASY, EASY, MODERATE, HARD;

    @Override
    public String toString() {
        switch (this) {
            case VERY_EASY:
                return "Very Easy : 0 - 100 - 10";
            case EASY:
                return "Easy : 0 - 1 000 - 100";
            case MODERATE:
                return "Moderate : 0 - 10 000 - 1000";
            case HARD:
                return "Hard : 0 - 100 000 - 10 000";
        }
        return "";
    }

    public int getLevel() {
        switch (this) {
            case VERY_EASY:
                return 100;
            case EASY:
                return 1_000;
            case MODERATE:
                return 10_000;
            case HARD:
                return 100_000;
        }
        return 0;
    }
    
}
