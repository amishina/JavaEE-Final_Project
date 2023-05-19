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
            <div class="col-6 mx-auto">
                <form action="/register" method="post">
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>EMAIL: </label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="email" class="form-control" name="email" required
                                   placeholder="Enter email">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>PASSWORD: </label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="password" class="form-control" name="password" required
                                   placeholder="Enter password">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>RE-ENTER PASSWORD: </label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="password" class="form-control" name="re_password" required
                                   placeholder="Re-enter password">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>FULL NAME: </label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="text" class="form-control" name="fullName" required
                                   placeholder="Enter full name">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button class="btn btn-success">Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
