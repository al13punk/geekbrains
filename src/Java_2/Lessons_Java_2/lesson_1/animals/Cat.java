package Java_2.Lessons_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Cat extends Animal implements Jumpable, Swimable {

    private int swim_limit;
    private float jump_limit;

    public Cat(String name) {
        this.name = name;
        this.runMeter = 100;
        swim_limit = 100;
        jump_limit = 3.8f;
    }

    @Override
    public String voice() {

        return "Cat";
    }

    @Override
    public boolean jump(float height) {
        if (height < jump_limit) {
            return true;
        }
        return false;
    }

    @Override
    public boolean swim(float height) {
        if (height < swim_limit) {
            return true;
        }
        return false;
    }
}
