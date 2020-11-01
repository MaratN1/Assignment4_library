package main.Controllers;

import main.Dao.UserDao;
import main.Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login");
        String userPassword  = req.getParameter("password");
        System.out.println("login: " + userLogin);
        System.out.println("password: " + userPassword);
        UserDao userDao = new UserDao();

        User user = userDao.get(userLogin, userPassword);

        if(user != null){
            HttpSession session = req.getSession();
            session.setAttribute("currentUserFullName",user.getFullName());
            resp.sendError(HttpServletResponse.SC_OK);
        }else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);


        }
    }
}
