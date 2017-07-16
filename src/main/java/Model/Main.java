package Model;

import Impl.*;
import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Model.Developer.getDeveloper;
import static Model.Project.getProject;
import static Model.Skill.getSkill;


/**
 * Created by SO on 14.06.2017.
 */
public class Main {
    private static DAO<Developer> developerBasicDao = new DevelopersImpl();
    private static DAO<Skill> skillBasicDao = new SkillsImpl();
    private static DAO<Project> projectBasicDao = new ProjectImpl();
    private static DAO<Company> companyBasicDao = new CompanyImpl();
    private static DAO<Customer> customerBasicDao = new CustomerImpl();


    public static void main(String[] args) {


        Developer developer = new Developer("Michael", "Smirnov", 22, 22222222);
        setDeveloperSkills();
        Project project = new Project("Project5G", 500000000);
        Skill skill = new Skill("Basic");
        Company company = new Company("Softline");
        Customer customer = new Customer("Customer 10", "Belarus");


        create(developer);
        readDeveloperById(developer.getId());
        updateDeveloper(developer.getId());
        deleteDeveloper(developer.getId());


        create(project);
        readProjectById(project.getId());
        updateProject(project.getId());
        deleteProject(project.getId());


        create(skill);
        readSkillById(skill.getId());
        updateSkill(skill.getId());
        deleteSkill(skill.getId());


        create(company);
        readCompanyById(company.getId());
        updateCompany(company.getId());
        deleteCompany(company.getId());


        create(customer);
        readCustomerById(customer.getId());
        updateCustomer(customer.getId());
        deleteCustomer(customer.getId());

        createDeveloperWithSkill(developer);
        createProjectWithDeveloper(project);
    }



    public static Developer setDeveloperSkills() {
        Developer developer = getDeveloper();
        List<Skill> skills = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            skills.add(getSkill());

            developer.setDeveloperSkills(skills);
        }
        return developer;
    }

    public static void createDeveloperWithSkill(Developer developer) {
        for (Skill skill : developer.getDeveloperSkills()) {
            skillBasicDao.create(skill);

        }
        create(developer);
        try (Connection connection = Utils.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO developers_skills (developer_id, skill_id) VALUES (?,?)")) {

            for (Skill skill : developer.getDeveloperSkills()) {
                ps.setInt(1, developer.getId());
                ps.setInt(2, skill.getId());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public  static Project getProjectDevelopers(){
        Project project = getProject();
        List<Developer> developers = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            developers.add(getDeveloper());
            project.setDeveloperProject(developers);
        }
        return project;
    }

    public  static void createProjectWithDeveloper(Project project) {
        for (Developer developer : project.getDeveloperProject()) {
            projectBasicDao.create(project);
        }
        create(project);
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO developers_projects (developer_id,project_id) VALUES (?,?))");
            for (Developer developer : project.getDeveloperProject()) {
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setInt(2, developer.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // CRUD for Developer

    private static void create(Developer developer) {
        System.out.println("=====CREATE======");
        developerBasicDao.create(developer);
        System.out.println("Created:" + developer);
    }


    private static void readDeveloperById(int id) {
        System.out.println("=============READ=============");
        Developer readDeveloper = developerBasicDao.read(id);
        System.out.println(readDeveloper);
    }

    private static void updateDeveloper(int id) {
        System.out.println("======UPDATE=====");
        Developer currentDeveloper = new Developer("Ivan", "Ivanov", 33, 3333);
        currentDeveloper.setId(id);
        Developer newDeveloper = developerBasicDao.update(currentDeveloper);
        System.out.println(newDeveloper);

    }

    private static void deleteDeveloper(int id) {
        System.out.println("======DELETE=====");
        developerBasicDao.delete(id);
        System.out.println("deleted developer with id:" + id);
    }


    //  CRUD for Project
    private static void create(Project project) {
        System.out.println("======CREATE=======");
        projectBasicDao.create(project);
        System.out.println("Created:" + project);

    }

    private static void readProjectById(int id) {
        System.out.println("=============READ=============");
        Project readProject = projectBasicDao.read(id);
        System.out.println(readProject);
    }
        private static void updateProject(int id) {
            System.out.println("======UPDATE=====");
            Project currentProject = new Project("ki",11111);
            currentProject.setId(id);
            Project newProject = projectBasicDao.update(currentProject);
            System.out.println(newProject);

        }
        private static void deleteProject(int id) {
            System.out.println("======DELETE=====");
            projectBasicDao.delete(id);
            System.out.println("deleted project with id:" + id);
        }


    // CRUD for Skills
    private static void create(Skill skill) {
        System.out.println("=====CREATE======");
        skillBasicDao.create(skill);
        System.out.println("Created:" + skill);
    }

    private static void readSkillById(int id) {
        System.out.println("=============READ=============");
        Skill readSkill = skillBasicDao.read(id);
        System.out.println(readSkill);
    }

    private static void updateSkill(int id) {
        System.out.println("======UPDATE=====");
        Skill currentSkill = new Skill("Detch languige");
        currentSkill.setId(id);
        Skill newSkill = skillBasicDao.update(currentSkill);
        System.out.println(newSkill);
    }

    private static void deleteSkill(int id) {
        System.out.println("======DELETE=====");
        skillBasicDao.delete(id);
        System.out.println("deleted skill with id:" + id);
    }


        // CRUD for Company

    private static void create(Company company) {
        System.out.println("=====CREATE======");
        companyBasicDao.create(company);
        System.out.println("Created:" + company);
    }

    private static void readCompanyById(int id) {
        System.out.println("=============READ=============");
        Company readCompany = companyBasicDao.read(id);
        System.out.println(readCompany);
    }

    private static void updateCompany(int id) {
        System.out.println("======UPDATE=====");
        Company currentCompany = new Company("Nokia");
        currentCompany.setId(id);
        Company newCompany = companyBasicDao.update(currentCompany);
        System.out.println(newCompany);
    }

    private static void deleteCompany(int id) {
        System.out.println("======DELETE=====");
        companyBasicDao.delete(id);
        System.out.println("deleted company with id:" + id);
    }

        //CRUD for Customer

    private static void create(Customer customer) {
        System.out.println("=====CREATE======");
        customerBasicDao.create(customer);
        System.out.println("Created:" + customer);
    }

    private static void readCustomerById(int id) {
        System.out.println("=============READ=============");
        Customer readCustomer = customerBasicDao.read(id);
        System.out.println(readCustomer);
    }



    private static void updateCustomer(int id) {
        System.out.println("======UPDATE=====");
        Customer currentCustomer = new Customer("Customer11", "Poland");
        currentCustomer.setId(id);
        Customer newCustomer = customerBasicDao.update(currentCustomer);
        System.out.println(newCustomer);
    }

    private static void deleteCustomer(int id) {
        System.out.println("======DELETE=====");
        customerBasicDao.delete(id);
        System.out.println("deleted customer with id:" + id);
    }





    }































