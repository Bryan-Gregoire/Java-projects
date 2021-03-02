package deadlock;

/**
 *
 * @author g53735
 */
public class MyThreadA extends Thread {
    
    myObject firstObj;
    myObject secondObj;

    public MyThreadA(myObject a, myObject b) {
        this.firstObj = a;
        this.secondObj = b;
    }

    @Override
    public void run() {
        System.out.println("Début du thread : ");
        synchronized (firstObj) {
            System.out.println("Lock first object");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            synchronized (secondObj) {
                System.out.println("first object hold second object");
            }
        }
    }
}
