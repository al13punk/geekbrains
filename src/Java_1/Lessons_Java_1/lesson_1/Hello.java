package Java_1.Lessons_Java_1.lesson_1;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");

        /*int a, c = 65;
        a = 45;
        int b = a + c;
        c++;
        a += 12;*/

        int a;
        int b;

        a = 2;
        b = 3;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
//        System.out.println(c);
        System.out.println("a + b = " + addTwoNumber(a, b));

        if (a > 0 && b == 00) {
            System.out.println("a > 0");
        } else {
            System.out.println("a <= 0");
        }
    }

    private static int addTwoNumber(int num1, int num2) {
        int sum;
        sum = num1 + num2;
        return sum;
    }

}
