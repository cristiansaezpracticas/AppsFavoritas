<%-- 
    Document   : actualizar
    Created on : 30-nov-2021, 20:36:47
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar o Actualizar App</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <h1>App</h1> 
        <a href="Servlet?op=listar">Volver</a>
        <%
            String mensaje = ( String ) request.getAttribute("mensaje");
            String operacion = ( String ) request.getAttribute("operacion");
            if ( mensaje!=null) out.println(mensaje);
        %>
        <form action="Servlet">
            <input type="hidden" value="<%=operacion%>" name="op">
            <p>Id: <input type="text" value="${app.id}" name="id" readonly></p>
            <p>Nombre: <input type="text" value="${app.nombre}" name="nombre"></p>
            <p>Categoria: <input type="text" value="${app.categoria}" name="categoria"></p>
            <p>Plataforma: <input type="text" value="${app.plataforma}" name="plataforma"></p>
            
            <input type="submit" value="Actualizar App">
        </form>
    </body>
</html>