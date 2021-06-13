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
            <div class="enlaceHeader"><a href="./signUp.jsp">Sign Up</a></div>
        </header>

        <main>
            <section class="entradilla">
                <a href="index.jsp"><img
                        src="https://fontmeme.com/permalink/210610/42500246a9232da5b50e573f025bfa81.png"
                        alt="Logo de Pokemon"></a>
            </section>

            <section class="formulario">
                <div class="cabeceraFormulario">
                    <h1>Buscar el pokemon que tienen un movimiento </h1>
                </div>
                <div class="mainFormulario">
                    <form method="post">
                        <br>
                        <label for="contraseña"> Introduca el pokemon que tienen un movimiento a buscar </label>
                        <br>
                        <input type="password" id="contraseña" placeholder="" required="required" />
                        <br>
                        <a href="administrador.jsp"><input class="boton" type="button" value="Buscar"></a>
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
                            <a href="mailto:ruben30303030@gmail.com"><img src="./Imagenes/IconoGmail.png"
                                    alt="Icono de Gmail"></a>
                            <a href="mailto:ruben30303030@gmail.com">Ruben30303030@gmail.com</a>
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
                            <span>650 341 025</span>
                        </div>
                    </div>
                    <div class="enlacesFooter">
                        <div>
                            <a href="https://www.google.com/"><img src="./Imagenes/IconoGmail.png"
                                    alt="Icono de Gmail"></a>
                            <a href="https://www.google.com/">Jose12linares@gmail.com</a>
                        </div>
                        <div>
                            <a href="https://github.com/RubenGonz"><img src="./Imagenes/IconoGitHub.png"
                                    alt="Icono de github"></a>
                            <a href="https://github.com/Joseman2000LR">Joseman2000LR</a>
                        </div>
                        <div>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001"><img
                                    src="./Imagenes/IconoFacebook.png" alt="Icono de facebook"></a>
                            <a href="https://www.facebook.com/profile.php?id=100011142169001">Jose Manuel Linares</a>
                        </div>
                        <div>
                            <img src="./Imagenes/IconoTelefono.png" alt="Icono de un telefono"></a>
                            <span>JoseMLinares</span>
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