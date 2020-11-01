package main.Controllers;

import main.Dao.BookDao;
import main.Models.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/AddBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");;
        String authorFullName = req.getParameter("authorFullName");
        String countOfCopiesStr = req.getParameter("countOfCopies");

        int countOfCopies = Integer.parseInt(countOfCopiesStr);

        Book book = new Book();
        book.setName(name);
        book.setAuthorFullName(authorFullName);
        book.setCountOfCopies(countOfCopies);

        BookDao bookdao = new BookDao();
        if(bookdao.insert(book)){
            resp.sendError(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}
