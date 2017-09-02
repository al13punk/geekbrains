package Java_2.HW_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Hippo extends Animal implements Swimable {

    private double swim_limit;

    public Hippo(String name) {
        this.name = name;
        this.runMeter = 50;//+5*(Math.random()-.5);
        swim_limit = 200;//+20*(Math.random()-.5);
    }

    @Override
    public String voice() {

        return "uf-uf-uf";
    }

    @Override
    public boolean swim(float height) {
        if (height < swim_limit) {
            return true;
        }
        return false;
    }
}
