<%@ page import="kz.bitlab.techorda.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.models.NewsCategory" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    ArrayList<NewsCategory> newsCat = (ArrayList<NewsCategory>) request.getAttribute("newsCat");
%>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-success navbar-dark">
        <div class="container-fluid">
                        <a class="navbar-brand" href="/">
                            <%=siteName%>
                        </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/home">All News</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            By category
                        </a>
                        <div class="dropdown-menu" >
                            <%
                                if(newsCat!=null){
                                    for(NewsCategory n : newsCat) {
                            %>
                                        <a class="dropdown-item" href="/news-by-category?newsCatId=<%=n.getId()%>">
                                            <%=n.getName()%>
                                        </a>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </li>
                    <%
                        if(currentUser!=null){
                            if(currentUser.getRoleId()==1){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="/add-news-page">Add news</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/add-category-page">Add category</a>
                    </li>
                    <%
                        }
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            <%=currentUser.getFullName()%>
                        </a>
                        <div class="dropdown-menu" >
                            <a class="dropdown-item" href="/profile">Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Log out</a>
                        </div>
                    </li>
                    <%
                    }else{
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Log in</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </nav>
</div>
