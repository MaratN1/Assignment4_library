package main.Controllers;

import com.google.gson.Gson;
import main.Dao.BookDao;
import main.Dao.ReaderDao;
import main.Models.Book;
import main.Models.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BooksResourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao= new BookDao();
        List<Book> bookList = bookDao.select();

        Gson gson = new Gson();

        resp.getWriter().write(gson.toJson(bookList));

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.flush();
    }
}
