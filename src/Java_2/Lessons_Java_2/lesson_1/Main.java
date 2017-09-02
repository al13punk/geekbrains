package Java_2.Lessons_Java_2.lesson_1;

/**
 * Created by Red Panda on 29.08.2017.
 */

import Java_2.Lessons_Java_2.lesson_1.animals.*;
import Java_2.Lessons_Java_2.lesson_1.obstances.*;

public class Main {
    public static void main(String[] args) {

        Animal[] zoo = {new Cat("Cat"), new Hen("Hen"), new Hippo("Hippo")};
        Track track = new Track(80);
        Wall wall = new Wall(3);
        Water water = new Water(10);

        for (Animal animal : zoo) {
            System.out.println(animal+"say "+animal.voice());
            System.out.println("run "+track.doIt(animal));
            System.out.println("jump "+wall.doIt(animal));
            System.out.println("swim "+water.doIt(animal));
        }
    }
}
