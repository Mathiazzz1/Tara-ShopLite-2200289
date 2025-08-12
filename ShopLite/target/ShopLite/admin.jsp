<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ShopLite • Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg bg-white border-bottom">
    <div class="container">
        <a class="navbar-brand text-danger" href="${pageContext.request.contextPath}/home">ShopLite • Admin</a>
    </div>
</nav>

<section class="container my-5" style="max-width:720px;">
    <c:if test="${param.err=='1'}">
        <div class="alert alert-danger">Datos inválidos</div>
    </c:if>
    <div class="card shadow-sm">
        <div class="card-body p-4">
            <h3 class="mb-3">Nuevo producto</h3>
            <!-- Requisito (POST /admin): crear producto válido y redirigir a /home -->
            <form method="post" action="${pageContext.request.contextPath}/admin" class="row g-3">
                <div class="col-12">
                    <label class="form-label">Nombre</label>
                    <input class="form-control" name="name" placeholder="Teclado 60%">
                </div>
                <div class="col-12">
                    <label class="form-label">Precio</label>
                    <input class="form-control" name="price" placeholder="59.99">
                </div>
                <div class="col-12">
                    <button class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
