package Java_1.HW_Java_1.lesson_5;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Александр Руденко on 09.08.2017.
 */
public class HW5_1 {

    static Player[] players;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int sizeField = -1, sizeWinChain = -1, playerFlag = -1, countAI = -1;
        // строка для теста
//        int sizeField = 3, sizeWinChain = 3, playerFlag = 0, countAI = 2;
        do {
            System.out.println("введите размер поля +A+, длинну победной цепи +B+,будет ли игрок +C+ (1-да/0-нет) и колличество ии +D+ \n цепь меньше или равна длинне поля");
            try {//проверка что ввели число
//                System.out.println("введите размер поля");
                sizeField = sc.nextInt();
//                System.out.println("введите длинну победной цепи \n цепь меньше или равна длинне поля");
                sizeWinChain = sc.nextInt();
//                System.out.println("введите будет ли игрок  \n (1-да/0-нет)");
                playerFlag = sc.nextInt();
//                System.out.println("введите колличество ии");
                countAI = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch Exception!");
                sc.next();
            }
        }
        while (sizeField <= 0 || sizeWinChain <= 0 || sizeField < sizeWinChain || countAI <= 0 || playerFlag < 0 || playerFlag > 1);
        new HW5_1(sizeField, sizeWinChain, playerFlag, countAI);


    }

    HW5_1(int sizeField, int sizeWinChain, int playerFlag, int countAI) {
        Field f = new Field(sizeField, sizeWinChain);
        players = new Player[countAI + playerFlag];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i, ("Player" + (char) (65 + i)), (char) (65 + i), f);
        }
        for (Player p : players) {
            System.out.println(p.getName());
        }
        f.showField();
        game(f, players, playerFlag);

    }

    static Player[] getPlayers() {
        return players;
    }

    static int getPlayersSIze() {
        return players.length;
    }

    void game(Field f, Player[] players, int playerFlag) {
        boolean a = true;
        int count = 1;
        while (a) {
            System.out.println("---------" + count + "---------");
            for (int i = 0; i < players.length; i++) {

                players[i].turnPlayer(playerFlag);
                f.showField();

                if (f.fieldIsFull()) {
                    System.out.println("Field Is Full");
                    a = false;
                    break;
                }
                if (f.checkWin(players[i].getSymbol())) {
                    System.out.println(players[i].getName() + " is won!!");
                    a = false;
                    break;
                }
            }
            count++;
        }
    }


}

class Cell {
    private char symbol; //что будет хранится в ячейке

    //создание ячейки и заполнение символом
    Cell(char symbol) {
        this.symbol = symbol;
    }

    //установить новый символ
    void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    //получить символ
    char getSymbol() {
        return symbol;
    }

    //показать символ
    void showCell() {
        System.out.print(symbol);
    }

}

class Field {
    Cell[][] field; //карта игры
    private final char EMPTY_SYMBOL = '*';// дефолтный пустой символ
    private final int size;// размер поля
    private final int winChain;// размер победной цери

    //проверка победы на поле по символу
    boolean checkWin(char symbol) {
        //счечики по осям относительно точки
        int countX = 0;
        int countY = 0;
        int countXY = 0;
        int countInvXY = 0;

        //избыточный цикл по осям с проверками сколько точек лежит рядом
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j].getSymbol() == symbol) {
                    countX = 0;
                    countY = 0;
                    countXY = 0;
                    countInvXY = 0;
                    for (int k = 0; k < winChain; k++) {

                        if (cellInField(i + k, j) && field[i + k][j].getSymbol() == symbol) {
                            countX++;
                        } else {
                            countX = 0;
                        }

                        if (cellInField(i, j + k) && field[i][j + k].getSymbol() == symbol) {
                            countY++;
                        } else {
                            countY = 0;
                        }

                        if (cellInField(i + k, j + k) && field[i + k][j + k].getSymbol() == symbol) {
                            countXY++;
                        } else {
                            countXY = 0;
                        }

                        if (cellInField(i - k, j + k) && field[i - k][j + k].getSymbol() == symbol) {
                            countInvXY++;
                        } else {
                            countInvXY = 0;
                        }
                        // если хоть один счетчик равеш или больше победной цепи то победа
                        if (countX == winChain || countY == winChain || countXY == winChain || countInvXY == winChain) {
                            return true;

                        }

                    }
                }
            }
        }

        return false;
    }

    //создаем поле и заполняем пустыми символами, победную длинну
    Field(int size, int chain) {
        this.size = size;
        winChain = chain;
        field = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[j][i] = new Cell(EMPTY_SYMBOL);
            }
        }
    }

    //проверка что в поле нет пустых символов
    boolean fieldIsFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j].getSymbol() == EMPTY_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    //проверка символа на совпадение
    boolean cellIsEmpty(int i, int j, char symbol1) {

        if (field[i][j].getSymbol() == symbol1) {
            return true;
        }
        return false;
    }

    //проверка что координаты попадают в перделы поля
    boolean cellInField(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        return true;
    }

    //печать поля в консоль
    void showField() {
        System.out.print("   ");

        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("   ");

        for (int i = 1; i <= size; i++) {
            System.out.print("_ ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "| ");
            for (int j = 0; j < size; j++) {
                field[i][j].showCell();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //получить поле
    Cell[][] getField() {
        return field;
    }

    //получить символ
    char getEmptySymbol() {
        return EMPTY_SYMBOL;
    }

    //получить символ
    int getSize() {
        return size;
    }

    //получить длинну победной цепи
    int getWinChain() {
        return winChain;
    }

}

class Player {
    private int flag; //флажок игрока 0 игрок остальные ии
    private String name; // имя игрока
    private char symbol; // символ игрока
    private Field map; // карта на которой играет игрок
    Scanner sc = new Scanner(System.in);

    // создаем игрока: флаг, имя, символ, поле.
    Player(int flag, String name, char symbol, Field field) {
        this.flag = flag;
        map = field;
        this.name = name;
        this.symbol = symbol;
    }

    //получить имя игрока
    String getName() {
        return name;
    }

    //получить symbol
    char getSymbol() {
        return symbol;
    }


    // выбор игрок(flag == 0)/ии(flag != 0) и игра с играком да(playerFlag == 1)/нет(playerFlag != 0)
    void turnPlayer(int playerFlag) {
        if (flag == 0 && playerFlag == 1) {
            turnHuman();
//            System.out.println("игрок");
        } else {
            turnAI();
//            System.out.println("ии");

        }
    }

    // ход игрока
    void turnHuman() {
        int x = -1;
        int y = -1;

        // цикл пока не будут введены верные координаты
        while (true) {
            System.out.println("x y");
            try {//проверка что ввели число
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch Exception!");
                sc.next();
            }

            if (map.cellInField(y, x)) {
                if (map.cellIsEmpty(y, x, map.getEmptySymbol())) {
                    break;
                }
            }

        }
        // ход игрока
        map.field[y][x].setSymbol(symbol);
    }

    //ход ии
    void turnAI() {
        double max = -99;
        int maxX = 0;
        int maxY = 0;
        //массив важности точек
        double[][] importance = new double[map.getSize()][map.getSize()];

        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                //точка в поле и пустая
                if (map.cellInField(j, i) && map.cellIsEmpty(j, i, map.getEmptySymbol())) {
                    for (int k = 0; k < HW5_1.getPlayersSIze(); k++) {
                        //суммируем важность точки х, у, символ
                        importance[j][i] += checkImportance(j, i, HW5_1.players[k].symbol);
                    }
                } else {
                    //точка не в полк или занята
                    importance[j][i] = 0;
                }

                if (importance[j][i] > max) {
                    max = importance[j][i];
                    maxX = j;
                    maxY = i;
                }
            }
        }

        //криво написанный выбор рандомной точки хода при равных
        int countOfChance = 0;
        Random random = new Random();
        for (int i = 0; i < importance.length; i++) {
            for (int j = 0; j < importance.length; j++) {
                if (importance[i][j] == max) {
                    countOfChance++;
                }
            }

        }

        System.out.println(countOfChance);
        if (countOfChance != 0) {
            int number = random.nextInt(countOfChance);
            int count = 0;
            for (int i = 0; i < importance.length; i++) {
                for (int j = 0; j < importance.length; j++) {
                    if (importance[j][i] == max) {
                        count++;
                        if (number == count) {
                            maxX = j;
                            maxY = i;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("+++" + maxX + " " + maxY + "+++");
        map.field[maxX][maxY].setSymbol(symbol);

        //таблица решения
       /* System.out.println("---importance---");
        for (int i = 0; i < importance.length; i++) {
            for (int j = 0; j < importance.length; j++) {
                System.out.print(importance[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----");*/
    }

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
        for (int k = 1; k < map.getSize(); k++) {
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
        if (x == 0 || x == map.getSize() - 1 || y == 0 || y == map.getSize() - 1) {
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
            if (d >= map.getWinChain() - 1) {
                winBonus += 20;
                if (this.symbol == symbol) {
                    winBonus += 9990;
                }
            }
        }
        return max + cornerBonus + otherBonus + winBonus;
    }

    void checkAround(int x, int y, int k, int num, int countNum, boolean[] check, double[] count, char symbol, int dx, int dy) {
        //флаг что эта ось ещё работает,  точка в поле и символ равен символу игрока
        if (map.cellInField(x + k * dx, y + k * dy)) {
            if (check[num] && map.field[x + k * dx][y + k * dy].getSymbol() == symbol) {
                count[countNum]++;
            } else {
                check[num] = false;
            }
            //тут нужно дописать про пустые и их счетчик
        } else {
            check[num] = false;
        }
    }

}