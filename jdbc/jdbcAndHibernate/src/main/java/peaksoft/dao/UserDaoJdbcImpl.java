package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = " create table if not exists users(" +
                "id serial primary key," +
                "name varchar," +
                "last_name varchar," +
                "age int );";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Successfully created...");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String sql = "drop table if exists users;";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Successfully dropped");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (" +
                "name, last_name,age)" +
                "values (?,?,?);";

        try(Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("user with name "+name+" successfully saved!");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try(Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
                statement.executeUpdate();
            System.out.println("Successfully deleted...");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users;";
        try(Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet result = statement.executeQuery();
            while(result.next()){
                users.add(new User(
                        result.getString("name"),
                                result.getString("last_name"),
                        result.getByte("age")
                ));
            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate users;";
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Successfully cleaned...");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}