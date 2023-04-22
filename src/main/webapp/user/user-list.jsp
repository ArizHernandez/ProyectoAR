<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html data-bs-theme="dark">
    <%@include file="/shared/head-loader.jsp" %>
    <body>
        <%@include file="/shared/nav.jsp"%>
        <section class="container">
            <h2 class="text-center">
                <span class="fw-bolder">Usuarios</span>
            </h2>
            <section class="row justify-content-between align-items-center">
                <div class="col-auto">
                    <a href="UserController?action=form" class="btn btn-info mb-md-3">Agregar <span class="fw-bolder">+</span></a>
                </div>
                <p class="mb-0 d-block w-auto">
                    Total items <span class="fw-bolder">${users.size()}</span>
                </p>
                <form class="col-auto" method="GET" action="UserController">
                    <div class="input-group">
                        <input type="hidden" name="action" value="list" />
                        <input class="form-control" type="search" placeholder="Buscar" name="search" value="${search}"/>
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </form>
            </section>
            <section class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Usuario</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellido</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Rol</th>
                            <th scope="col">Creado por</th>
                            <th scope="col">Actualizado por</th>
                            <th scope="col">Fecha creacion</th>
                            <th scope="col">Ultima actualizacion</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.idUsuario}</td>
                                <td>${user.user}</td>
                                <td>${user.name}</td>
                                <td>${user.lastName}</td>
                                <td>${user.status == 1 ? "Activo" : "Inactivo"}</td>
                                <td>${user.rolName}</td>
                                <td>${user.createdUser}</td>
                                <td>${user.updatedUser}</td>
                                <td>${user.createdDate}</td>
                                <td>${user.updatedDate}</td>
                                <td class="d-flex g-2">
                                    <form method="GET" action="UserController">
                                        <input type="hidden" name="id" value="${user.idUsuario}" />
                                        <input type="hidden" name="action" value="form" />
                                        <button class="btn btn-primary" type="submit">Editar</button>
                                    </form>
                                    <form method="POST" action="UserController">
                                        <input type="hidden" name="idDelete" value="${user.idUsuario}" />
                                        <button class="btn btn-danger ms-2" type="submit">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </section>
    </body>
</html>
