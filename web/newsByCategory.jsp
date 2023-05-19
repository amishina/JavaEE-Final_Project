<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.models.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News portal</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container mt-3">
        <%
            ArrayList<News> newsPieces = (ArrayList<News>) request.getAttribute("newsItems");
            if (newsPieces!=null) {
                for(News n: newsPieces) {
        %>
        <div class="p-5 mb-3" style="background-color: #eaeeec">
            <a href="/news-details?id=<%=n.getId()%>">
                <h3><%=n.getTitle()%></h3>
            </a>
            <h3><b><%=n.getNewsCategory().getName()%></b></h3>
            <p><%=n.getContent()%></p>
            <p>
                Posted at <strong><%=n.getPostDate()%></strong>
            </p>
        </div>
        <%
            }
        }else {
        %>
        <h1 class="text-center">No news in this category</h1>
        <%
            }
        %>
    </div>
</body>
</html>
