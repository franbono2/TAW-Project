<%-- 
    Document   : estudiosEstadisticos_list
    Created on : 01-may-2021, 18:41:43
    Author     : Francisco Bono
--%>
<%@page import="taw.entity.EstudioEstadistico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        List<EstudioEstadistico> lista = (List)request.getAttribute("lista");
    %>
    <body>
        <h1>Listado de Estudios Estadisticos</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>
                <th>RESULTADOS</th>
            </tr>
            <%
                for (EstudioEstadistico e : lista) {
            %>
            <tr>
                <td> <%= e.getId()%> </td>
                <td> <%= e.getNombre() %> </td>
                <td> <%= e.getDescripcion() %> </td>
                <td> <%= e.getResultados() %> </td>
            </tr>
            <%
                }
            %>
        </table>
        <!--href para crear un nuevo estudio-->
    </body>
</html>
