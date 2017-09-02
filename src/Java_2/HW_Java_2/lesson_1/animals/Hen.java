package Java_2.HW_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 29.08.2017.
 */

public class Hen extends Animal implements Jumpable {

    private double jump_limit;

    public Hen(String name) {
        this.name = name;
        this.runMeter = 100;//+10*(Math.random()-.5);
        jump_limit = 10;//+3*(Math.random()-.5);
    }

    @Override
    public String voice() {

        return "ko-ko-ko";
    }

    @Override
    public boolean jump(float height) {
        if (height < jump_limit) {
            return true;
        }
        return false;
    }
}
