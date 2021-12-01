<%-- 
    Document   : listar
    Created on : 30-nov-2021, 20:36:41
    Author     : Cristian
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.Apps"%>
<%@page import="modelo.Apps"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Apps</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            
        <h1>Listado de Apps</h1>
        <a class="btn btn-primary" href="Servlet?op=insertar">Nueva App</a>

        <%
            List<Apps> listaApps = ( List<Apps> )request.getAttribute("listado");
            String mensaje = ( String ) request.getAttribute("mensaje");
            String num_paginasStr = ( String )  request.getAttribute("num_paginas");
            int num_paginas = Integer.parseInt(num_paginasStr);
        %>
         
        <h2 style="margin-top: 10px" class="alert alert-success"><%=mensaje%></h2>
        <table class="table">
            <tr>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Plataforma</th>
                <th>Actualizar</th>
                <th>Borrar</th>
            </tr>
            <% for ( Apps p: listaApps ) { %>
            <tr>
                <td><%=p.getNombre() %></td>
                <td><%=p.getCategoria() %></td>
                <td><%=p.getPlataforma() %></td>
                <td><a href="Servlet?op=actualizar&id=<%=p.getId() %>">Actualizar</a></td>
                <td><a href="Servlet?op=borrar&id=<%=p.getId() %>" onclick="return Confirmation() ">Borrar</a></td>
            </tr>
            <%}%>
        </table>
        <p>Mostrando página ${pagina} de ${num_paginas}</p>
        <%
            for ( int p=1;p<=num_paginas;p++ ) {
        %>
                <a href="Servlet?op=listar&pagina=<%=p%>" ><%=p%></a> 
        <%      
            }
        %>
        </div>
        <script>
            function Confirmation(){
                if (confirm("¿Está seguro/a de que quiere eliminar la aplicación?")  ) {
                    alert("El registro de eliminará");
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </body>
</html>