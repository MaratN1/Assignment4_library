package main.Dao;

import main.Models.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.tools.ForwardingJavaFileManager;
import java.sql.*;
import java.util.List;

public class UserDao implements IDao<User> {
    private final String INSERT_QUERY = "insert into users(fullname, login, password, role) values(?, ?, ?, ?)";

    @Override
    public List<User> select() {
        return null;
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
}
