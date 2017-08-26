package Java_1.Lessons_Java_1.lesson_5;

/**
 * Created by Red Panda on 08.08.2017.
 */

public class OOP {
    public static void main(String[] args) {
//        Cat.voice();
       /* Cat cat = new Cat("vasya", "black", 2);
        Cat cat1 = new Cat("vasya1", "black1", 2);
        cat.setAge(-99);
        System.out.println(cat.getName() + " " + cat.color + " " + cat.getAge());
        cat.voice();
        System.out.println(cat1.getName() + " " + cat1.color + " " + cat1.getAge());
        cat1.voice();*/
    }
}

class Cat {
    private String name;
    String color;
    private int age;

    Cat() {
        name = "NaN";
        color = "NaN";
        age = -1;
    }

    Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

     void voice() {
        System.out.println("meow");
    }
}