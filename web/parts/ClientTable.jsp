<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="client" items="${listClients}">

            <tr>
                <td><c:out value="${client.id}" /></td>
                <td><c:out value="${client.fname}" /></td>
                <td><c:out value="${client.lname}" /></td>
                <td><c:out value="${client.address}" /></td>
                <td><c:out value="${client.phone}" /></td>
                <td><a href="/admin/clients.jsp?method=edit&id=<c:out value='${client.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                        href="/ClientServlet?method=delete&id=<c:out value='${client.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>

</table>