package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.NewsCategory;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/add-category")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null) {
            if(user.getRoleId()==1) {
                String categoryName = request.getParameter("categoryName");
                if (categoryName != null) {
                    NewsCategory newsCategory = new NewsCategory();
                    newsCategory.setName(categoryName);
                    DBConnection.addCategory(newsCategory);
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/?noCategoryError");
                }
            }else{
                request.getRequestDispatcher("/403.jsp").forward(request,response);
            }
        }
    }
}
