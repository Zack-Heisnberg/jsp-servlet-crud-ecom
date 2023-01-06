<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Total</th>
            <th>Client</th>
            <th>Items</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="order" items="${listOrders}">

            <tr>
                <td>${order.id}</td>
                <td>${order.date}</td>
                 <td>${order.total}DZD</td>
                <td>id: ${order.client.id} <br>
                 Name: ${order.client.fname} ${order.client.lname} <br>
                 Address: ${order.client.address} <br>
                 Phone: ${order.client.phone} <br>
                </td>
                <td> 
                <c:forEach var="ProductCart" items="${order.products}">
                    <table><tr>
                   <td>${ProductCart.product.title}  -  x${ProductCart.qt} - ${ProductCart.price}DZD</td>
                      </tr>
                      </table>
                </c:forEach>
                </td>
                <td><a href="/admin/index.jsp?method=edit&id=<c:out value='${order.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                        href="/OrderServlet?method=delete&id=<c:out value='${order.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>

</table>