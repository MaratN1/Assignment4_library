package main.Dao;

import main.Models.Reader;
import main.Models.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDao implements IDao<Reader> {
    private final String INSERT_QUERY = "insert into readers(fullname, groupname) values(?, ?)";
    private final String SELECT_QUERY = "select * from readers";
    private final String GET_BUY_FULLNAME = "select * from readers where fullNmae=?";

    @Override
    public List<Reader> select() {
        Context initialContext = null;
        Connection connection = null;
        List<Reader> readers = new ArrayList<>();
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
                    Reader reader = new Reader();
                    reader.setId(resultSet.getInt("id"));
                    reader.setFullName(resultSet.getString("fullName"));
                    reader.setGroup(resultSet.getString("groupname"));




                    readers.add(reader);
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

        return readers;
    }


    @Override
    public boolean insert(Reader item) {

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
                preparedStatement.setString(2, item.getGroup());

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
    public Reader get(int id) {
        return null;
    }
}
