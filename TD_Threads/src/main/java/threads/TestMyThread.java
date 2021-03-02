package threads;

/**
 *
 * @author g53735
 */
public class TestMyThread {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        MyThread t = new MyThread("one");
        t.start();
        // t.run();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 50000; ++j) ;
            System.out.println("Main Thread: " + i);
        }
    }
}
