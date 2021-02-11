package view;

/**
 *
 * @author g53735
 */
public class Etudiant {

    private int num;
    private String prenom;
    private String nom;

    public Etudiant(int num, String prenom, String nom) {
        this.num = num;
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getNum() {
        return num;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
