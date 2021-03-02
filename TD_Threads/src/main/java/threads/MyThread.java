/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author g53735
 */
public class MyThread extends Thread {

    private String name;

    public MyThread(String s) {
        this.name = s;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 500000000; ++j) ;
            System.out.println("MyThread: " + name + " : " + i);
        }
    }
}
