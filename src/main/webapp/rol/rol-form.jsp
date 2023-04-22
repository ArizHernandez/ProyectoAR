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
                            value="${actualRol.createdUser}"
                    >
                        <option selected value="">Selecciona una opcion</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
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
                            value="${actualRol.updatedUser}"
                    >
                        <option selected value="">Selecciona una opcion</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
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
                    <button class="btn btn-primary mt-3">
                        <%= request.getAttribute("actualRol") == null ? "Crear" : "Editar" %>
                    </button>
                </div>
            </form>
        </section>
    </body>
</html>