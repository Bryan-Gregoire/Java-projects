package compte;

import java.util.Random;

/**
 *
 * @author g53735
 */
public class Operation extends Thread {

    private final Compte cpt;
    private final Random rnd;

    public Operation(Compte cpt) {
        this.cpt = cpt;
        rnd = new Random();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10_000) {
            int rand = rnd.nextInt(20);
            synchronized (cpt) {
                cpt.deposerArgent(rand);
                cpt.retirerArgent(rand);
            }
            synchronized (cpt) {
                System.out.println("Solde compte : " + cpt.getSomme());
            }
            i++;
        }
    }
}
