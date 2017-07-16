package Impl;

import Model.Company;

import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SO on 18.06.2017.
 */
public class CompanyImpl implements  DAO<Company> {

    @Override
    public void create(Company company) {
        try (Connection connection = Utils.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO companies(name) VALUE (?)")) {

            ps.setString(1, company.getName());

            ps.executeUpdate();

            Connection connection1 = Utils.getConnection();
            PreparedStatement ps1 = connection1.prepareStatement(
                    "SELECT id FROM companies WHERE name = ?");
            ps1.setString(1, company.getName());


            ResultSet resultSet = ps1.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                company.setId(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Company read(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM companies WHERE id=? ");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Company company = new Company();

            while (resultSet.next()) {
                int companyID = resultSet.getInt("id");
                String companyName = resultSet.getString("name");
                company.setId(companyID);
                company.setName(companyName);

            }
            return company;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public Company update(Company company) {

        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE companies SET name=? Where id=?");
            preparedStatement.setString(1, company.getName());
            preparedStatement.setInt(2, company.getId());

            int changeRows = preparedStatement.executeUpdate();

            if (changeRows != 0) {
                return read(company.getId());
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException();
        }


    }
    @Override
    public void delete(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM companies WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
