package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value="/hide-news")
public class HideNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null && user.getRoleId()==1) {
            int id = Integer.parseInt(request.getParameter("id"));
            DBConnection.hideNews(id);
            response.sendRedirect("/");
        }else{
            response.sendRedirect("/login");
        }
    }
}
