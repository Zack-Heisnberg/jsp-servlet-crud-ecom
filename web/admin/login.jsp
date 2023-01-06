<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!DOCTYPE html>
<html>
       <jsp:include page="../parts/head.jsp"/>
    <body>
      <jsp:include page="../parts/header.jsp"/>
          <div style="min-height: 70vh" class="row justify-content-center align-content-center">
             <div class="card col-md-3 col-sm-6 col-10 mt-5 mb-5" >
  <div class="card-body">
    <h5 class="card-title text-center">Login</h5>
<form  action="/LoginPost" method="post">
  <div class="form-group m-2">
    <label for="UserName">UserName</label>
    <input type="text" class="form-control" name="username" placeholder="Enter Username" required>
  </div>
  <div class="form-group m-2">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" name="password" placeholder="Enter Password" required>
  </div>
    <div class="p-1 text-danger">
          ${alert}
    </div>
    <div class="text-center"> 
  <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
  </div>
</div>
    </div>
</div>

      <jsp:include page="../parts/footer.jsp"/>
    </body>
</html>
