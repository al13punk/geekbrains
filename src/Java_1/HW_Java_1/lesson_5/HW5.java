package Java_1.HW_Java_1.lesson_5;

import java.util.Random;

/**
 * Created by Александр Руденко on 08.08.2017.
 */
public class HW5 {
    /**
     * Создать класс "Сотрудник" с полями: +ФИО, +должность, +email, +телефон, +зарплата, возраст;
     * Конструктор класса должен заполнять эти поля при создании объекта;
     * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
     * Создать массив из 5 сотрудников
     * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
     */

    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        String[] lastName = new String[]{"Abramson", "Barrington", "Carrington", "Daniels", "Fitzgerald", "Gate", "Hawkins", "Johnson", "Leapman", "Mathews", "Nelson", "Peterson", "Sherlock"};
        String[] firstName = new String[]{"Aleksandr", "Aleksey", "Zakhar", "Ilya", "Iosif", "Oleg", "Aleksandra", "Alla", "Angelina", "Diana", "Karina", "Lyubov", "Olesya"};
        String[] patronymicName = new String[]{"Feliksovich", "Ivanovich", "Orestovich ", "Serafimovich ", "Yuryevich", "", "", "", "", "", "", "", ""};
        String[] position = new String[]{"Chief Financial Officer", "Chief Technical/Technology Officer", "Chief Information Officer", "Chief Operating Officer", "Chief Compliance Officer", "Chief Security Officer", "Chief Marketing Office", "Chief Data Officer", "Chief Analytics Officer", "Chief Knowledge Officer"};
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int r1, r2, r3;
            r1 = random.nextInt(lastName.length - 1);
            r2 = random.nextInt(firstName.length - 1);
            r3 = random.nextInt(patronymicName.length - 1);
            workers[i] = new Worker(lastName[r1], firstName[r2], patronymicName[r3], position[random.nextInt(position.length - 1)], lastName[r1].substring(0, 2) + firstName[r2].substring(0, 2) + random.nextInt(89) + 10 + "@gmail.com", "+7" + random.nextInt(89999) + 10000, random.nextInt(100000) + 100000, random.nextInt(32) + 18);
        }

        for (Worker w : workers) {
            if (w.getAge() > 40) {
                w.showWokerInfo();
                System.out.println();
            }
        }
    }
}

class Worker {


    private String[] fullName = new String[3];
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    Worker(String lastName, String firstName, String patronymicName, String position, String email, String phone, int salary, int age) {
        fullName[0] = lastName;
        fullName[1] = firstName;
        fullName[2] = patronymicName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;

    }

    Worker(String lastName, String firstName, String position, String email, String phone, int salary, int age) {
        fullName[0] = lastName;
        fullName[1] = firstName;
        fullName[2] = "";
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;

    }

    void showWokerInfo() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("Full Name: ");
        for (int i = 0; i < fullName.length; i++) {
            System.out.print(fullName[i] + " ");
        }
        System.out.println(" age: " + age);
        System.out.print("position: " + position);
        System.out.println("  salary: " + salary);
        System.out.print("email: " + email);
        System.out.println("  phone: " + phone);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void setFullName(String[] fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

}
