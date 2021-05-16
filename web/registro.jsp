<%-- 
    Document   : registro.jsp
    Created on : 26-abr-2021, 19:36:19
    Author     : migue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <%        
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>
    <body>
        <h1>Registro de usuario</h1>
        <form method="POST" action="ServletRegistro">
            <%= strError %> <br/>
            Correo electrónico: <input type ="text" name="email" value=""/><br/>
            Contraseña: <input type="password" name="contrasenia" value="" /><br>
            Nombre: <input type ="text" name="nombre" value=""/><br/>
            Apellidos: <input type ="text" name="apellidos" value=""/><br/>
            Domicilio: <input type ="text" name="domicilio" value=""/><br/>
            Ciudad: <input type ="text" name="ciudad" value=""/><br/>
            Sexo: 
            <br> <input type="radio" id="hombre" name="genero" value="hombre">
            <label for="hombre">Hombre</label><br>
            <input type="radio" id="mujer" name="genero" value="mujer">
            <label for="female">Mujer</label><br>
            <input type="radio" id="otro" name="genero" value="otro">
            <label for="otro">Otro</label>
            </br>
            
            <input type="submit" value="Registrar" />
        </form>
    </body>
</html>
