package Timer;

/**
 * Classe thread affichant un petit message a intervalle regulier : exemple
 * d’utilisation de la methode Thread.interrupted
 */
public class MyTimerInterrupt extends Thread {

    private int laps;

    public MyTimerInterrupt(int laps) {
        this.laps = laps;
    }

    @Override
    public void run() {
        while (!interrupted()) {  // Pourquoi pas utiliser isInterrupted() ?
            try {
                System.out.println("MyTimer: not interrupted");
                sleep(laps);
            } catch (InterruptedException e) {
                System.out.println("MyTimer: exception " + e);
                return; // essayer avec et sans ce return !
            }
        }
    }
}
