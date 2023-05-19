<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.techorda.models.News" %>
<%@ page import="kz.bitlab.techorda.models.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <%@include file="head.jsp"%>
</head>
    <body>
    <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <div class="row mt-3">
                <div class="col-12">
                    <%
                        News news =(News) request.getAttribute("news");
                        if(news!=null){
                    %>
                            <div class="p-5 mb-3" style="background-color: #eaeeec">
                                <h3><%=news.getTitle()%></h3>
                                <h3><b><%=news.getNewsCategory().getName()%></b></h3>
                                <p><%=news.getContent()%></p>
                                <p>
                                    Posted at <strong><%=news.getPostDate()%></strong>
                                </p>
                                <%
                                    if(currentUser!=null && currentUser.getRoleId()==1) {
                                %>
                                <div>
                                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                            data-bs-target="#editNews">
                                        Edit news
                                    </button>
                                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                            data-bs-target="#hideNews">
                                        Hide news
                                    </button>
                                    <!--Update news -->
                                    <div class="modal fade" id="editNews" data-bs-backdrop="static"
                                         data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <form action="/save-news" method="post">
                                                    <input type="hidden" name="id" value="<%=news.getId()%>">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">
                                                            Edit news
                                                        </h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <label>
                                                                    CATEGORY:
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="row mt-2">
                                                            <div class="col-12">
                                                                <input type="text" class="form-control" name="category"
                                                                       readonly
                                                                       value="<%=news.getNewsCategory().getName()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <label>
                                                                    TITLE:
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="row mt-2">
                                                            <div class="col-12">
                                                                <input type="text" class="form-control" name="title"
                                                                       required value="<%=news.getTitle()%>">
                                                            </div>
                                                        </div>
                                                        <div class="row mt-3">
                                                            <div class="col-12">
                                                                <label>
                                                                    CONTENT:
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="row mt-2">
                                                            <div class="col-12">
                                                                <textarea class="form-control" name="content"
                                                                          required
                                                                          rows="10"><%=news.getContent()%></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Cancel</button>
                                                        <button class="btn btn-success">
                                                            Update news
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Hide news -->
                                    <div class="modal fade" id="hideNews" data-bs-backdrop="static"
                                         data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <form action="/hide-news" method="post">
                                                    <input type="hidden" name="id" value="<%=news.getId()%>">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5">
                                                            Hide news
                                                        </h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <h5 class="text-center">Are you sure?</h5>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="submit" class="btn btn-danger">YES</button>
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">NO</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        <%
                            if(currentUser!=null){
                        %>
                                <div class="mt-4">
                                    <form action="/add-comment" method="post">
                                        <input type="hidden" name="newsId" value="<%=news.getId()%>">
                                        <div class="row">
                                            <div class="col-12">
                                                <textarea class="form-control" name="comment"></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-12">
                                                <button class="btn btn-success btn-small">Add comment</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                    <%
                            }
                    %>
                            <div class="row">
                                <div class="row-12">
                                    <%
                                        ArrayList<Comment> comments = (ArrayList<Comment>)
                                                request.getAttribute("comments");
                                        if(comments!=null){
                                            for(Comment comment : comments){
                                    %>
                                                <div class="list-group">
                                                    <a href="JavaScript:void(0)" class="list-group-item
                                                    list-group-item-action">
                                                        <div class="d-flex w-100 justify-content-between">
                                                            <h5 class="mb-1"><%=comment.getUser().getFullName()%></h5>
                                                            <small class="text-body-secondary"><%=comment
                                                                    .getPostDate()%>></small>
                                                        </div>
                                                        <p class="mb-1"><%=comment.getComment()%></p>
                                                    </a>
                                                </div>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
