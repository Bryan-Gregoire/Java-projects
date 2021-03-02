package threads;

/**
 *
 * @author g53735
 */
public class MyThreadComposition {

    private final Thread thread;

    public MyThreadComposition() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread do some stuff
            }
        });

//        // Or with lambda
//        this.thread = new Thread(() ->{
//            // Do some stuff
//        });
    }

    public Thread getThread() {
        return thread;
    }

    public void start() {
        this.thread.start();
    }
}
