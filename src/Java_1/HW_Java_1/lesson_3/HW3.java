package Java_1.HW_Java_1.lesson_3;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Александр Руденко on 01.08.2017.
 */

        /*
        1Написать программу,которая загадывает случайное число от 0до 9,
        и пользователю дается 3попытки угадать это число.
        При каждой попытке компьютер должен сообщить больше ли
        указанное пользователем число чем загаданное,или меньше.
        После победы или проигрыша выводится запрос – «Повторить игру еще раз?
        1 – да/0 – нет»(1 – повторить,0 – нет).

        2*Создать массив из слов String[]words={"apple","orange","lemon","banana","apricot","avocado","broccoli","carrot","cherry","garlic","grape","melon","leak","kiwi","mango","mushroom","nut","olive","pea","peanut","pear","pepper","pineapple","pumpkin","potato"};
        При запуске программы компьютер загадывает слово,запрашивает ответ у пользователя,
        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
        Если слово не угадано,компьютер показывает буквы которые стоят на своих местах.
        apple – загаданное apricot-ответ игрока ap############# (15символов,
        чтобы пользователь не мог узнать длину слова)Для сравнения двух слов посимвольно,
        можно пользоваться:String str="apple";str.charAt(0);-метод,вернет char,
        который стоит в слове str на первой позиции Играем до тех пор,пока игрок не
        отгадает слово Используем только маленькие буквы
        */
public class HW3 {

    public static void main(String[] args) {

        gameNumber();
        gameWord();

    }

    /**
     * "1" Написать программу,которая загадывает случайное число от 0до 9,
     * и пользователю дается 3 попытки угадать это число.
     * При каждой попытке компьютер должен сообщить больше ли
     * указанное пользователем число чем загаданное,или меньше.
     * После победы или проигрыша выводится запрос – «Повторить игру еще раз?
     * 1 – да/0 – нет»(1 – повторить,0 – нет).
     */
    public static void gameNumber() {

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            int num = (int) (Math.random() * 10);
//            System.out.println(num);
            System.out.println("загадано случайное число от 0до 9");

            for (int i = 0; i < 3; i++) {
                int tempN = scanner.nextInt();
                if (tempN == num) {
                    System.out.println("    ***Победа!!***");
                    break;
                }
                System.out.println(tempN > num ? "больше" : "меньше");

                System.out.println(i == 2 ? "    ***Поражение!!***" : "");
            }

            System.out.println("*Повторить игру еще раз?*\n    *1 – да/0 – нет*");
            int answer = scanner.nextInt();
            while (!(answer == 0 || answer == 1)) {
                System.out.println(" *1 – да/0 – нет*");
                answer = scanner.nextInt();
            }
            if (answer == 0) {
                break;
            } else {
                System.out.println("Новая игра");
            }
        }

    }

    /**
     * 2*Создать массив из слов String[]words={"apple","orange","lemon","banana","apricot","avocado","broccoli","carrot","cherry","garlic","grape","melon","leak","kiwi","mango","mushroom","nut","olive","pea","peanut","pear","pepper","pineapple","pumpkin","potato"};
     * При запуске программы компьютер загадывает слово,запрашивает ответ у пользователя,
     * сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
     * Если слово не угадано,компьютер показывает буквы которые стоят на своих местах.
     * apple – загаданное apricot-ответ игрока ap############# (15символов,
     * чтобы пользователь не мог узнать длину слова)Для сравнения двух слов посимвольно,
     * можно пользоваться:String str="apple";str.charAt(0);-метод,вернет char,
     * который стоит в слове str на первой позиции Играем до тех пор,пока игрок не
     * отгадает слово Используем только маленькие буквы
     */
    public static void gameWord() {
        String[] words = {"apple", "orange", "lemon", "banana",
                "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak",
                "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple",
                "pumpkin", "potato"};
        System.out.println("загадано случайное слово");
        String word = words[new Random().nextInt(words.length)];
//        System.out.println(word);

        Scanner scanner = new Scanner(System.in);
        String wordTry = "";
        String wordClose = "###############";

        while (!wordTry.equals(word)) {
            wordTry = scanner.next();

            String wordCloseTemp = "";

            for (int i = 0; i < 15; i++) {
                if ((wordClose.charAt(i) == '#') && (i < word.length()) && (i < wordTry.length())) {
                    wordCloseTemp += (word.charAt(i) == wordTry.charAt(i) ? wordTry.charAt(i) : '#');
                } else {
                    wordCloseTemp += wordClose.charAt(i);
                }
            }


            if (word.equals(wordCloseTemp.substring(0, word.length()))) {
                break;
            }

            wordClose = wordCloseTemp;
            System.out.println(wordClose);
            System.out.println("нет");

        }

        System.out.println(word);
        System.out.println("да");
    }

}