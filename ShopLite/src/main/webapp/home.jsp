<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ShopLite • Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg bg-white border-bottom">
    <div class="container">
        <a class="navbar-brand text-primary" href="${pageContext.request.contextPath}/home">ShopLite</a>
        <div class="ms-auto d-flex gap-2">
            <a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/admin">Nuevo producto (Admin)</a>
            <form method="post" action="${pageContext.request.contextPath}/auth/logout">
                <button class="btn btn-sm btn-outline-danger">Cerrar sesión</button>
            </form>
        </div>
    </div>
</nav>

<section class="container my-5">
    <h2 class="mb-3">Productos</h2>

    <!-- Requisito: esta lista debe venir paginada desde HomeServlet -->
    <div class="row g-3">
        <c:forEach var="p" items="${items}">
            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${p.name}"/></h5>
                        <p class="text-muted">$ <c:out value="${p.price}"/></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Controles de paginación (requiere page,size,total desde el servlet) -->
    <div class="d-flex justify-content-between align-items-center mt-4">
        <a class="btn btn-outline-secondary ${page==1?'disabled':''}"
           href="${pageContext.request.contextPath}/home?page=${page-1}&size=${size}">« Anterior</a>
        <span class="text-muted">Página ${page}, tamaño ${size} (total ${total})</span>
        <a class="btn btn-outline-secondary ${(page*size)>=total?'disabled':''}"
           href="${pageContext.request.contextPath}/home?page=${page+1}&size=${size}">Siguiente »</a>
    </div>
</section>
</body>
</html>
