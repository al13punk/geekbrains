package Java_2.HW_Java_2.lesson_1.obstances;

import Java_2.HW_Java_2.lesson_1.animals.Animal;
import Java_2.HW_Java_2.lesson_1.animals.Team;

/**
 * Created by Red Panda on 01.09.2017.
 */
public class Course {
    private String name;
    private ObstacleDoIt[] course;

    public Course(String name, ObstacleDoIt[] obstances) {
        this.name = name;
        this.course = obstances;
    }

    public void doIt(Team team) {
        Animal[] members = team.getTeam();
        for (int i = 0; i < members.length; i++) {
            for (int j = 0; j < course.length; j++) {
                if (!course[j].doIt(members[i])) {
                    team.setFinishCell(i, false);
                    break;
                } else {
                    team.setFinishCell(i, true);
                }
            }
        }
    }
}