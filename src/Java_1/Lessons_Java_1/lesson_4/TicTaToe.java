package Java_1.Lessons_Java_1.lesson_4;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Red Panda on 05.08.2017.
 */

//TicTaToe

public class TicTaToe {

    final int SIZE = 3;
    final char DOT_EMPTY = '.';
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    char[][] map = new char[SIZE][SIZE];

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public static void main(String[] args) {
        new TicTaToe();

    }

    TicTaToe() {
        iniMap();
//        printMap();
        while (true) {
            humanTurn();
//            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("human");
                break;
            }
            if (isMapFull()) {
                System.out.println("sorry");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("ai");
                break;
            }
          /*  if (isMapFull()) {
                System.out.println("sorry");
                break;
            }*/
        }
        printMap();
    }

    void iniMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCellValid(int x, int y) {
        if ((x < 0 || x >= SIZE) || (y < 0 || y >= SIZE)) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    boolean checkWin(char dot) {
        if(map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) return true;
        if(map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) return true;
        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) return true;
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) return true;
        if (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot) return true;
        return false;
    }

    void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("enter x y 1-3");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void aiTurn() {
        int x;
        int y;
        do {

            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;

    }

}
