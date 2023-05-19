package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.models.Comment;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.User;

import java.io.IOException;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null) {
            String commentText = request.getParameter("comment");
            int newsId = Integer.parseInt(request.getParameter("newsId"));

            News news = DBConnection.getNewsById(newsId);
            if(news!=null){
                Comment comment = new Comment();
                comment.setNews(news);
                comment.setUser(user);
                comment.setComment(commentText);
                DBConnection.addComment(comment);
                response.sendRedirect("/news-details?id="+newsId);
            }else {
                response.sendRedirect("/");
            }
        }else{
            response.sendRedirect("/login");
        }
    }

}
