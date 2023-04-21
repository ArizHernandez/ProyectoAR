<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <%@include file="shared/head-loader.jsp"%>
    <body>
        <h1>
            <%= "Hello World!" %>
        </h1>
        <br/>
        <div class="alert alert-primary" role="alert">
            A simple primary alert—check it out!
        </div>
        <a href="RolController?action=list">Hello Servlet</a>
    </body>
</html>