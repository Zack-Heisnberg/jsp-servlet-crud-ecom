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

        <section class="h-100 h-custom" style="background-color: #eee;min-height: 75vh;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card">
                            <div class="card-body p-4">

                                <div class="row">

                                    <div class="col-lg-7">
                                        <h5 class="mb-3"><a href="/" class="text-body"><i
                                                    class="fas fa-long-arrow-alt-left me-2"></i>Thank you for your Order</a></h5>
                                        <hr>
                                        <div class="d-flex justify-content-start align-items-start mb-4">
                                            <div>
                                                <p class="mb-1">Dear ${fname}</p>
                                                <p class="mb-0">Your <span id="cartItemsCount"></span> order id: ( ${orderid} ) , was placed successfully </p>
                                            </div>
                                        </div>

                                        <div id="cartItems">

                                        </div>



                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="./parts/footer.jsp"/>

    <script>
        clearcart();
    </script>
</body>
</html>
