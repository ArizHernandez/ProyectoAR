<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <%@include file="/shared/head-loader.jsp" %>
    <body>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Activo</th>
                <th scope="col">Creado por</th>
                <th scope="col">Actualizado por</th>
                <th scope="col">Fecha creacion</th>
                <th scope="col">Ultima actualización</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rols}" var="rol">
                <tr>
                    <td scope="row">${rol.name}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
