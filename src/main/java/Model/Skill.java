package Model;

import java.util.Random;

/**
 * Created by SO on 15.06.2017.
 */
public class Skill {
    private int id;
    private String Name;

    public Skill() {
    }

    public static Skill getSkill (){
        Skill skill = new Skill("France languige" + new Random());
        return  skill;
    }

    public Skill(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Model.Skill{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
