package main.Dao;

import main.Models.Book;
import main.Models.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IDao<Book>{
    private final String INSERT_QUERY = "insert into books(name, authorFullName, countOfCopies) values(?, ?, ?)";
    private final String SELECT_QUERY = "select * from books";
    private final String GET_BY_ID_QUERY = "select * from books where id=?";
    private final String CHANGE_BY_ID_QUERY = "update books set name=? , authorFullName=?, countOfCopies=? where id=?";

    public boolean change(int id, Book book){
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
                preparedStatement = connection.prepareStatement(CHANGE_BY_ID_QUERY);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthorFullName());
                preparedStatement.setInt(3,book.getCountOfCopies());
                preparedStatement.setInt(4,id);

                int updated = preparedStatement.executeUpdate();

                if(updated > 0){
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
    public List<Book> select() {
        Context initialContext = null;
        Connection connection = null;
        List<Book> books = new ArrayList<>();
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
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("name"));
                    book.setAuthorFullName(resultSet.getString("authorFullName"));
                    book.setCountOfCopies(resultSet.getInt("countOfCopies"));



                    books.add(book);
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

        return books;
    }


    @Override
    public boolean insert(Book item) {
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
                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getAuthorFullName());
                preparedStatement.setInt(3, item.getCountOfCopies());

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
    public Book get(int id){
        Context initialContext = null;
        Connection connection = null;
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/library_db");
            connection = ds.getConnection();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("name"));
                    book.setAuthorFullName(resultSet.getString("authorFullName"));
                    book.setCountOfCopies(resultSet.getInt("countOfCopies"));
                    return book;
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

        return null;
    }
}
