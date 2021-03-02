package threads;

/**
 *
 * @author g53735
 */
public class TestMyRunnable {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable("one");
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10_000_000; ++j) ;
            System.out.println("TestMyRunnable: " + i);
        }
    }
}
