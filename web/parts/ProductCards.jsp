
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


        <c:forEach var="product" items="${listProducts}">
      <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img height="200px" style="object-fit: scale-down;" class="card-img-top mt-2" src="/ImageServlet?id=${product.id}" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${product.title}</h5>
                                    <!-- Product price-->
                                    ${product.price} DZD
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
         <div onclick="addtocart(${product.id}, '${product.title}', '${product.description}', ${product.price})" class="text-center"><button class="btn btn-outline-dark mt-auto" >Add to cart</button></div>
                            </div>
                        </div>
                    </div>
        </c:forEach>
        <c:if test="${listProducts.isEmpty()}">
            <div class="col text-center">
                <h4>
                    No Products Available
                </h4> 
            </div>  
        </c:if>