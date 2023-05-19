<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container mt-3 mt-5">
        <div class="row">
            <div class="col-6 mx-auto">
                <form action="/add-news" method="post" >
                    <div class="modal-header p-3" style="border: 1px gray solid; border-radius: 5px">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">
                            Add news
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3" style="border: 1px gray solid; border-radius: 5px">
                        <div class="row mt-3">
                            <div class="col-12">
                                <label>
                                    CATEGORY:
                                </label>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-12">
                                <select class="form-select" name="category">
                                    <%
                                        ArrayList<NewsCategory> newsCategories = (ArrayList<NewsCategory>)
                                                request.getAttribute("newsCat");
                                        if (newsCategories!=null){
                                            for(NewsCategory newsCats : newsCategories) {
                                    %>
                                    <option value="<%=newsCats.getId()%>">
                                        <%=newsCats.getName()%>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="modal-body mt-3">
                            <div class="row">
                                <div class="col-12">
                                    <label>
                                        TITLE:
                                    </label>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-12">
                                    <input type="text" class="form-control" name="title" required>
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
                                    <textarea class="form-control" name="content" required rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer mt-3">
                            <button class="btn btn-success" style="margin-right: 5px">
                                Add news
                            </button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                Cancel
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>




</body>
</html>
