package main.Controllers;

import main.Dao.ReaderDao;
import main.Models.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddReaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/AddReader.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullname");
        String group = req.getParameter("group");

        Reader reader = new Reader();
        reader.setFullName(fullName);
        reader.setGroup(group);

        ReaderDao readerDao = new ReaderDao();

        if (readerDao.insert(reader)) {
            resp.sendError(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}
