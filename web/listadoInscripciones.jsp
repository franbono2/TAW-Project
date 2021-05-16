<%-- 
    Document   : listadoInscripciones
    Created on : 16-may-2021, 3:59:32
    Author     : migue
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="taw.entity.Inscripcion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <%
        List<Inscripcion> listaInscripciones = (List)request.getAttribute("listaInscripciones");   
        List<Inscripcion> listaFiltrada = (List)request.getAttribute("listaFiltrada");
        if(listaFiltrada == null){
            listaFiltrada = new ArrayList();
        }
        if(listaInscripciones == null){
            listaInscripciones = new ArrayList();
        }
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    %>
    <body>
        <h1>Inscripciones</h1>
        <table border="1">
            <tr>
                <th>Evento</th>
                <th>Fecha inscripcion</th>
                <th>Fecha del evento</th>
                <th>Fila</th>
                <th>Columna</th>
                <th>Número de entradas</th>
                <th>Cancelar reserva</th>
            </tr>
            <%
                if(listaInscripciones != null && !listaInscripciones.isEmpty()){
                    
                for(Inscripcion ins: listaInscripciones){
            %>
                    <tr> 
            <%
                    if(ins.getFila() < 0){
            %>
                        <td><%= ins.getEvento().getNombre() %></td>
                        <td><%= formateador.format(ins.getFechaInscripcion())%></td>
                        <td><%= formateador.format(ins.getEvento().getFechaCelebracion())%></td>
                        <td>---</td>
                        <td>---</td>
                        <td><%= Math.abs(ins.getFila())%></td>
                        <td> <a href="ServletCancelarReserva?idInscripcion=<%= ins.getId()%>">Cancelar reserva</a></td>

            <%
                    }else{
            %>
                        <td><%= ins.getEvento().getNombre() %></td>
                        <td><%= formateador.format(ins.getFechaInscripcion())%></td>
                        <td><%= formateador.format(ins.getEvento().getFechaCelebracion())%></td>
                        <td><%= ins.getFila()%></td>
                        <td><%= ins.getColumna()%></td>
                        <td>1</td>
                        <td> <a href="ServletCancelarReserva?idInscripcion=<%= ins.getId()%>">Cancelar reserva</a></td>
            <%
                    }
            %>
                
                </tr>
            <% 
                }
            }else{
                for(Inscripcion ins: listaFiltrada){
            %>
                                <tr> 
            <%
                    if(ins.getFila() < 0){
            %>
                        <td><%= ins.getEvento().getNombre() %></td>
                        <td><%= formateador.format(ins.getFechaInscripcion())%></td>
                        <td><%= formateador.format(ins.getEvento().getFechaCelebracion())%></td>
                        <td>---</td>
                        <td>---</td>
                        <td><%= Math.abs(ins.getFila())%></td>
                        <td> <a href="ServletCancelarReserva?idInscripcion=<%= ins.getId()%>">Cancelar reserva</a></td>

            <%
                    }else{
            %>
                        <td><%= ins.getEvento().getNombre() %></td>
                        <td><%= formateador.format(ins.getFechaInscripcion())%></td>
                        <td><%= formateador.format(ins.getEvento().getFechaCelebracion())%></td>
                        <td><%= ins.getFila()%></td>
                        <td><%= ins.getColumna()%></td>
                        <td>1</td>
                        <td> <a href="ServletCancelarReserva?idInscripcion=<%= ins.getId()%>">Cancelar reserva</a></td>
            <%
                    }
            %>
                
                </tr>
            <%
                }
            }
            %>
            
        </table>
            <h2>Filtro de eventos</h2>
            <form method="POST" action="ServletFiltrarReservas?">
                Filtrar hasta: <input type="date" name="fechaFiltro"> </br>
                <input type="submit" value="Filtrar">
            </form>
            <form method="POST" action="ServletListadoInscripciones?">
                <input type="submit" value="Resetear">
            </form>
    </body>
</html>
