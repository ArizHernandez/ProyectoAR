<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html data-bs-theme="dark">
    <%@include file="/shared/head-loader.jsp" %>
    <body>
        <%@include file="/shared/nav.jsp"%>
        <h2 class="text-center">
            <%= request.getAttribute("actualUser") == null ? "Crear" : "Editar" %> Usuario
        </h2>
        <section class="container">
            <form class="row g-4" method="POST" action="UserController">
                <input type="hidden" name="id" value="${actualUser.idUsuario}"/>
                <div class="col-md-6">
                    <label class="form-label" for="code">
                        Codigo
                    </label>
                    <input
                            required
                            maxlength="80"
                            class="form-control"
                            name="code"
                            id="code"
                            value="${actualUser.code}"
                    />
                </div>
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
                            value="${actualUser.name}"
                    />
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="lastName">
                        Apellido
                    </label>
                    <input
                            required
                            maxlength="80"
                            class="form-control"
                            name="lastName"
                            id="lastName"
                            value="${actualUser.lastName}"
                    />
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="user">
                        Usuario
                    </label>
                    <input
                            required
                            maxlength="80"
                            class="form-control"
                            name="user"
                            id="user"
                            value="${actualUser.user}"
                    />
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="password">
                        Password
                    </label>
                    <input
                            required
                            maxlength="80"
                            class="form-control"
                            name="password"
                            id="password"
                            value="${actualUser.password}"
                    />
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="idRol">
                        Rol:
                    </label>
                    <select
                            required
                            class="form-select"
                            name="idRol"
                            id="idRol"
                    >
                        <option selected value="">Selecciona una opcion</option>
                        <c:forEach items="${rols}" var="rol">
                            <option ${actualRol.idRol && (actualRol.idRol == user.idRol) ? "selected" : ""} value="${rol.idRol}">${rol.name}</option>
                        </c:forEach>
                    </select>
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
                        <option ${actualUser.status == 0 ? "selected" : ""}  value="0">Inactivo</option>
                        <option ${actualUser.status == 1 ? "selected" : ""}  value="1">Activo</option>
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
                            <option
                                ${actualRol.createdUser && (actualRol.createdUser == user.name)
                                    ? "selected"
                                    : ""}
                                value="${user.name}"
                            >
                                    ${user.name}
                            </option>
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
                <div>
                    <a href="UserController?action=list" class="btn btn-danger mt-3">
                        Cancelar
                    </a>
                    <button class="btn btn-primary mt-3">
                        <%= request.getAttribute("actualUser") == null ? "Crear" : "Editar" %>
                    </button>
                </div>
            </form>
        </section>
    </body>
</html>