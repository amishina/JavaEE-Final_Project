package kz.bitlab.techorda.db;

import kz.bitlab.techorda.models.Comment;
import kz.bitlab.techorda.models.News;
import kz.bitlab.techorda.models.NewsCategory;
import kz.bitlab.techorda.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/final_project",
                    "root",
                    "Pa$$w0rd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.postDate, n.categoryId, n.title, n.content, c.name " +
                    "FROM news AS n " +
                    "INNER JOIN news_categories AS c ON n.categoryId = c.id " +
                    "WHERE n.status = 0 " +
                    "ORDER BY c.name, n.postDate DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News n = new News();
                n.setId(resultSet.getInt("id"));
                n.setPostDate(resultSet.getTimestamp("postDate"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));

                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
                n.setNewsCategory(newsCategory);

                news.add(n);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static ArrayList<News> getNewsByCatId(int newsCatId){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.postDate, n.categoryId, n.title, n.content, c.name " +
                    "FROM news AS n " +
                    "INNER JOIN news_categories AS c ON n.categoryId = c.id " +
                    "WHERE n.status = 0 AND n.categoryId = ? " +
                    "ORDER BY n.postDate DESC");
            statement.setInt(1,newsCatId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News n = new News();
                n.setId(resultSet.getInt("id"));
                n.setPostDate(resultSet.getTimestamp("postDate"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));

                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
                n.setNewsCategory(newsCategory);

                news.add(n);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(int newsId){
        News news = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.postDate, n.categoryId, n.title, n.content, c.name " +
                    "FROM news AS n " +
                    "INNER JOIN news_categories AS c ON n.categoryId = c.id " +
                    "WHERE n.status = 0 AND n.id = ? " +
                    "ORDER BY n.postDate DESC");
            statement.setInt(1,newsId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                news = new News();
                news.setId(resultSet.getInt("id"));
                news.setPostDate(resultSet.getTimestamp("postDate"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));

                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
                news.setNewsCategory(newsCategory);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static ArrayList<NewsCategory> getNewsCategories(){
        ArrayList<NewsCategory> newsCat = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM news_categories " +
                    "ORDER BY name");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));

                newsCat.add(newsCategory);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCat;
    }

    public static NewsCategory getNewsCategoryById(int newsCatId){
        NewsCategory newsCategory = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM news_categories " +
                    "WHERE id = ?");
            statement.setInt(1,newsCatId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCategory;
    }

    public static User getUser(String email){
        User user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("fullName"));
                user.setRoleId(resultSet.getInt("roleId"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(User user){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, fullName, roleId) " +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserFullname(int id, String fullName){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET fullName = ? " +
                    "WHERE id = ?");
            statement.setString(1,fullName);
            statement.setInt(2,id);

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUserPassword(int id, String password){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET password = ? " +
                    "WHERE id = ?");
            statement.setString(1,password);
            statement.setInt(2,id);

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> getComments(int newsId){
        ArrayList<Comment> comments = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT co.id, co.comment, co.postDate, co.userId, co.newsId, u.fullName " +
                    "FROM comments co " +
                    "INNER JOIN users u ON u.id = co.userId " +
                    "WHERE co.newsId = ? " +
                    "ORDER BY co.postDate DESC");
            statement.setLong(1,newsId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("postDate"));
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("fullName"));
                comment.setUser(user);
                News news = new News();
                news.setId(resultSet.getInt("id"));
                comment.setNews(news);
                comments.add(comment);
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return comments;
    }

    public static void addNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news (title, content, postDate, categoryId, status) " +
                    "VALUES (?, ?, NOW(),?,?)");
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getContent());
            statement.setInt(3,news.getNewsCategory().getId());
            statement.setInt(4,news.getStatus());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET title = ?, content = ? " +
                    "WHERE id = ?");
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getContent());
            statement.setLong(3,news.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comment comment){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (comment, userID, newsId, postDate) " +
                    "VALUES (?, ?, ?, NOW())");
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUser().getId());
            statement.setLong(3, comment.getNews().getId());

            statement.executeUpdate();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addCategory(NewsCategory newsCategory){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news_categories (name) " +
                    "VALUES (?)");
            statement.setString(1,newsCategory.getName());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void hideNews(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET status = 1 " +
                    "WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
