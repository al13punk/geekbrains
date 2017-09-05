package Java_2.Lessons_Java_2.lesson_3;

import java.util.*;


/**
 * Created by Red Panda on 05.09.2017.
 */
public class Main {
    public static void main(String[] args) {
         ArrayListExample();
        HashSetExample();
        TreeSetExample();
        HashMapExample();
        TreeMapExample();
    }

    //массив
    //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    static void ArrayListExample() {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(1, "C");
        System.out.println(list);
        list.remove("B");
        list.remove(2);
        System.out.println(list);
    }

    //только уникальные значения
    //https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
    static void HashSetExample() {
        Set<String> list = new HashSet<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");
        System.out.println(list);
    }

    //уникальный и отсортированные
    //https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html
    static void TreeSetExample() {
        Set<String> list = new TreeSet<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");
        System.out.println(list);
    }

    //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
    static void HashMapExample() {
        Map<String, String> hm = new HashMap<>();
        hm.put("1", "A");
        hm.put("2", "B");
        hm.put("3", "C");
        hm.put("4", "B");

        Set<Map.Entry<String, String>> set = hm.entrySet();
        for (Map.Entry<String, String> o : set) {
            System.out.println(o.getKey() + ":" + o.getValue());
        }
        System.out.println(hm);
        System.out.println(hm.get("2"));

    }

    //отсортированные по ключу
    //https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
    static void TreeMapExample() {
        Map<String, String> hm = new TreeMap<>();
        hm.put("4", "A");
        hm.put("3", "B");
        hm.put("2", "C");
        hm.put("1", "D");

        Set<Map.Entry<String, String>> set = hm.entrySet();
        for (Map.Entry<String, String> o : set) {
            System.out.println(o.getKey() + ":" + o.getValue());
        }
        System.out.println(hm);
    }
}
