package threads;

/**
 *
 * @author g53735
 */
public class MyRunnable implements Runnable {

    private final String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10000000; ++j) ;
            System.out.println("MyRunnable: " + name + " : " + i);
        }
    }
}
