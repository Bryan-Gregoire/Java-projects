package compte;

/**
 *
 * @author g53735
 */
public class Compte {

    private int somme;

    public Compte(int somme) {
        this.somme = somme;
    }

    public int getSomme() {
        return this.somme;
    }

    public void deposerArgent(int somme) {
        this.somme += somme;
    }

    public void retirerArgent(int somme) {
        if (this.somme > somme) {
            this.somme -= somme;
        }
    }
}
