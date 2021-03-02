package block;

/**
 *
 * @author g53735
 */
public class MyThread extends Thread {

    private MyObject myObject;

    public MyThread(String name, MyObject myObject) {
        super(name);
        this.myObject = myObject;
    }

    @Override
    public void run() {
        String nom = Thread.currentThread().getName();
        System.out.println("MyThread: " + nom + " in run");
        myObject.fct();
        System.out.println("MyThread: " + nom + " out run");
    }
}
