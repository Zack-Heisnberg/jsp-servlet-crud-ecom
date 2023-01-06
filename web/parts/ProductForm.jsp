<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${product != null}">
                <form action="/ProductServlet?method=UpdateProduct" enctype="multipart/form-data" method="post">
                </c:if>
                <c:if test="${product == null}">
                    <form action="/ProductServlet?method=AddProduct" enctype="multipart/form-data" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${product != null}">
                                Edit Product
                            </c:if>
                            <c:if test="${product == null}">
                                Add New Product
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${product != null}">
                        <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Title</label> <input type="text"
                                                    maxlength="15"
                                                    value="<c:out value='${product.title}' />" class="form-control"
                                                    name="title" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Description</label> <input type="text"
                                                          maxlength="35"
                                                          onchange="this.value = this.value.replace('\'','')"
                                                          value="<c:out value='${product.description}' />" class="form-control"
                                                          name="description" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Price</label> <input type="number"
                                                    value="<c:out value='${product.price}' />" class="form-control"
                                                    name="price" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Image ( 1 * 1 )</label>
                        <c:choose> 
                            <c:when test="${product != null}">
                                <input type="file" accept="image/*"
                                       class="form-control"
                                       name="image">
                            </c:when>
                            <c:otherwise>
                                <input type="file" accept="image/*"
                                       class="form-control"
                                       name="image" required="">
                            </c:otherwise>
                        </c:choose>
                    </fieldset>

                    <button type="submit" class="btn btn-success my-2">Save</button>
                </form>
        </div>
    </div>
</div>