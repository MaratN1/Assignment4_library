package main.Controllers;

import com.google.gson.Gson;
import main.Dao.BookDao;
import main.Models.Book;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = "";

        for(Cookie cookie : req.getCookies()){
            if(cookie.getName(). equals("bookId")){
                idStr = cookie.getValue();
            }
        }

        int id = Integer.parseInt(idStr);
        System.out.println(id);
        BookDao bookDao = new BookDao();

        Book book = bookDao.get(id);

        if(book != null) {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(book));
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.flush();
        }else{
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);

        }
    }
}
