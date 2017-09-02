package Java_2.Lessons_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 29.08.2017.
 */

public class Hen extends Animal implements Jumpable {

    private int swim_limit;
    private float jump_limit;

    public Hen(String name) {
        this.name = name;
        this.runMeter = 100;
        swim_limit = 100;
        jump_limit = 3.8f;
    }

    @Override
    public String voice() {

        return "Hen";
    }

    @Override
    public boolean jump(float height) {
        if (height < jump_limit) {
            return true;
        }
        return false;
    }
}
