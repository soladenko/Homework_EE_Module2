package Impl;

import Model.Customer;
import Model.Skill;
import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SO on 18.06.2017.
 */
public class CustomerImpl implements DAO<Customer> {
    @Override
    public void create(Customer customer) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `customers` (`name`, `country`) VALUES (?, ?)");
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getCountry());
            preparedStatement.executeUpdate();

            Connection connection1 = Utils.getConnection();
            PreparedStatement preparedStatement1 = connection1.prepareStatement("SELECT id FROM customers WHERE name = ? AND country = ?");
            preparedStatement1.setString(1,customer.getName());
            preparedStatement1.setString(2,customer.getCountry());

            ResultSet resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                customer.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Customer read(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id=? ");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = new Customer();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerCountry = resultSet.getString("country");
                customer.setId(customerId);
                customer.setName(customerName);
                customer.setCountry(customerCountry);
            }
            return customer;

        } catch (SQLException e) {
            throw  new  RuntimeException();
        }

    }
    @Override
    public Customer update(Customer customer) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET name=?,country =? Where id=?");

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getCountry());
            preparedStatement.setInt(3,customer.getId());


            int changeRows = preparedStatement.executeUpdate();

            if (changeRows != 0) {
                return read(customer.getId());
            }else {
                return null;
            }



        } catch (SQLException e) {
            throw  new RuntimeException();

        }


    }


    @Override
    public void delete(int id) {
        try (Connection connection = Utils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
