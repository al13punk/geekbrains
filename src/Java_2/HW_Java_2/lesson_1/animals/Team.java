package Java_2.HW_Java_2.lesson_1.animals;

/**
 * Created by Red Panda on 01.09.2017.
 */
public class Team {

    private String name;
    private Animal[] team;
    private boolean[] finish;

    public Animal[] getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public void setFinishCell(int i, boolean finish) {
        this.finish[i] = finish;
    }

    public Team(String name, Animal[] team) {
        if (team.length == 4) {
            this.name = name;
            this.team = team;
            finish = new boolean[team.length];
        } else {
            System.out.println("team size = 4");
        }

    }


    public String showTeam() {
        String s = "team_name " + name + "\n all_members \n";
        for (int i = 0; i < team.length; i++) {
            s += " " + team[i].name + " " + finish[i] + "\n";
        }

        return s;
    }

    public String showWhoCanFinish() {
        String s = "team_name " + name + "/\n" + "finish_members \n";

        for (int i = 0; i < team.length; i++) {
            if (finish[i] == true) {
                s += " " + team[i].name + "\n";
            }
        }


        return s;
    }

}
