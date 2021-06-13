<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <meta name='author' content='Ruben Gonzalez Rodriguez'>
        <title>PobemonDb</title>
        <link rel="shortcut icon" href="./Imagenes/Pokeball.png">
        <link rel="stylesheet" href="general.css" />
        <link rel="stylesheet" href="login.css" />
        <link rel="stylesheet" href="pokeball.css" />
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
            <div class="enlaceHeader"><a href="./login.jsp">Login</a></div>
        </header>

        <main>
            <section class="entradilla">
                <a href="index.jsp"><img
                        src="https://fontmeme.com/permalink/210610/42500246a9232da5b50e573f025bfa81.png"
                        alt="Logo de Pokemon"></a>
            </section>

            <section class="formulario">
                <div class="cabeceraFormulario">
                    <h1>Sign Up</h1>
                </div>

                <div class="mainFormulario">
                    <form method="post">
                        <br>
                        <label for="nombre">Introduzca su nombre</label>
                        <input type="text" id="nombre" placeholder="Intruzca el nombre aquí" required="required" />
                        <br>
                        <label for="apellidos">Introduzca sus apellidos</label>
                        <input type="text" id="apellidos" placeholder="Intruzca los apellidos aquí" required="required" />
                        <br>
                        <label for="correo">Introduzca su correo</label>
                        <input type="text" id="correo" placeholder="Intruzca el correo aquí" required="required" />
                        <br>
                        <a href="index.jsp"><input class="boton" type="button" value="Terminar registro"></a>
                    </form>
                </div>
            </section>

            <section class="container">
                <div class="pokeball"></div>
            </section>
        </main>

        <footer>
            <div>
                <h1>Información del proyecto</h1>
                <div class="enlacesFooter">
                    <div>
                        <a href="https://github.com/RubenGonz/PokemonDb"><img src="./Imagenes/IconoGitHub.png"
                                alt="Icono de github"></a>
                        <a href="https://github.com/RubenGonz/PokemonDb">PokemonDb</a>
                    </div>
                    <div>
                        <a href="https://github.com/RubenGonz/PokemonDb/wiki/PokemonDb-base-de-datos"><img
                                src="./Imagenes/IconoGitHub.png" alt="Icono de github"></a>
                        <a href="https://github.com/RubenGonz/PokemonDb/wiki/PokemonDb-base-de-datos">Nuestra base de
                            datos</a>
                    </div>
                    <div>
                        <a href="https://github.com/RubenGonz/PokemonDb/wiki/Manual-del-Usuario"><img
                                src="./Imagenes/IconoGitHub.png" alt="Icono de github"></a>
                        <a href="https://github.com/RubenGonz/PokemonDb/wiki/Manual-del-Usuario">Manuales</a>
                    </div>
                </div>
            </div>

            <div>
                <h1>Contribuidores</h1>
                <div class="contribuidores">
                    <div class="enlacesFooter">
                        <div>
                            <a href="https://www.google.com/"><img src="./Imagenes/IconoGmail.png"
                                    alt="Icono de Gmail"></a>
                            <a href="https://www.google.com/">Ruben30303030@gmail.com</a>
                        </div>
                        <div>
                            <a href="https://github.com/RubenGonz"><img src="./Imagenes/IconoGitHub.png"
                                    alt="Icono de github"></a>
                            <a href="https://github.com/RubenGonz">RubenGonz</a>
                        </div>
                        <div>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001"><img
                                    src="./Imagenes/IconoFacebook.png" alt="Icono de facebook"></a>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001">Rubén González
                                Rodríguez</a>
                        </div>
                        <div>
                            <img src="./Imagenes/IconoTelefono.png" alt="Icono de un telefono"></a>
                            <span>RubenGonz</span>
                        </div>
                    </div>
                    <div class="enlacesFooter">
                        <div>
                            <a href="https://www.google.com/"><img src="./Imagenes/IconoGmail.png"
                                    alt="Icono de Gmail"></a>
                            <a href="https://www.google.com/">Jose pon tus cosas</a>
                        </div>
                        <div>
                            <a href="https://github.com/RubenGonz"><img src="./Imagenes/IconoGitHub.png"
                                    alt="Icono de github"></a>
                            <a href="https://github.com/RubenGonz">Jose pon tus cosas</a>
                        </div>
                        <div>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001"><img
                                    src="./Imagenes/IconoFacebook.png" alt="Icono de facebook"></a>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001">Jose pon tus cosas</a>
                        </div>
                        <div>
                            <img src="./Imagenes/IconoTelefono.png" alt="Icono de un telefono"></a>
                            <span>Jose pon tus cosas</span>
                        </div>
                    </div>
                </div>
            </div>

            <div>
                <h1>Ayudanos a mejorar</h1>
                <textarea name="IdeasMejoras" id="" placeholder="Escribe tus ideas aqui" cols="40" rows="5"></textarea>
            </div>
        </footer>

    </body>

    </html>