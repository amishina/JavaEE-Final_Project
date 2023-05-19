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
                <div class="col-12 mx-auto">
                    <h4 class="text-center">Welcome <%=currentUser!=null?currentUser.getFullName():""%></h4>

                    <div class="container mt-3 mt-5 pb-5">
                        <%
                            if (currentUser!=null) {
                        %>
                        <div class="row">
                            <div class="col-6 mx-auto">
                                <div class="row">
                                    <div class="col-12">
                                        <label>FULL NAME: </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" readonly
                                               value="<%=currentUser.getFullName()%>">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>EMAIL: </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" readonly
                                               value="<%=currentUser.getEmail()%>">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>PASSWORD: </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="password" class="form-control" readonly
                                               value="<%=currentUser.getPassword()%>">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                                data-bs-target="#changeFullName">
                                            Change full name
                                        </button>
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                                data-bs-target="#changePassword">
                                            Change password
                                        </button>
                                    </div>
                                </div>
                                <!-- Modal Change full name-->
                                <div class="modal fade" id="changeFullName" data-bs-backdrop="static"
                                     data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
                                      aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel">
                                                    Change full name
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="/save-profile?flag=1" method="post">
                                                    <input type="hidden" name="id" value="<%=currentUser.getId()%>">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <label>FULL NAME: </label>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <input type="text" class="form-control" name="fullName"
                                                                   required value="<%=currentUser.getFullName()%>">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-3">
                                                        <div class="col-12">
                                                            <button class="btn btn-primary">Update full name</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Modal Change password-->
                                <div class="modal fade" id="changePassword" data-bs-backdrop="static"
                                     data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel2">
                                                    Change password
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="/save-profile?flag=2" method="post">
                                                    <input type="hidden" name="id" value="<%=currentUser.getId()%>">
                                                    <div class="row mt-3">
                                                        <div class="col-12">
                                                            <label>CURRENT PASSWORD: </label>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <input type="password" class="form-control" name="password"
                                                                   required placeholder="Enter current password">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-3">
                                                        <div class="col-12">
                                                            <label>NEW PASSWORD: </label>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <input type="password" class="form-control"
                                                                   name="newPassword" required
                                                                   placeholder="Enter new password">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <input type="password" class="form-control"
                                                                   name="reNewPassword" required
                                                                   placeholder="Re-enter new password">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-3">
                                                        <div class="col-12">
                                                            <button class="btn btn-primary">Change password</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                        } else {
                        %>
                        <h3 class="text-center">USER NOT FOUND</h3>
                        <%
                            }
                        %>
                    </div>


                </div>
            </div>
        </div>
    </body>
</html>
