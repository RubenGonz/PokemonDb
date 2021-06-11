<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Pagina de verificacion de Administrador</title>
    </head>
    <body>
        <h1>Verificacion Administrador</h1>

        <jsp:useBean id="validarAdministrador" class="es.ejemplos.jpexposito.ValidarAdministrador" />
        
        <jsp:setProperty name="validarAdministrador" property="Administrador" />
        <jsp:setProperty name="validarAdministrador" property="password"/>
          
        
        Información del Administrador<br/>
        <p>Username : <jsp:getProperty name="validarAdministrador" property="Administrador"/></p>
        <p>Password : <jsp:getProperty name="validarAdministrador" property="password"/></p>
          
        <%if(validarAdministrador.validate()){%>
            Ok! El Administrador es valido<br/>
        <%}else{%>
            Error! El Administrador es Invalido<br/>
        <%}%>  
    </body>
</html>