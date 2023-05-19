package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.NewsCategory;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/add-news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null) {
            if(user.getRoleId()==1) {
                int category = Integer.parseInt(request.getParameter("category"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                NewsCategory newsCat = DBConnection.getNewsCategoryById(category);
                if (newsCat != null) {
                    News news = new News();
                    news.setTitle(title);
                    news.setContent(content);
                    news.setStatus(0);
                    news.setNewsCategory(newsCat);
                    DBConnection.addNews(news);
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
