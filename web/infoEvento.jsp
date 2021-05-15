<%-- 
    Document   : infoEvento
    Created on : 05-may-2021, 18:06:23
    Author     : migue
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="taw.entity.Inscripcion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="taw.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
    <%
        Evento evento = (Evento)request.getAttribute("evento");
        boolean inscrito = (Boolean)request.getAttribute("inscrito");
        List<Inscripcion> listaInscripcionesUsuario = (List)request.getAttribute("listaInscripcionesUsuario");
        List<Inscripcion> listaInscripciones = (List)request.getAttribute("listaInscripciones");
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Map<Integer,Integer> asientosSeleccionados = new HashMap();
    %>
    
        <h1> <%= evento.getNombre() %> </h1>
        <%= evento.getDescripcion() %> <br/>
        
        Lugar del evento: <%= evento.getLocalizacion() %> <br/>
        Fecha de evento: <%= formateador.format(evento.getFechaCelebracion())%> <br/>
        Precio de entrada: <%= evento.getCosteInscripcion() %> <br/>
        Aforo: <%= evento.getAforo() %> <br/>
        Fecha límite de reserva <%= formateador.format(evento.getFechaLimiteReserva())%> <br/>
        Asientos numerados:
        <%
            Integer nFilas = evento.getNumeroFilas();
            Integer aFilas = evento.getAsientosPorFila();
            if(nFilas == null || aFilas == null){ 
        %>
                NO
        <%
            }else{
        %>      
                SI
        <%
            }
        %>
        
        <%
           if(inscrito){
        %>
        </br> <b> Ya está inscrito en este evento: Ya posee <%= listaInscripcionesUsuario.size() %> entrada/s</b>
        <%
           }
        %>
        <%
            
        if(nFilas != null && aFilas != null){
        %>
        <form method="POST" action="ServletInscripcion">
            </br>
            <label for="asientos">Elija asientos</label>
            <table class ="default" border="1" style="widht:auto; height:20px;">
                <%
                    int numeroFilas = evento.getNumeroFilas();
                    int numeroColumnas = evento.getAsientosPorFila();
                    if(listaInscripciones.isEmpty()){
                        for (int filas = 1; filas <= numeroFilas; filas++){
                %>
                            <tr>
                <%
                            for (int columnas = 1; columnas <= numeroColumnas; columnas++){
                %>
                                <td> 
                                <b> <input type="submit" style="color:black" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoSeleccionado"/> </b>
                                </td>  
                <%
                            }
                %>
                            </tr>
                <%
                        }
                    }else{
                        for (int filas = 1; filas <= numeroFilas; filas++){       
                %>
                        <tr>
                <%
                            for(int columnas = 1; columnas <= numeroColumnas; columnas++){
                                    for(int i = 0; i < listaInscripciones.size(); i++){
                                         Inscripcion aux = listaInscripciones.get(i);
                                         if(aux.getFila() == filas && aux.getColumna() == columnas){
                %>
                                            <td> 
                                           <b> <input type="submit" style="color:red" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoOcupado" disabled=""/> </b>
                                           </td>
                <%
                                        }else{
                %>
                                            <td> 
                                            <b> <input type="submit" style="color:black" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoSeleccionado"/> </b>
                                            </td>    
                <%
                                        }
                                    }    
                            }
                %>                 
                                    
                <%
                        }
                %>
                        </tr>
                <%
                    }
                %>
            </table>
    <%
        }
    %>
        </form>
    
</html>
