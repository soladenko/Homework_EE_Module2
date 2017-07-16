package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SO on 15.06.2017.
 */
public class Project {
    private int id;
    private String name;
    private int cost;
    List<Developer> developerProject = new ArrayList<>();



    public Project() {
    }

    public Project(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public static Project getProject(){
        Project project = new Project();
        project.setName("ProjectCompanyHuawey");
        project.setCost(300000000);
        return project;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Developer> getDeveloperProject() {
        return developerProject;
    }

    public void setDeveloperProject(List<Developer> developerProject) {
        this.developerProject = developerProject;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", developerProject=" + developerProject +
                '}';
    }
}






