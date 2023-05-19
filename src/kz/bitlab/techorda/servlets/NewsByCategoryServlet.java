package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.NewsCategory;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/news-by-category")
public class NewsByCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        int newsCatId = -1;
        try {
            newsCatId = Integer.parseInt(request.getParameter("newsCatId"));
        }catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<News> news = DBConnection.getNewsByCatId(newsCatId);
        request.setAttribute("newsItems",news);
        ArrayList<NewsCategory> newsCat = DBConnection.getNewsCategories();
        request.setAttribute("newsCat",newsCat);

        request.getRequestDispatcher("/newsByCategory.jsp").forward(request,response);
    }
}
