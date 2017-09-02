package Java_2.HW_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Cat extends Animal implements Jumpable, Swimable {

    private double swim_limit;
    private double jump_limit;

    public Cat(String name) {
        this.name = name;
        this.runMeter = 100;//+10*(Math.random()-.5);
        swim_limit = 100;//+10*(Math.random()-.5);
        jump_limit = 3.8;//+2*(Math.random()-.5);
    }

    @Override
    public void finalize(){
        System.out.println(this.getClass()+"  "+this.name +" was exterminate");
    }


    @Override
    public String voice() {

        return "meow-meow-meow";
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
