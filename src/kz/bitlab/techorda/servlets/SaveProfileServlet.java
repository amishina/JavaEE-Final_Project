package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/save-profile")
public class SaveProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        int flag=0;

        try {
            flag = Integer.parseInt(request.getParameter("flag"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(user!=null) {
            if(flag==1) {
                int id = Integer.parseInt(request.getParameter("id"));
                String fullName = request.getParameter("fullName");
                user.setFullName(fullName);
                DBConnection.updateUserFullname(id,fullName);
                response.sendRedirect("profile?FullnameSuccessfullyUpdated");
            }else if(flag==2){
                int id = Integer.parseInt(request.getParameter("id"));
                String password = request.getParameter("password");
                String newPassword = request.getParameter("newPassword");
                String reNewPassword = request.getParameter("reNewPassword");
                if (user.getPassword().equals(password)) {
                    if (newPassword.equals(reNewPassword)) {
                        user.setPassword(newPassword);
                        DBConnection.updateUserPassword(id,newPassword);
                        response.sendRedirect("profile?PasswordSucsessfullyUpdated");
                    } else {
                        response.sendRedirect("/profile?NewPasswordError");
                    }
                } else {
                    response.sendRedirect("/profile?CurrentPasswordError");
                }
            }else{
                response.sendRedirect("/profile?UpdateError");
            }
        }else{
            response.sendRedirect("/login");
        }
    }
}
