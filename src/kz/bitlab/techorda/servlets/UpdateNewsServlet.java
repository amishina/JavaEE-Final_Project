package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/save-news")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null && user.getRoleId()==1) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String category = request.getParameter("category");
            int id = Integer.parseInt(request.getParameter("id"));

            News news = DBConnection.getNewsById(id);
            if (news != null) {
                news.setTitle(title);
                news.setContent(content);
                DBConnection.updateNews(news);
                response.sendRedirect("/news-details?id=" + id);
            } else {
                response.sendRedirect("/");
            }
        }
    }

}
