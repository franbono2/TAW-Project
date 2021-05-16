<%-- 
    Document   : infoEvento
    Created on : 05-may-2021, 18:06:23
    Author     : migue
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
        int numeroEntradasOcupadas = (Integer)request.getAttribute("nEntradasOcupadas");
        int numeroEntradasDisponibles = evento.getAforo() - Math.abs(numeroEntradasOcupadas);
        int maximoEntradasPorUsuario = evento.getAforo();
        int aforo;
        if(evento.getNumeroFilas() != null && evento.getAsientosPorFila() != null){
            aforo = evento.getNumeroFilas() * evento.getAsientosPorFila();
        }else{
            aforo = evento.getAforo();
        }
        
        if(evento.getLimiteEntradasPorUsuario() != null){
             maximoEntradasPorUsuario= evento.getLimiteEntradasPorUsuario();
        }
        
        int limiteEntradas;
        if(numeroEntradasDisponibles <= maximoEntradasPorUsuario){
            limiteEntradas = numeroEntradasDisponibles;
        }else{
            limiteEntradas = maximoEntradasPorUsuario;
        }
        
        List<String> listaAsientosSeleccionados = (List)request.getAttribute("listaAsientosSeleccionados");
        String asientosSeleccionados ="";
        for(String s : listaAsientosSeleccionados){
            if(s != null){
                asientosSeleccionados += s + ",";
            }
        }

        request.setAttribute("inscrito", inscrito);
        request.setAttribute("listaInscripcionesUsuario", listaInscripcionesUsuario);
        request.setAttribute("listaInscripciones", listaInscripciones);
    %>
    
        <h1> <%= evento.getNombre() %> </h1>
        <%= evento.getDescripcion() %> <br/>
        
        Lugar del evento: <%= evento.getLocalizacion() %> <br/>
        Fecha de evento: <%= formateador.format(evento.getFechaCelebracion())%> <br/>
        Precio de entrada: <%= evento.getCosteInscripcion() %> <br/>
        Aforo: <%= aforo %> <br/>
        Fecha límite de reserva <%= formateador.format(evento.getFechaLimiteReserva())%> <br/>
        Asientos numerados:
        <%
            Integer nFilas = evento.getNumeroFilas();
            Integer aFilas = evento.getAsientosPorFila();
            if(nFilas == null || aFilas == null){ 
        %>
                NO
                </br> Numero de entradas disponibles: <%= numeroEntradasDisponibles%>
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
        <form method="POST" action="ServletInscripcion?idEvento=<%= evento.getId()%>&asientosSeleccionados=<%=asientosSeleccionados%>">
            </br>
            <label for="asientos">Elija asientos</label>
            <table class ="default" border="1" style="widht:auto; height:20px;">
                <%
                    int numeroFilas = evento.getNumeroFilas();
                    int numeroColumnas = evento.getAsientosPorFila();
                    if(listaInscripciones.isEmpty() && listaAsientosSeleccionados.isEmpty()){
                        for (int filas = 1; filas <= numeroFilas; filas++){
                %>
                            <tr>
                <%
                            for (int columnas = 1; columnas <= numeroColumnas; columnas++){
                %>
                                <td> 
                                <b> <input type="submit" style="color:black" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoLibreSeleccionado"/> </b>
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
                                if(listaInscripciones.isEmpty()){
                                    if(asientosSeleccionados != null && !asientosSeleccionados.isEmpty() && listaAsientosSeleccionados.contains(filas + "-" + columnas)){
                %>
                                         <td> 
                                           <b> <input type="submit" style="color:blue" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoSeleccionado"/> </b>
                                           </td>
                <%
                                    }else{
                %>
                                            <td> 
                                            <b> <input type="submit" style="color:black" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoLibreSeleccionado"/> </b>
                                            </td>    
                <%
                                    }   
                                }else{

                                    boolean creado = false;
                                    for(int i = 0; i < listaInscripciones.size() && !creado; i++){
                                         Inscripcion aux = listaInscripciones.get(i);
                                         if(aux.getFila() == filas && aux.getColumna() == columnas){
                                            creado = true;
                %>
                                            <td> 
                                           <b> <input type="submit" style="color:red" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoOcupado" disabled=""/> </b>
                                           </td>
                <%
                                        }else if(asientosSeleccionados != null && !asientosSeleccionados.isEmpty() && listaAsientosSeleccionados.contains(filas + "-" + columnas)){
                                           creado = true;
                %>
                                            <td> 
                                           <b> <input type="submit" style="color:blue" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoSeleccionado"/> </b>
                                           </td>
                <%
                                        }else{
                %>

                <%
                                        }
                                    }
                            if(!creado){
                                
                %>
                                   <td> 
                                   <b> <input type="submit" style="color:black" value="<%= Integer.toString(filas) + "-" + Integer.toString(columnas)%>" name="asientoLibreSeleccionado"/> </b>
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
                    
                %>
            </table>
            
            </form>
            
            <form method ="POST" action="ServletRegistroEnEvento?idEvento=<%= evento.getId()%>&asientosSeleccionados=<%=asientosSeleccionados%>">
                </br> <input type="submit" value="Comprar entradas">
            </form>
    <%
            
                        }

        }else{

    %>
        <form method ="POST" action="ServletRegistroEnEvento?idEvento=<%= evento.getId()%>">
            
            Introduzca número de entradas: 
            <input type="number" name="nEntradas" min="1" max="<%= limiteEntradas%>">
            <%
                if(limiteEntradas == 0){
            %>
                 <input type="submit" value="AGOTADAS" disabled="">
            <%
                }else{
            %>
                 <input type="submit" value="Comprar">
            <%
                }
            %>
            
        </form>
    <%
    }
    %>
        
        
    
</html>
