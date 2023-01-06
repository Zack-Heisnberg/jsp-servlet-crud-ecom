
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%
    if (session.getAttribute("admin") != null) {
        String name = (String) session.getAttribute("admin");
    } else {
        response.sendRedirect("/admin/login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <jsp:include page="../parts/head.jsp"/>
    <body>
        <jsp:include page="../parts/adminheader.jsp"/>
        <div class="container-fluid p-5" style="min-height: 75vh">
            <div class="container text-left d-flex">
                <h4>Clients Management</h4> <a href="<%=request.getContextPath()%>?method=ViewAdd" class="mx-4 btn btn-success">Add
                    New Client</a>
            </div>
            <br>
            <%
                String method = request.getParameter("method");
            %>
             <%
                String id = request.getParameter("id");
            %>
            <jsp:include page="/ClientServlet?method=<%=method%>&id=<%=id%>"/>
        </div>
        <jsp:include page="../parts/footer.jsp"/>
    </body>
</html>
