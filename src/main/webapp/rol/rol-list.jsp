<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html data-bs-theme="dark">
    <%@include file="/shared/head-loader.jsp" %>
    <body>
        <%@include file="/shared/nav.jsp"%>
        <section class="container">
            <h2 class="text-center">
                <span class="fw-bolder">Roles</span>
            </h2>
            <section class="row justify-content-between align-items-center">
                <div class="col-auto">
                    <a href="RolController?action=form" class="btn btn-info mb-md-3">Agregar <span class="fw-bolder">+</span></a>
                </div>
                <p class="mb-0 d-block w-auto">
                    Total items <span class="fw-bolder">${rols.size()}</span>
                </p>
                <form class="col-auto" method="GET" action="RolController">
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
                            <th scope="col">Nombre</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Creado por</th>
                            <th scope="col">Actualizado por</th>
                            <th scope="col">Fecha creacion</th>
                            <th scope="col">Ultima actualizacion</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${rols}" var="rol">
                            <tr>
                                <td>${rol.idRol}</td>
                                <td>${rol.name}</td>
                                <td>${rol.active == 1 ? "Activo" : "Inactivo"}</td>
                                <td>${rol.createdUser}</td>
                                <td>${rol.updatedUser}</td>
                                <td>${rol.createdDate}</td>
                                <td>${rol.updatedDate}</td>
                                <td class="d-flex g-2">
                                    <form method="GET" action="RolController">
                                        <input type="hidden" name="id" value="${rol.idRol}" />
                                        <input type="hidden" name="action" value="form" />
                                        <button class="btn btn-primary" type="submit">Editar</button>
                                    </form>
                                    <form method="POST" action="RolController">
                                        <input type="hidden" name="idDelete" value="${rol.idRol}" />
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
