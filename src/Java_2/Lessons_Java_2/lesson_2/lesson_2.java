package Java_2.Lessons_Java_2.lesson_2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by al13punk on 05.09.2017.
 */
public class lesson_2 {

    public static void main(String[] args)/*throws FileNotFoundException*/  {
        int[] m = new int[5];
        int a = 0;
        int b;
        try {
            getFile();
//            return;
//            m[5] = a;
//            b = 10 / a;
//            div(15);
//            throw new NullPointerException("NPE test");
        } catch (FileNotFoundException | ArithmeticException | ArrayIndexOutOfBoundsException | NullPointerException ex) {
            ex.printStackTrace();
//            System.out.println("division by 0");
        } finally {
            System.out.println("I'm finally");
        }
        System.out.println("Everything is OK.");

    }

    public static int div(int a) {
        return a / 0;
    }

    static void getFile() throws FileNotFoundException {
//        try {
        PrintWriter printWriter = new PrintWriter("file.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
