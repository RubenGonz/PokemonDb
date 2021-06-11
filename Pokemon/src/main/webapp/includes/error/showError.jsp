<%@page import="es.ejemplos.jpexposito.exceptions.UsuarioException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Error Message</title></head>
<body>
<form action="" method="post" >

    <center>
        <br><br>
        <table width="90%" height="100px"
               style="  -moz-border-radius: 8px;  -webkit-border-radius: 8px;border-radius: 8px;border: 2px solid #467aa7;">
            <tr>
                <td style="color:#467aa7;">
                    <% 
                        UsuarioException e = (UsuarioException) exception;
                        String message = e.getMessage();
                    %>

                    <label><%=message %></label>
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>