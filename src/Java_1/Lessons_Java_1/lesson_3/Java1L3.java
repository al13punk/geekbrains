package Java_1.Lessons_Java_1.lesson_3;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Red Panda on 01.08.2017.
 */
public class Java1L3 {
    public static void main(String[] args) {

//        calc();


        try {
            FileWriter file = new FileWriter("C:\\Java\\Lessons_Java_1\\src\\geekbrains\\Lessons_Java_1\\lesson_3\\text.txt");
            file.write("\nHello\n\nJava\n");
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            int b;
            FileReader file = new FileReader("C:\\Java\\Lessons_Java_1\\src\\geekbrains\\Lessons_Java_1\\lesson_3\\text.txt");
            while ((b = file.read()) != -1) {
                System.out.print((char) b);
            }
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void calc() {
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            int num1 = scanner.nextInt();
            String operator = scanner.next();
            int num2 = scanner.nextInt();

            switch (operator) {
                case "+":
                    System.out.println("= " + (num1 + num2));
                    break;
                case "-":
                    System.out.println("= " + (num1 - num2));
                    break;
                case "*":
                    System.out.println("= " + (num1 * num2));
                    break;
                case "/":
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero");
                    } else {
                        System.out.println("= " + (num1 / num2));
                    }
                    break;
                default:
                    System.out.println("Error: Undefined operator");
            }
        }
    }
}
