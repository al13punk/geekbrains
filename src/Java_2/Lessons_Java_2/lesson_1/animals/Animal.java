package Java_2.Lessons_Java_2.lesson_1.animals;


public abstract class Animal {
    protected String name;
    protected double runMeter;

    public abstract String voice();

    public boolean run(double meter) {
        return meter <= runMeter ? true : false;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + name;
    }

}

