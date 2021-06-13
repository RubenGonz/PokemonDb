<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Login</title>
        <link rel="shortcut icon" href="./Imagenes/Pokeball.png">
        <link rel="stylesheet" href="css.css">
        <link rel="stylesheet" href="general.css" />

    </head>

    <body>
      <header>
            <div class="logo">
                <a href="index.jsp"><img
                        src="https://fontmeme.com/permalink/210610/42500246a9232da5b50e573f025bfa81.png"
                        alt="Logo de Pokemon"></a>
            </div>
            <div></div>
            <div class="enlaceHeader"><a href="./index.jsp">Home</a></div>
            <div class="enlaceHeader"><a href="./signUp.jsp">Sign Up</a></div>
        </header>
        <div class="login">
            <h1>Administrador </h1>
            <br>
            <form method="post">
                <input type="password"  placeholder="Password" required="required" />
                <br>
                <a href="administrador.jsp" class="style-5"><input type="button" value="Enter"></a>
            </form>
        </div>
        
    </body>