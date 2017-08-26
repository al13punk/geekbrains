package Java_1.HW_Java_1.lesson_7;

/**
 * Created by Александр Руденко on 15.08.2017.
 */

/**
 * Расширить задачу про котов и тарелки с едой
 * +2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20)
 * +3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true
 * +4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы)
 * +5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль
 * +6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку
 */

public class HW7 {
    public static void main(String[] args) {

        Plate plate = new Plate(50);
        Cat[] cats = new Cat[10];

        for (int i = 0; i < cats.length; i++) {

            cats[i] = new Cat("cat_" + i, 5 + (int) (Math.random() * 10) + 1);
            cats[i].eat(plate);

            System.out.println(cats[i].getName() + " " + cats[i].isFull() + " " + plate);

            if (!cats[i].isFull() && i != cats.length - 1) {
                int add = 10;
                plate.addFood(add);
                System.out.println("add +" + add + " " + plate);
            }

        }

    }

}

class Cat {

    private String name;
    private boolean full;
    private int appetite;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        full = false;
    }

    void eat(Plate plate) {
        if (plate.isReal(appetite)) {
            plate.decreaseFood(appetite);
            this.full = true;
        }
    }

    public boolean isFull() {
        return full;
    }

    public String getName() {
        return name;
    }

}

class Plate {

    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean isReal(int appetite) {
        return this.food - appetite >= 0;
    }

    @Override
    public String toString() {
        return "Food: " + food;
    }

    void addFood(int addFood) {
        this.food += addFood;
    }

    public void decreaseFood(int appetite) {
        this.food -= appetite;

    }

}
