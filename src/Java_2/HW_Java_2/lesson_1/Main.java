package Java_2.HW_Java_2.lesson_1;

/**
 * Created by Red Panda on 29.08.2017.
 */

import Java_2.HW_Java_2.lesson_1.animals.*;
import Java_2.HW_Java_2.lesson_1.obstances.*;

public class Main {
    public static void main(String[] args) {



        Team team = new Team("team1", new Animal[]{new Cat("Cat1"), new Cat("Cat2"), new Hen("Hen"), new Hippo("Hippo")});
        Course course = new Course("course1", new ObstacleDoIt[]{new Track(40), new Wall(2), new Water(100)});

        course.doIt(team);

        System.out.println(team.showTeam());
        System.out.println(team.showWhoCanFinish());

        //дз про сборщик мусора
        for (int i = 0; i <200000 ; i++) {
            new Cat("cat_" + i);
        }
    }
}
