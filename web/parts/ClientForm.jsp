<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${client != null}">
                <form action="/ClientServlet?method=UpdateClient" enctype="multipart/form-data" method="post">
                </c:if>
                <c:if test="${client == null}">
                    <form action="/ClientServlet?method=AddClient" enctype="multipart/form-data" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${client != null}">
                                Edit Client
                            </c:if>
                            <c:if test="${client == null}">
                                Add New Client
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${client != null}">
                        <input type="hidden" name="id" value="<c:out value='${client.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>First Name</label> <input type="text"
                                                         value="<c:out value='${client.fname}' />" class="form-control"
                                                         name="fname" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Last Name</label> <input type="text"
                                                        value="<c:out value='${client.lname}' />" class="form-control"
                                                        name="lname" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Address</label> <input type="text"
                                                      value="<c:out value='${client.address}' />" class="form-control"
                                                      name="address" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Phone</label> <input type="number"
                                                    value="<c:out value='${client.phone}' />" class="form-control"
                                                    name="phone" required>
                    </fieldset>



                    <button type="submit" class="btn btn-success my-2">Save</button>
                </form>
        </div>
    </div>
</div>