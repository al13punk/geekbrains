package Java_2.Lessons_Java_2.lesson_1.obstances;

import Java_2.Lessons_Java_2.lesson_1.animals.*;

/**
 * Created by Red Panda on 29.08.2017.
 */
public class Wall {
    private int height;

    public Wall(int lenght) {
        this.height = height;
    }

    public boolean doIt(Animal animal) {
        if (animal instanceof Jumpable) {
            return ((Jumpable) animal).jump(height);
        }
        return false;
    }
}
