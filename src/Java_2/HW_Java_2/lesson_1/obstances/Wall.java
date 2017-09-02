package Java_2.HW_Java_2.lesson_1.obstances;

import Java_2.HW_Java_2.lesson_1.animals.Animal;
import Java_2.HW_Java_2.lesson_1.animals.Jumpable;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Wall implements ObstacleDoIt {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean doIt(Animal animal) {
        if (animal instanceof Jumpable) {
            return ((Jumpable) animal).jump(height);
        }
        return false;
    }
}
