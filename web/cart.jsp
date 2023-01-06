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

        <section class="h-100 h-custom" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card">
                            <div class="card-body p-4">

                                <div class="row">

                                    <div class="col-lg-7">
                                        <h5 class="mb-3"><a href="/" class="text-body"><i
                                                    class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                                        <hr>
                                        <div class="d-flex justify-content-start align-items-start mb-4">
                                            <div>
                                                <p class="mb-1">Shopping cart</p>
                                                <p class="mb-0">You have <span id="cartItemsCount"></span> items in your cart</p>
                                            </div>
                                        </div>

                                        <div id="cartItems">

                                        </div>



                                    </div>
                                    <div class="col-lg-5">

                                        <div class="card bg-primary text-white rounded-3">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center mb-4">
                                                    <h5 class="mb-0">Client details</h5>
                                                </div>

                                                <form class="mt-4" action="/NewOrder" method="post">
                                                    <div class="row mb-4">
                                                        <div class="col-md-6">
                                                            <div class="form-outline form-white">
                                                                <input type="text" id="typeExp" class="form-control form-control-lg"
                                                                       placeholder="First Name" name="fname" required />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <input type="text" id="typeExp" class="form-control form-control-lg"
                                                                   placeholder="Last Name" name="lname"  required />
                                                        </div>
                                                    </div>

                                                    <div class="form-outline form-white mb-4">
                                                        <input type="text" id="typeName" class="form-control form-control-lg" required
                                                               placeholder="Full Address"  name="address" />
                                                        <label class="form-label"for="typeName">Address</label>
                                                    </div>

                                                    <div class="form-outline form-white mb-4">
                                                        <input type="text" id="typeText" name="phone" class="form-control form-control-lg" required />
                                                        <label class="form-label" for="typeText">Phone Number</label>
                                                    </div>
                                                    <hr class="my-4">

                                                    <div class="d-flex justify-content-between mb-4">
                                                        <p class="mb-2">Total</p>
                                                        <p class="mb-2 text-bold" id="totalPrice"></p>
                                                    </div>

                                                    <div id="hiddencartitems">
                                                    </div>
                                                    <input type="hidden" id="totalinput" name="totalinput" value="0" />

                                                    <button type="submit" class="btn btn-info btn-block btn-lg">
                                                        <div class="d-flex justify-content-between">
                                                            <span>Checkout <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                                                        </div>
                                                    </button>

                                                </form>
                                            </div>
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
        generatecarthtml()
    </script>
</body>
</html>
