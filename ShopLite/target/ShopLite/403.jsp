<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Acceso denegado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center bg-light" style="min-height:100vh;">
<div class="container text-center">
    <h1 class="display-6 mb-3">403</h1>
    <p class="text-muted">No tienen permisos para ver esta página.</p>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login.jsp">Ir a Login</a>
</div>
</body>
</html>
