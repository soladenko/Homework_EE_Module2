package Impl;

import Model.Skill;
import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SO on 15.06.2017.
 */
public class SkillsImpl implements DAO<Skill> {


    @Override
    public void create(Skill skill) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skills (name) VALUE (?)");
            preparedStatement.setString(1,skill.getName());

            preparedStatement.executeUpdate();

            Connection connection1 = Utils.getConnection();
            PreparedStatement ps1 = connection1.prepareStatement("SELECT id FROM skills WHERE name = ? ");
            ps1.setString(1,skill.getName());

            ResultSet resultSet = ps1.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                skill.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Skill read(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM skills WHERE id = ? ");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Skill skill = new Skill();

            while (resultSet.next()) {
                int skillId = resultSet.getInt("id");
                String skillname = resultSet.getString("name");
                skill.setId(skillId);
                skill.setName(skillname);
            }
            return skill;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public Skill update(Skill skill) {

        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE skills SET name= ? WHERE id = ?");


            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());

            int changeRow = preparedStatement.executeUpdate();

            if (changeRow != 0) {
                return read(skill.getId());
            }
         else
             return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    @Override
    public void delete(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM 'skills' WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    }

