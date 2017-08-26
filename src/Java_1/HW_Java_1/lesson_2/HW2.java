package Java_1.HW_Java_1.lesson_2;

/**
 * Created by Александр Руденко on 29.07.2017.
 */

/*
       + 1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1,
        0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;

        +2 Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его
        значениями 0 3 6 9 12 15 18 21;

        +3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6
        умножить на 2;

        +4 Создать квадратный двумерный целочисленный массив (количество строк и столбцов
        одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;

       + 5 ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без
        помощи интернета);

        +6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части
        массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) →
        false, checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив
        не входят.

       + 7 **** Написать метод, которому на вход подается одномерный массив и число n (может быть
        положительным, или отрицательным), при этом метод должен сместить все элементы
        массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными
        массивами.
*/
public class HW2 {

    public static void main(String[] args) {

//1-------------------
        System.out.println("№1");
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < arr1.length; i++) {

            if (arr1[i] == 0) {
                arr1[i] = 1;
            } else if (arr1[i] == 1) {
                arr1[i] = 0;
            }
        }

        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();

//2-------------------
        System.out.println("№2");

        int[] arr2 = new int[8];

        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }

        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();

//3-------------------
        System.out.println("№3");

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i : arr3) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] *= 2;
            }
        }

        for (int i : arr3) {
            System.out.print(i + " ");
        }
        System.out.println();

//4-------------------
        System.out.println("№4");

        int arr4[][];
        int num = 7;
        arr4 = new int[num][num];


        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < arr4.length; i++) {

            arr4[i][i] = 1;
            arr4[arr4.length - i - 1][i] = 1;
        }
        System.out.println();


        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println();
        }

//5-------------------
        System.out.println("№5");

        int[] arr5 = {1, 9, 2, -99, 3, 7, 4, 6, 5, 0, 99, 8};
        for (int i : arr5) {
            System.out.print(i + " ");
        }
        System.out.println();

        int min = arr5[0];
        int max = arr5[0];

        for (int i = 0; i < arr5.length; i++) {
            max = arr5[i] > max ? arr5[i] : max;
            min = arr5[i] < min ? arr5[i] : min;
        }
        System.out.println("max= " + max);
        System.out.println("min= " + min);

//6-------------------
        System.out.println("№6");

        int[] arr6_0 = new int[3];
        int[] arr6_1 = {1, 1, 1, 2, 1};
        int[] arr6_2 = {2, 1, 1, 2, 1, 0};

        System.out.println(checkBalance(arr6_0));
        System.out.println(checkBalance(arr6_1));
        System.out.println(checkBalance(arr6_2));


//7-------------------
        System.out.println("№7");


        int[] arr7 = {0, 1, 2, 3, 4};
        for (int i : arr7) {
            System.out.print(i + " ");
        }
        System.out.println("Массив");

        arr7 = shiftArray(arr7, -3);

        for (int i : arr7) {
            System.out.print(i + " ");
        }
        System.out.println("Массив-3");


        arr7 = shiftArray(arr7, 2);

        for (int i : arr7) {
            System.out.print(i + " ");
        }
        System.out.println("Массив+2");


        arr7 = shiftArray(arr7, 0);

        for (int i : arr7) {
            System.out.print(i + " ");
        }
        System.out.println("Массив+0");
    }

    //6-------------------
    static public boolean checkBalance(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            System.out.print(i + " ");
            sum += i;
        }
        System.out.println();

        if (sum != 0) {
            int Left = 0;
            int Right = 0;
            for (int i = 0; i < arr.length; i++) {
                Left += (i <= (arr.length-1) / 2) ? arr[i] : 0;
                Right += (i > (arr.length-1) / 2) ? arr[i] : 0;

            }
            System.out.println("Left= " + Left + " Right= " + Right);
            if (Right == Left) {
                return true;
            }
        } else {
            System.out.println("пустой");
        }
        return false;
    }

    static public int[] shiftArray(int[] arr, int num) {

        int count = Math.abs(num);
        if (num < 0) {
            for (int j = 0; j < count; j++) {
                int temp = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = temp;
            }
        }
        if (num > 0) {
            for (int j = 0; j < count; j++) {
                int temp = arr[arr.length - 1];

                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = temp;

            }
        }

        return arr;
    }

}
