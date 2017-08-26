package Java_1.HW_Java_1.lesson_4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Александр Руденко on 05.08.2017.
 */


        /*+1 Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
        +2 Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
        +3 * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
        +4 *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.*/


public class HW4 {
    final char X = 'x';
    final char O = 'o';
    final char EMPTY = '*';
    final int FIELD_SIZE;
    final int WIN_CHAIN;
    char[][] field;
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = -1, num2 = -1;
        do {

            System.out.println("введите размер поля и длинну победной цепи,\n цепь меньше или равна длинне поля");
            try {//проверка что ввели число
                num1 = sc.nextInt();
                num2 = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch Exception!");
                sc.next();
            }
        } while (num1 <= 0 || num2 <= 0 || num1 < num2);
        new  HW4(num1, num2);
    }

    public  HW4(int fieldSize, int winChain) {
        FIELD_SIZE = fieldSize;
        WIN_CHAIN = winChain;
        field = new char[FIELD_SIZE][FIELD_SIZE];
        createField();
        showArr(field);
        int count = 1;

        while (true) {

            System.out.println("----------" + count + "-----------");

//            turnHuman();
            turnAI(X);


            if (checkWin(X)) {
                System.out.println("Победа игрока");
                break;
            }
            if (isFull()) {
                System.out.println("Поле заполнено!! Конец игры");
                break;
            }

            turnAI(O);

            showArr(field);
            if (checkWin(O)) {
                System.out.println("Победа ИИ");
                break;
            }
            if (isFull()) {
                System.out.println("Поле заполнено!! Конец игры");
                break;
            }
            count++;
        }
        showArr(field);

    }

    //создать поле
    void createField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = EMPTY;
            }

        }

    }

    //ход игрока
    void turnHuman() {
        int x = -1;
        int y = -1;

        do {
            System.out.println("x y");
            try {//проверка что ввели число
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch Exception!");
                scanner.next();
            }

        } while (!isEmpty(y, x));
        field[y][x] = X;
    }

    // ход ии
    void turnAI(char symbol) {
        double max = -2;
        int maxX = 0;
        int maxY = 0;
        double[][] importance = new double[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isEmpty(i, j)) {
                    importance[i][j] = checkImportance(i, j, X) + checkImportance(i, j, O);
                } else {
                    importance[i][j] = 0;
                }
                if (importance[i][j] > max) {
                    max = importance[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }
        field[maxX][maxY] = symbol;
        System.out.println("---importance---");
        showArr(importance);
        System.out.println("----");

    }

    //приоретет клеток для ИИ
    double checkImportance(int x, int y, char symbol) {

        //прибавка при возможных финальных шагах
        double winBonus = 0;
        double cornerBonus = 0;
        double max = 0;
        double otherBonus = 0;

        // счетчики точек по осям
        double[] count = new double[4];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        double[] checkLine = new double[4];
        for (int i = 0; i < count.length; i++) {
            checkLine[i] = 1;
        }

        // фалажки что последовательность на оси не окончена
        boolean check[] = new boolean[8];
        for (int i = 0; i < check.length; i++) {
            check[i] = true;
        }


        //избыточный цикл по осям с проверками сколько точек лежит рядом
        for (int k = 1; k < FIELD_SIZE; k++) {
            checkAround(x, y, k, 0, 0, check, count, symbol, 1, 1);
            checkAround(x, y, k, 1, 0, check, count, symbol, -1, -1);
            checkAround(x, y, k, 2, 1, check, count, symbol, -1, 1);
            checkAround(x, y, k, 3, 1, check, count, symbol, 1, -1);
            checkAround(x, y, k, 4, 2, check, count, symbol, 0, 1);
            checkAround(x, y, k, 5, 2, check, count, symbol, 0, -1);
            checkAround(x, y, k, 6, 3, check, count, symbol, 1, 0);
            checkAround(x, y, k, 7, 3, check, count, symbol, -1, 0);
        }

        //маленький дебаф для точек на краю поля
        if (x == 0 || x == FIELD_SIZE - 1 || y == 0 || y == FIELD_SIZE - 1) {
            cornerBonus = -0.1;
        }

        //баф если победная для игрока/ии + поиска максимальной значимости
        for (double d : count) {
            if (d > max) {
                max = d;
            }
            if (d > 0) {
                otherBonus += 0.1 * d;
            }
            /*if (d >= WIN_CHAIN - 2) {
                winBonus += 2;
            }*/
            if (d >= WIN_CHAIN - 1) {
                winBonus += 20;
                if (symbol == O) {
                    winBonus += 30;
                }
            }
        }


        return max + cornerBonus + otherBonus + winBonus;
    }

    //приоретет клетки
    void checkAround(int x, int y, int k, int num, int countNum, boolean[] check, double[] count, char symbol, int dx, int dy) {

        if (check[num] && inField(x + k * dx, y + k * dy) && field[x + k * dx][y + k * dy] == symbol) {
            count[countNum]++;
        } else {
            check[num] = false;
        }
    }

    //-------------проверки финала игры---------------------
    //проверка победы по входящему символу

    boolean checkWin(char symbol) {
        int countX = 0;
        int countY = 0;
        int countXY = 0;
        int countInvXY = 0;

        //избыточный цикл по осям с проверками сколько точек лежит рядом
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == symbol) {
                    countX = 0;
                    countY = 0;
                    countXY = 0;
                    countInvXY = 0;
                    for (int k = 0; k < WIN_CHAIN; k++) {

                        if (inField(i + k, j) && field[i + k][j] == symbol) {
                            countX++;
                        } else {
                            countX = 0;
                        }

                        if (inField(i, j + k) && field[i][j + k] == symbol) {
                            countY++;
                        } else {
                            countY = 0;
                        }

                        if (inField(i + k, j + k) && field[i + k][j + k] == symbol) {
                            countXY++;
                        } else {
                            countXY = 0;
                        }

                        if (inField(i - k, j + k) && field[i - k][j + k] == symbol) {
                            countInvXY++;
                        } else {
                            countInvXY = 0;
                        }

                        if (countX == WIN_CHAIN || countY == WIN_CHAIN || countXY == WIN_CHAIN || countInvXY == WIN_CHAIN) {
                            return true;

                        }

                    }
                }
            }
        }

        return false;
    }

    // поле заполнено и нет свободных точек
    boolean isFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    //-------------для удобства---------------------
    //проверка точка свободна
    boolean isEmpty(int x, int y) {
        if (!inField(x, y) || field[x][y] != EMPTY) {
            return false;
        }
        return true;
    }

    //проверка точка в пределах поля
    boolean inField(int x, int y) {
        if (x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE) {
            return false;
        }
        return true;
    }

    // показать массив char[][]
    void showArr(char[][] arr) {
        System.out.print("   ");
        for (int i = 1; i <= FIELD_SIZE; i++) {
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.print("   ");

        for (int i = 1; i <= FIELD_SIZE; i++) {
            System.out.print("_ ");
        }
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(i+"| ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // показать массив int[][]
    void showArr(double[][] arr) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}
