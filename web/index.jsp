<%-- 
    Document   : index
    Created on : Dec 28, 2022, 8:55:06 PM
    Author     : zakaria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="./parts/head.jsp"/>
    <body>
        <jsp:include page="./parts/header.jsp"/>

        <header class="bg-dark py-5">
            <div class="container px-4 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Enjoy a 10% Sale </h1>
                    <p class="lead fw-normal text-white-50 mb-0">We offer you best prices and quality </p>
                </div>
            </div>
        </header>
   
   

<!-- Modal -->
<div class="modal" id="added">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-body">
        The item was added to your Shopping Cart
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="hidemodal()">Continue Shopping</button>
        <a href="cart.jsp"> <button type="button" class="btn btn-primary">View Cart</button> </a>
      </div>
    </div>
  </div>
</div>

   <section class="py-5" style="min-height: 60vh">
       <div class="container px-4 px-lg-5 mt-5">
           <div class="row gx-4 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
               <jsp:include page="ProductServlet?method=ViewCards"/>
               
           </div>
       </div>
   </section>

   
     <jsp:include page="./parts/footer.jsp"/>
    </body>
</html>
