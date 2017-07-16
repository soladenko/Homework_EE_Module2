package Impl;

import Model.Project;
import Model.Skill;
import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SO on 18.06.2017.
 */
public class ProjectImpl implements DAO<Project> {

    @Override
    public void create(Project project) {
        try (Connection connection = Utils.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO projects (name,cost) VALUES (?,?)")){

            ps.setString(1, project.getName());
            ps.setInt(2, project.getCost());
            ps.executeUpdate();

            Connection connection1 = Utils.getConnection();
            PreparedStatement ps1 = connection1.prepareStatement(
                    "SELECT id FROM projects WHERE name = ? and cost = ?");
            ps1.setString(1, project.getName());
            ps1.setInt(2, project.getCost());

            ResultSet resultSet = ps1.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                project.setId(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Project read(int id) {
        try (Connection connection = Utils.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Project project = new Project();

            while (resultSet.next()) {
                int projectId = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                int projectCost = resultSet.getInt("cost");
                project.setId(projectId);
                project.setName(projectName);
                project.setCost(projectCost);
            }
            return project;

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public Project update(Project project) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE projects SET name=?,cost = ? Where id=?");

            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCost());
            preparedStatement.setInt(3, project.getId());



            int changeRows = preparedStatement.executeUpdate();

            if (changeRows != 0) {
                return read(project.getId());
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM projects WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
