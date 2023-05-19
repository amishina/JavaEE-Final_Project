package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.models.Comment;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.NewsCategory;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        News news = DBConnection.getNewsById(id);
        request.setAttribute("news",news);
        ArrayList<NewsCategory> newsCat = DBConnection.getNewsCategories();
        request.setAttribute("newsCat",newsCat);

        if(news!=null) {
            ArrayList<Comment> comments = DBConnection.getComments(news.getId());
            request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("/newsdetails.jsp").forward(request,response);
    }
}
