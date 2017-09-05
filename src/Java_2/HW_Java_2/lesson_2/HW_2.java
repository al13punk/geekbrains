package Java_2.HW_Java_2.lesson_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Red Panda on 04.09.2017.
 */

public class HW_2 {
    static int sum = 0;

    public static void main(String[] args) {
        String[][] testArray = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "22", "23", //"24"",
                        "s"// то что выдает ArrayIndexOutOfBoundsException(MyArraySizeException) и ClassCastException(MyArrayDataException)
                },
                {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}
        };


        /**
         * 3. В методе main() вызвать полученный метод, обработать возможные исключения, и вывести результат расчета.
         */
        try {
            // так и не придумал как прочитать массив из файла в соответствии с его размерами
            testArray = readFile("D:\\\\array.txt");//массив из файла
            checkArray(testArray);
        } catch (MyArraySizeException | MyArrayDataException ex) {
//            ex.printStackTrace();
            System.out.println(ex.toString());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            System.out.println(sum);
        }

        System.out.println("Everything is OK.");
    }

    public static void checkArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        /**
         * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
         * при подаче массива другого размера необходимо бросить исключение ArrayIndexOutOfBoundsException.
         */
       /* if (array.length == 4) {//проверка колличества массивов в массиве
            for (int i = 0; i < 4; i++) {
                if (array[i].length != 4) {//проверка колличества элементов в массивах
                    throw new MyArraySizeException();
                }
            }
        } else {
            throw new MyArraySizeException();
        }*/
        /**
         * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
         * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
         * текст вместо числа), должно быть брошено соответствующее исключение, с детализацией в какой именно
         * ячейке лежат неверные данные.
         */
        sum = 0;
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("array[" + i + "][" + j + "]= " + array[i][j]);
                }
            }
        }
    }

    /**
     * 4* Задание повышенной сложности: двухмерный массив необходимо прочитать из текстового файла, вместо стандартных
     * создать и использовать свои исключения (MyArraySizeException и MyArrayDataException).
     */
    public static String[][] readFile(String way) throws MyArraySizeException, FileNotFoundException {
        String[][] array = new String[4][4];
        Scanner scanner = new Scanner(new File(way));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (scanner.hasNext()) {
                    array[i][j] = scanner.next();
                    if (i == 3 && j == 3 && scanner.hasNext()) {
                        throw new MyArraySizeException();
                    }
                }
            }
        }
        scanner.close();
        return array;
    }
}


class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    String result = "*** I'm is MyArraySizeException ***";

    @Override
    public String toString() {
        return result;
    }
}

class MyArrayDataException extends ClassCastException {
    String result = "*** I'm is MyArrayDataException ***";

    public MyArrayDataException(String s) {
        result += " " + s;
    }

    @Override
    public String toString() {
        return result;
    }
}