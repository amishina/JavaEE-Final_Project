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
                <form action="/add-category" method="post" >
                    <div class="modal-header p-3" style="border: 1px gray solid; border-radius: 5px">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">
                            Add category
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3" style="border: 1px gray solid; border-radius: 5px">
                        <div class="row mt-3">
                            <div class="col-12">
                                <label>
                                    CATEGORY NAME:
                                </label>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-12">
                                <input type="text" class="form-control" name="categoryName" required
                                placeholder="Enter category name">
                            </div>
                        </div>
                        <div class="modal-footer mt-3">
                            <button class="btn btn-success" style="margin-right: 5px">
                                Add category
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
