package Java_1.Lessons_Java_1.lesson_6;

/**
 * Created by Red Panda on 12.08.2017.
 */
public class OOPExtends {
    public static void main(String[] args) {
        Cat cat = new Cat("Vasil", "red", 5);
//        System.out.println(cat + " say " + cat.voice());
        Dog dog = new Dog("Sharik", "black", 7);
//        System.out.println(dog + " say " + dog.voice());


        Animal[] animals = {cat, dog};
        for (Animal an : animals) {
            System.out.println(an + " say " + an.voice());

        }
    }
}

class Cat extends Animal {
    Cat(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    String voice() {
        return "mow";
    }
}

class Dog extends Animal {
    Dog(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    String voice() {
        return "bow";
    }


}

abstract class Animal {
    private String name;
    String color;
    private int age;

    Animal() {
        name = "NaN";
        color = "NaN";
        age = -1;
    }

    Animal(String name, String color, int age) {
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

    abstract String voice();

    @Override
    public String toString() {
        return getClass().getName() + " " + name + " " + color + " " + age;
    }
}

