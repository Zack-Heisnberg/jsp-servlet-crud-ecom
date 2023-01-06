<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th style="width: 75px">Image</th>
            <th>Title</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${listProducts}">

            <tr>
                <td><c:out value="${product.id}" /></td>
                <td><img width="75px" height="75px" style="object-fit: scale-down;" src="/ImageServlet?id=<c:out value='${product.id}' />"/></td>
                <td><c:out value="${product.title}" /></td>
                <td><c:out value="${product.description}" /></td>
                <td><c:out value="${product.price}" /></td>
                <td><a href="/admin/index.jsp?method=edit&id=<c:out value='${product.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                        href="/ProductServlet?method=delete&id=<c:out value='${product.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>

</table>