package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("fullName");

        User user = DBConnection.getUser(email);
        if (user==null) {
            if (password.equals(rePassword)) {
                User createUser = new User();
                createUser.setEmail(email);
                createUser.setPassword(password);
                createUser.setFullName(fullName);
                createUser.setRoleId(2);
                DBConnection.addUser(createUser);
                response.sendRedirect("login?registeredSuccessfully");
            } else {
                response.sendRedirect("/register?passworderror");
            }
        } else {
            response.sendRedirect("/register?emailerror");
        }
    }
}
