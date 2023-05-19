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
import java.util.ArrayList;

@WebServlet(value = "/add-news-page")
public class AddNewsPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null){
            if(user.getRoleId()==1) {
                ArrayList<News> news = DBConnection.getNews();
                request.setAttribute("news",news);
                ArrayList<NewsCategory> newsCat = DBConnection.getNewsCategories();
                request.setAttribute("newsCat",newsCat);
                request.getRequestDispatcher("/addNews.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/403.jsp").forward(request,response);
                }
        }else{
            response.sendRedirect("/login");
        }
    }
}
