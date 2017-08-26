package Java_1.HW_Java_1.lesson_6;

/**
 * Created by Александр Руденко on 12.08.2017.
 */


/**
 * +1. Создать классы Собака и Кот с наследованием от класса Животное.
 * +2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
 * В качестве параметра каждому методу передается величина, означающая или длину
 * препятствия (для бега и плавания), или высоту (для прыжков).
 * 3. У каждого животного есть ограничения на действия
 * (бег: кот 200 м., собака 500 м.;
 * прыжок: кот 2 м., собака 0.5 м.;
 * плавание: кот не умеет плавать, собака 10 м.).
 * 4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
 * (Например, dog1.run(150); -> результат: run: true)
 * 5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
 */

public class HW6 {
    public static void main(String[] args) {
        Animal[] animals = {new Dog("Sharik", "white", 5), new Cat("Vasiliy", "black", 5)};

        for (Animal an : animals) {
            System.out.println(an.getClass().getName() + " " + an.run(200) + " " + an.jump(1.5) + " " + an.swim(5));
        }
    }
}

abstract class Animal {
    private String name;
    private String color;
    private int age;
    private double runMeter, jumpMeter, swimMeter;


    Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        if (age < 0) {
            this.age = 0;

        } else {
            this.age = age;
        }
    }

    boolean run(double meter) {
        return meter <= runMeter ? true : false;
    }

    boolean jump(double meter) {
        return meter <= jumpMeter ? true : false;
    }

    boolean swim(double meter) {
        return meter <= swimMeter ? true : false;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + name + " " + color + " " + age + " r=" + String.format("%.0f", runMeter) + "m, j=" + String.format("%.0f", jumpMeter) + "m, s=" + String.format("%.0f", swimMeter) + "m";
    }

    public double getRunMeter() {
        return runMeter;
    }

    public void setRunMeter(double runMeter) {
        this.runMeter = runMeter;
    }

    public double getJumpMeter() {
        return jumpMeter;
    }

    public void setJumpMeter(double jumpMeter) {
        this.jumpMeter = jumpMeter;
    }

    public double getSwimMeter() {
        return swimMeter;
    }

    public void setSwimMeter(double swimMeter) {
        this.swimMeter = swimMeter;
    }

}

class Cat extends Animal {
    Cat(String name, String color, int age) {
        super(name, color, age);
        double ageBonus = ((age >= 5) || (age == 0)) ? 0.5 : 1;
        setRunMeter(200 * (Math.random() + .5) * ageBonus);
        setJumpMeter(2 * (Math.random() + .5) * ageBonus);
    }

    @Override
    boolean swim(double meter) {
        return false;
    }

}

class Dog extends Animal {
    Dog(String name, String color, int age) {
        super(name, color, age);
        double ageBonus = ((age >= 10) || (age == 0)) ? 0.5 : 1;
        setRunMeter(500 * (Math.random() + .5) * ageBonus);
        setJumpMeter(.5 * (Math.random() + .5) * ageBonus);
        setSwimMeter(10 * (Math.random() + .5) * ageBonus);
    }

}
