package Java_2.Lessons_Java_2.lesson_5;

/**
 * Created by Red Panda on 12.09.2017.
 */

//при старте нельзя гарантировать вывод
public class TwoWaysOfThread {
    public static void main(String[] args) {

       /* Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyThread());
        t1.start();
        t2.start();
        new Thread(new MyThread()).start();
        new MyThread().start();
//        new MyRunnable().start();
*/
        MyThread mt1 = new MyThread("mt1");
        MyThread mt2 = new MyThread("mt2");
//        MyThread mt3 = new MyThread("mt3");
        System.out.println(mt1.getName()+" "+mt1.isAlive());
        System.out.println(mt2.getName()+" "+mt2.isAlive());
        int i =0;
        do {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Main ");
            }
            i++;
            System.out.println(i);
        } while (mt1.isAlive() || mt2.isAlive()||(i<10));
        System.out.println(mt1.getName()+" "+mt1.isAlive());
        System.out.println(mt2.getName()+" "+mt2.isAlive());
        try {
            mt1.join();
            mt2.join();
            System.out.println(mt1.getName()+ " is joined");
            System.out.println(mt2.getName()+ " is joined");
        } catch (InterruptedException ex) {
            System.out.println("Main ");
        }
        System.out.println("main однозначно end");
    }
}

class MyRunnable implements Runnable {
    Thread thread;

    MyRunnable(String name) {
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + "is starting");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " count");
            try {
                Thread.sleep(400);
                System.out.println(i + " ");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(thread.getName() + " end");
    }
}


class MyThread extends Thread {
    Thread thread;

    MyThread(String name) {
//        super(name);
//        start();
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " is starting");
        for (int i = 0; i < 10; i++) {
//            System.out.println(i+" count");
            try {
                Thread.sleep(400);
                System.out.println(thread.getName() + " " + i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(thread.getName() + " end");
    }
}