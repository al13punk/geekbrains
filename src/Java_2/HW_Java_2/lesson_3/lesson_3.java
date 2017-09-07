package Java_2.HW_Java_2.lesson_3;

/**
 * Created by Red Panda on 06.09.2017.
 */


import java.util.*;


public class lesson_3 {

    public static void main(String[] args) {
//        1.
        String[] words = new String[]{"Создать", "массив", "с", "набором", "слов", "повторяющиеся",
                "слов", "должны", "встречаться", "повторяющиеся",
                "Создать", "массив", "слов", "массив"};
        System.out.println(arrayWords(words));
        System.out.println("\n");

//        2.
        PhoneBook phoneBook = new PhoneBook();

        System.out.println(phoneBook.get("Turner"));//вывод номеров Turner
        phoneBook.add("Turner", "+79123456711");//добавляем номер для Turner
        phoneBook.add("Turner", "+79123456701");//добавляем номер который занят и он не добавляется
        System.out.println(phoneBook.get("Turner"));//вывод номеров Turner
        System.out.println("----------------------");
        System.out.println(phoneBook.getPhoneBook());//вывод книги
        System.out.println("----------------------");
        phoneBook.add("White", "+79123456719"); //добавляем в книгу White с номером
        System.out.println(phoneBook.getPhoneBook());//вывод книги
    }


    /**
     * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     * Посчитать сколько раз встречается каждое слово.
     */
    public static Map arrayWords(String[] array) {

        Map<String, Integer> hm = new HashMap<>();
        for (String s : array) {
            if (hm.containsKey(s)) {
                hm.put(s, hm.get(s) + 1);
            } else {
                hm.put(s, 1);
            }
        }
        return hm;
    }
}

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит
 * в себе список фамилий и телефонных номеров. В этот телефонный справочник
 * с помощью метода add() можно добавлять записи. С помощью метода get() искать
 * номер телефона по фамилии. Следует учесть, что под одной фамилией может быть
 * несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
 * должны выводиться все телефоны.
 */
class PhoneBook {
    private ArrayList<Men> phoneBook = new ArrayList<>();

    PhoneBook() {
        phoneBook.add(new Men("Green", "+79123456701"));
        phoneBook.add(new Men("Walker", "+79123456702"));
        phoneBook.add(new Men("Edwards", "+79123456703"));
        phoneBook.add(new Men("Turner", "+79123456704"));
        phoneBook.add(new Men("Johnson", new String[]{"+79123456705", "+79123456706", "+79123456707"}));
        phoneBook.add(new Men("Williams", new String[]{"+79123456708", "+79123456709"}));

        sortPhoneBook();
    }

    //  добавить номер по фамилии
    public void add(String surname, String number) {
        boolean flag = true;
        for (Men men : phoneBook) {
            if (men.getSURNAME() == surname && isNumberFree(number)) {
                men.addNumber(number);
                flag = false;
                break;
            }

        }
        if (flag) {
            phoneBook.add(new Men(surname, number));
        }

        sortPhoneBook();
    }

    //  получить номер/а  по фамилии
    public String get(String surname) {
        for (Men men : phoneBook) {
            if (men.getSURNAME() == surname) {
                return surname + men.getNumberList();
            }
        }
        return null;
    }

    // свободен ли номер
    public boolean isNumberFree(String newNumber) {
        for (Men men : phoneBook) {
            if (men.getNumberList().contains(newNumber)) {
                return false;
            }
        }
        return true;
    }

    // получить книгу
    public ArrayList<Men> getPhoneBook() {
        return phoneBook;
    }

    public void sortPhoneBook() {
        Collections.sort(phoneBook, new Comparator<Men>() {
            public int compare(Men o1, Men o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
    }
}

class Men {
    private final String SURNAME;
    ArrayList<String> numberList = new ArrayList<>();

    Men(String surname, String number) {
        this.SURNAME = surname;
        numberList.add(number);
    }

    Men(String surname, String[] numbers) {
        this.SURNAME = surname;
        for (String s : numbers) {
            numberList.add(s);
        }

    }

    public void addNumber(String number) {
        numberList.add(number);
    }

    public ArrayList<String> getNumberList() {
        return numberList;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    @Override
    public String toString() {
        return SURNAME + ":" + numberList + "\n";
    }
}