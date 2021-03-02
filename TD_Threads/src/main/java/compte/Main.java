package compte;

/**
 *
 * @author g53735
 */
public class Main {

    public static void main(String[] args) {
        Compte cpt = new Compte(10000);

        for (int i = 0; i < 20; i++) {
            Operation opes = new Operation(cpt);
            opes.start();
        }
    }
}
