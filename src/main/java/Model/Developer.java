package Model;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by SO on 14.06.2017.
 */
public class Developer {
    private int id;
    private String name;
    private String surname;
    private int age;
    private int salary;

    private List<Skill> developerSkills = new ArrayList<>();

    public Developer() {
    }

    public Developer(String name, String surname, int age, int salary) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }

    public  static Developer getDeveloper(){
        Developer developer = new Developer();
        developer.setName("Alex");
        developer.setSurname("Roze");
        developer.setAge(22);
        developer.setSalary(10000);

        developer.setName("Andrey");
        developer.setSurname("Minov");
        developer.setAge(35);
        developer.setSalary(2000);
        return developer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Skill> getDeveloperSkills() {
        return developerSkills;
    }

    public void setDeveloperSkills(List<Skill> developerSkills) {
        this.developerSkills = developerSkills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", developerSkills=" + developerSkills +
                '}';
    }
}
