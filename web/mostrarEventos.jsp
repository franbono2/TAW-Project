<%-- 
    Document   : mostrarEventos
    Created on : 05-may-2021, 15:58:25
    Author     : migue
--%>

<%@page import="taw.dao.EventoFacade"%>
<%@page import="taw.entity.Evento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
             List<Evento> listaEventos = (List)request.getAttribute("listaEventos");
             Evento e;
        %>
        <h1>Eventos</h1>
        <form action="ServletMostrarEventos">
            <table class ="default" border="1" style="widht:auto; height:20px;">
                <tr>
                    <th>Evento</th>
                    <th>Descripción</th>
                    <th>Fecha del evento</th>
                    <th>Precio</th>
                </tr>
                <%
                    for (int i = 0; i < listaEventos.size(); i++){
                       e = listaEventos.get(i);
                    
                %>
                <tr>
                    <td><%= e.getNombre() %></td>
                    <td><%= e.getDescripcion() %></td>
                    <td><%= e.getFechaCelebracion() %></td>
                    <td><%= e.getCosteInscripcion() %></td>
                    <td><a href="ServletInfoEvento?id=<%= e.getId() %>">Entrar</a></td>
                </tr>
                <%
                    }
                %>
                        
             
            </table>
        </form>
    </body>
</html>
