package Java_2.HW_Java_2.lesson_1.obstances;

/**
 * Created by Red Panda on 29.08.2017.
 */

import Java_2.HW_Java_2.lesson_1.animals.Animal;

public class Track implements ObstacleDoIt {
    private int lenght;

    public Track(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public boolean doIt(Animal animal) {
        return animal.run(lenght);
    }

}
