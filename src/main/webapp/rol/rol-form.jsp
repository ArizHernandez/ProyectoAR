<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html data-bs-theme="dark">
    <%@include file="/shared/head-loader.jsp" %>
    <body>
        <%@include file="/shared/nav.jsp"%>
        <h2 class="text-center">
            <%= request.getAttribute("actualRol") == null ? "Crear" : "Editar" %> Rol
        </h2>
        <section class="container">
            <form class="row g-4" method="POST" action="RolController">
                <input type="hidden" name="id" value="${actualRol.idRol}"/>
                <div class="col-md-6">
                    <label class="form-label" for="name">
                        Nombre
                    </label>
                    <input
                            required
                            maxlength="80"
                            class="form-control"
                            name="name"
                            id="name"
                            value="${actualRol.name}"
                    />
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="active">
                        Estado:
                    </label>
                    <select
                            required
                            class="form-select"
                            name="active"
                            id="active"
                    >
                        <option value="">Selecciona una opcion</option>
                        <option ${actualRol.active == 0 ? "selected" : ""}  value="0">Inactivo</option>
                        <option ${actualRol.active == 1 ? "selected" : ""}  value="1">Activo</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="createdUser">
                        Usuario creador:
                    </label>
                    <select
                            required
                            class="form-select"
                            name="createdUser"
                            id="createdUser"
                    >
                        <option selected value="">Selecciona una opcion</option>
                        <c:forEach items="${users}" var="user">
                            <option ${actualRol.createdUser && (actualRol.createdUser == user.name) ? "selected" : ""} value="${user.name}">${user.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="updatedUser">
                        Usuario que actualiza:
                    </label>
                    <select
                            required
                            class="form-select"
                            name="updatedUser"
                            id="updatedUser"
                    >
                        <option selected value="">Selecciona una opcion</option>
                        <c:forEach items="${users}" var="user">
                            <option ${actualRol.updatedUser && (actualRol.updatedUser == user.name) ? "selected" : ""} value="${user.name}">${user.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12">
                    <label for="description">
                        Descripcion
                    </label>
                    <textarea
                            class="form-control"
                            name="description"
                            id="description"
                            value="${actualRol.description}"
                            style="height: 150px; resize: none;"
                    ></textarea>
                </div>
                <div>
                    <a href="RolController?action=list" class="btn btn-danger mt-3">
                        Cancelar
                    </a>
                    <button class="btn btn-primary mt-3">
                        <%= request.getAttribute("actualRol") == null ? "Crear" : "Editar" %>
                    </button>
                </div>
            </form>
        </section>
    </body>
</html>