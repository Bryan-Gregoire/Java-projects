package deadlock;

/**
 *
 * @author g53735
 */
public class Test {

    public static void main(String[] args) {
        myObject first = new myObject();
        myObject second = new myObject();

        MyThreadA tr = new MyThreadA(first, second);
        MyThreadA tr2 = new MyThreadA(second, first);

        System.out.println("Début étreinte mortelle : ");

        tr.start();
        tr2.start();

        System.out.println("Fin ? ");
    }

}
