package Java_1.Lessons_Java_1.lesson_7;

/**
 * Created by Red Panda on 15.08.2017.
 */
public class JAVA_1L7 {
    public static void main(String[] args) {
        /*String a = new String("aa");
        String a1 = "java";
        System.out.println();*/
        /*testString();
        testStringBuilder();*/

        Cat cat = new Cat("Barsik", 5);
        Plate plate = new Plate(50);
        System.out.println(plate);

        cat.eat(plate);
        System.out.println(plate);

    }


   /* static void testString() {
        long t1 = System.currentTimeMillis();
        String str = " ";
        for (int i = 0; i < 120000; i++) {
            str += "w";
        }
        System.out.println((System.currentTimeMillis() - t1) + "mc");
    }

    static void testStringBuilder() {
        long t1 = System.currentTimeMillis();
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < 120000; i++) {
            str.append("w");
        }
        System.out.println((System.currentTimeMillis() - t1) + "mc");
    }*/
}

class Cat {
    private String name;

    public int getAppetite() {
        return appetite;
    }

    private int appetite;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    void eat(Plate plate) {
        plate.decreaseFood(appetite);
    }
}

class Plate {


    private int food;

    Plate(int food) {
        this.food = food;
    }


    @Override
    public String toString() {
        return "Food: " + food;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void decreaseFood(int appetite) {
        this.food -= appetite;

    }
}