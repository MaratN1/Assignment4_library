package main.Dao;

import main.Models.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.tools.ForwardingJavaFileManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IDao<User> {
    private final String INSERT_QUERY = "insert into users(fullName, login, password, role) values(?, ?, ?, ?)";
    private final String SELECT_QUERY = "select * from users";
    private final String GET_BY_LOGIN_PASSWORD = "select * from users where login=? and password=?";
    @Override
    public List<User> select() {

        Context initialContext = null;
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/library_db");
            connection = ds.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
                while (resultSet.next())
                {
                   User user = new User();
                   user.setId(resultSet.getInt("id"));
                   user.setFullName(resultSet.getString("fullName"));
                   user.setLogin(resultSet.getString("login"));
                   user.setPassword(resultSet.getString("password"));
                   user.setRole(resultSet.getInt("role"));


                   users.add(user);
                }
                resultSet.close();
                connection.close();
                statement.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean insert(User item) {
        Context initialContext = null;
        Connection connection = null;

        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/library_db");
            connection = ds.getConnection();

            try {
                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement(INSERT_QUERY);
                preparedStatement.setString(1, item.getFullName());
                preparedStatement.setString(2, item.getLogin());
                preparedStatement.setString(3, item.getPassword());
                preparedStatement.setInt(4, item.getRole());

                int inserted = preparedStatement.executeUpdate();

                if(inserted > 0){
                    return true;
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    public User get(String login, String password ) {
        Context initialContext = null;
        Connection connection = null;
        User user = new User();
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/library_db");
            connection = ds.getConnection();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN_PASSWORD);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    user.setId(resultSet.getInt("id"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role"));
                    break;
                }
                resultSet.close();
                connection.close();
                preparedStatement.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("userFullName: " + user.getFullName());
        return user;
    }
}
