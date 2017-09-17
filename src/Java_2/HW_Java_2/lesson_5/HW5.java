package Java_2.HW_Java_2.lesson_5;

/**
 * Created by Александр Руденко on 15.09.2017.
 * обработка массива через потоки
 */

import java.util.Arrays;

public class HW5 {
    //        static final int size = 40000000;
    static final int size = 4000000;
    static float[] arr = new float[size];


    public static void main(String[] args) {

        Arrays.fill(arr, 1);
        singleStream();
//        showArr();

        Arrays.fill(arr, 1);
        multiStream(2);
//        showArr();

        Arrays.fill(arr, 1);
        multiStream(4);
//        showArr();

        Arrays.fill(arr, 1);
        multiStream(8);
//        showArr();

        Arrays.fill(arr, 1);
        multiStream(16);
//        showArr();

        Arrays.fill(arr, 1);
        multiStream(32);
//        showArr();

        System.out.println("Главный поток завершён...");
    }

    public static void singleStream() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//            System.out.print(arr[i] + " ");
        }
//        System.out.println();

        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("singleStream->" + finishTime);
//        return arr;
    }

    public static void multiStream(int numOfThreads) {
        long startTime = System.currentTimeMillis();
        int tempSize = arr.length / numOfThreads;
        float[][] allTempArr = new float[numOfThreads][];
        MyRunnable[] threads = new MyRunnable[numOfThreads];

        for (int i = 0; i < numOfThreads; i++) {
            allTempArr[i] = new float[tempSize];
            System.arraycopy(arr,  tempSize * i, allTempArr[i], 0, tempSize);
            threads[i] = new MyRunnable("th_" + i, allTempArr[i], i, tempSize);
        }
        try {
            for (MyRunnable thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < threads.length; i++) {
            System.arraycopy(threads[i].getArr(), 0, arr, tempSize * i, threads[i].getArr().length);
        }

        /*float[] tempArr1 = new float[h];
        float[] tempArr2 = new float[h];

        System.arraycopy(arr, 0, tempArr1, 0, h);
        System.arraycopy(arr, h, tempArr2, 0, h);

        MyRunnable thread1 = new MyRunnable("thread_1", tempArr1, 0, h);
        MyRunnable thread2 = new MyRunnable("thread_2", tempArr2, 1, h);

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(thread1.getArr(), 0, arr, 0, thread1.getArr().length);
        System.arraycopy(thread2.getArr(), 0, arr, h, thread2.getArr().length);*/

        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("multi_"+numOfThreads+"->" + finishTime);

    }

    public static void showArr() {
        for (float anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }

}


class MyRunnable extends Thread {
    float[] arr;
    int flag;
    int addNumber;

    MyRunnable(String name, float[] arr, int flag, int addNumber) {
        super(name);
        this.arr = arr;
        this.flag = flag;
        this.addNumber = addNumber;
        start();
    }

    @Override
    public void run() {
        System.out.println("      "+getName() + " is starting");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            int j = i + flag * addNumber;
            this.arr[i] = (float) (arr[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("      "+getName() + " is end "+finishTime);
    }

    public float[] getArr() {
        return arr;
    }
}



