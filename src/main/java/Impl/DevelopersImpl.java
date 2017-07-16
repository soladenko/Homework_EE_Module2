package Impl;

import Model.Developer;
import Model.Skill;
import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DevelopersImpl implements DAO<Developer> {


    @Override
    public void create(Developer developer) {
        try (Connection connection = Utils.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO developers(name, surname, age, salary) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, developer.getName());
            ps.setString(2, developer.getSurname());
            ps.setInt(3, developer.getAge());
            ps.setInt(4, developer.getSalary());

            ps.executeUpdate();

            Connection connection1 = Utils.getConnection();
            PreparedStatement ps1 = connection1.prepareStatement(
                    "SELECT id FROM developers WHERE name = ? AND surname = ?");
            ps1.setString(1, developer.getName());
            ps1.setString(2, developer.getSurname());

            ResultSet resultSet = ps1.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                developer.setId(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public Developer read(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM developers WHERE id=? ");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Developer developer = new Developer();

            while (resultSet.next()) {
                int developerId = resultSet.getInt("id");
                String developerName = resultSet.getString("name");
                String developerSurname = resultSet.getString("surname");
                int developerAge = resultSet.getInt("age");
                int developerSalary = resultSet.getInt("salary");
                developer.setId(developerId);
                developer.setName(developerName);
                developer.setSurname(developerSurname);
                developer.setAge(developerAge);
                developer.setSalary(developerSalary);
            }
            return developer;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    // try with resources for closed connection
    public Developer update(Developer developer) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developers SET name=?,surname=?,age=?,salary=? Where id=?");
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSurname());
            preparedStatement.setInt(3, developer.getAge());
            preparedStatement.setInt(4, developer.getSalary());
            preparedStatement.setInt(5, developer.getId());

            int changeRows = preparedStatement.executeUpdate();

            if (changeRows != 0) {
                return read(developer.getId());
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException();
        }


    }

    @Override
    public void delete(int id) {
        try (Connection connection = Utils.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "DELETE FROM developers WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}