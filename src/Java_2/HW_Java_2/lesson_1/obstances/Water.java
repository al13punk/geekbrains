package Java_2.HW_Java_2.lesson_1.obstances;


import Java_2.HW_Java_2.lesson_1.animals.Animal;
import Java_2.HW_Java_2.lesson_1.animals.Swimable;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Water implements ObstacleDoIt {
    private int height;

    public Water(int height) {
        this.height = height;
    }

    @Override
    public boolean doIt(Animal animal) {
        if (animal instanceof Swimable) {
            return ((Swimable) animal).swim(height);
        }
        return false;
    }
}
