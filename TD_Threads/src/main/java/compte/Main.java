package compte;

/**
 *
 * @author g53735
 */
public class Main {

    public static void main(String[] args) {
        Compte cpt = new Compte(10000);
//        Operation ope = new Operation(cpt);
//        Operation ope1 = new Operation(cpt);
//        Operation ope2 = new Operation(cpt);
//        Operation ope3 = new Operation(cpt);
//        Operation ope4 = new Operation(cpt);
//        Operation ope5 = new Operation(cpt);
//        Operation ope6 = new Operation(cpt);
//        Operation ope7 = new Operation(cpt);
//        Operation ope8 = new Operation(cpt);
//        Operation ope9 = new Operation(cpt);
//        Operation ope10 = new Operation(cpt);
//        Operation ope11 = new Operation(cpt);
//        Operation ope12 = new Operation(cpt);
//        Operation ope13 = new Operation(cpt);
//        Operation ope14 = new Operation(cpt);
//        Operation ope15 = new Operation(cpt);
//        Operation ope16 = new Operation(cpt);
//        Operation ope17 = new Operation(cpt);
//        Operation ope18 = new Operation(cpt);
//        Operation ope19 = new Operation(cpt);
//        Operation ope20 = new Operation(cpt);
//        ope.start();
//        ope1.start();
//        ope2.start();
//        ope3.start();
//        ope4.start();
//        ope5.start();
//        ope6.start();
//        ope7.start();
//        ope8.start();
//        ope9.start();
//        ope10.start();
//        ope11.start();
//        ope12.start();
//        ope13.start();
//        ope14.start();
//        ope15.start();
//        ope16.start();
//        ope17.start();
//        ope18.start();
//        ope19.start();
//        ope20.start();

        for (int i = 0; i < 20; i++) {
            Operation opes = new Operation(cpt);
            opes.start();
        }
    }
}
