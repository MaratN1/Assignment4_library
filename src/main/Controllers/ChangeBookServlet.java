package main.Controllers;

import main.Dao.BookDao;
import main.Models.Book;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);


        Cookie cookie = new Cookie("bookId", idStr);
        resp.addCookie(cookie);

        req.getRequestDispatcher("jsp/ChangeBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = "";

        for(Cookie cookie : req.getCookies()){
            if(cookie.getName(). equals("bookId")){
                idStr = cookie.getValue();
            }
        }

        int id = Integer.parseInt(idStr);

       String newName = req.getParameter("newName");
       String newAuthorFullName = req.getParameter("newAuthorFullName");
       String newCountOfCopiesStr = req.getParameter("newCountOfCopies");

       int newCountOfCopies = Integer.parseInt(newCountOfCopiesStr);
        Book book = new Book(newName, newAuthorFullName, newCountOfCopies);
        BookDao bookDao = new BookDao();
        if(bookDao.change(id, book)) {
            resp.sendError(HttpServletResponse.SC_OK);
        }else{
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
