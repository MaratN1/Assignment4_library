package main.Controllers;

import main.Dao.UserDao;
import main.Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userFullName = req.getParameter("fullNameInput");
        String userLogin = req.getParameter("loginInput");
        String userPassword = req.getParameter("passwordInput");

        User newUser = new User(userFullName, userLogin, userPassword,1);

        UserDao userDao = new UserDao();

        if (userDao.insert(newUser)) {
            System.out.println("user inserted successfully");
        }else{
            System.out.println("something went wrong");
        }
    }
}
