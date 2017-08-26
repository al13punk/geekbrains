package Java_1.Lessons_Java_1.lesson_1;

/*
        +1. Создать пустой проект в IntelliJ IDEA и прописать метод main();

        +2. Создать переменные всех пройденных типов данных,
         и инициализировать их значения;

       + 3. Написать метод вычисляющий выражение a * (b + (c / d))
         и возвращающий результат, где a, b, c, d – входные параметры этого метода;

       + 4. Написать метод, принимающий на вход два числа, и проверяющий
        что их сумма лежит в пределах от 10 до 20(включительно), если да
         – вернуть true, в противном случае – false;

        +5. Написать метод, которому в качестве параметра передается целое
         число, метод должен напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.

        +6. Написать метод, которому в качестве параметра передается целое
         число, метод должен вернуть true, если число отрицательное;

        +7. Написать метод, которому в качестве параметра передается строка,
         обозначающая имя, метод должен вывести в консоль сообщение «Привет,
         указанное_имя!»;

        +8. * Написать метод, который определяет является ли год високосным,
        и выводит сообщение в консоль. Каждый 4-й год является високосным,
        кроме каждого 100-го, при этом каждый 400-й – високосный.
*/
public class HW1 {
    public static void main(String[] args) {

        byte Byte;
        short Short;
        int Int;
        long Long;
        float Float;
        double Double;
        boolean Boolean;
        char Char;
        String s;


        Byte = -128;
        Short = -32768;
        Int = -2147483648;
        Long = -9223372036854775808L;
        Float = 120.0f;
        Double = 120.0;
        Boolean = true;
        Char = '0';
        s = "Hello World";

//        System.out.println(multiplyAddDivide(0, 0, 0, 0));

        System.out.println(between10To20(-2147483648, -2147483628));
//        System.out.println(between10To20(-9223372036854775808L, -9223372036854775808L));
//        System.out.println(between10To20(-1.0E308, -1.0E308));

//        positiveOrNegative(-0);
//
//        System.out.println(isNegative(0));
//
//        helloString("Red Panda");
//
//        isLeapYear(0);


    }

    static String multiplyAddDivide(int a, int b, int c, int d) {

        int result;
        if (d != 0) {
            result = a * (b + (c / d));
            return Integer.toString(result);
        }

        return "divide by zero";


    }


    static boolean between10To20(int num1, int num2) {
        int sum;

        sum = num1 + num2;

        if (sum >= 10 && sum <= 20) {
            System.out.println(sum);

            return true;
        }

        return false;
    }


    static void positiveOrNegative(int num) {
        if (num >= 0) {
            System.out.println(num + " Positive");
        } else {
            System.out.println(num + " Negative");
        }
    }

    static boolean isNegative(int num) {
        if (num < 0) {
            return true;
        }
        return false;
    }

    static void helloString(String name) {
        System.out.println("Привет, " + name + "!");
    }

    static void isLeapYear(int year) {
        if (year != 0 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {

            System.out.println(year + " - leap-year");
        } else {
            System.out.println(year + " - common year");
        }
    }

}
