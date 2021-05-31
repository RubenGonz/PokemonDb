package es.iespuertolacruz.pokemon.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.controller.AltoMandoController;
import es.iespuertolacruz.pokemon.controller.CampeonLigaController;
import es.iespuertolacruz.pokemon.controller.CaracteristicasController;
import es.iespuertolacruz.pokemon.controller.ConoceController;
import es.iespuertolacruz.pokemon.controller.EntrenadorCasualController;
import es.iespuertolacruz.pokemon.controller.EntrenadorController;
import es.iespuertolacruz.pokemon.controller.EntrenadorEquipaController;
import es.iespuertolacruz.pokemon.controller.EstadisticasBaseController;
import es.iespuertolacruz.pokemon.controller.EstadoController;
import es.iespuertolacruz.pokemon.controller.EvolucionaController;
import es.iespuertolacruz.pokemon.controller.LiderGimnasioController;
import es.iespuertolacruz.pokemon.controller.MovimientoController;
import es.iespuertolacruz.pokemon.controller.ObjetoController;
import es.iespuertolacruz.pokemon.controller.PokemonController;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class App {

    // variables 

    Caracteristicas caracteristicas;
    CaracteristicasController caracteristicasController;
    static Entrenador entrenador;
    EntrenadorController entrenadorController;
    static EstadisticasBase estadisticasBase;
    EstadisticasBaseController estadisticasBaseController;
    static Pokemon pokemon;
    PokemonController pokemonController;
    static Objeto objeto;
    ObjetoController objetoController;
    Movimiento movimiento;
    MovimientoController movimientoController;

    public static void main(String[] args) {
        menuPrincipal();
    }
    
    public static void menuPrincipal() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido PokemonDb");
            System.out.println("¿Usted que rol tienes?");
            System.out.println("1. Usuario");
            System.out.println("2. Admin");
            System.out.println("0. Salir");
            try {
                System.out.println("Escribe una de las 3 opciones que hay:");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        menuUsuario();
                        break;
                    case 2:
                        AdminContasenia();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número ");
                sn.next();
            }
        }
    }

    public static void menuUsuario() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al menu usuario");
            System.out.println("¿Que quieres buscar?");
            System.out.println("1. Buscar por pokemon");
            System.out.println("2. Buscar por entrenador");
            System.out.println("3. Buscar por objecto");
            System.out.println("4. Buscar  por movimiento");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("1.Has seleccionado la opcion buscar por pokemon");
                        menuBuscarPokemon();
                        break;
                    case 2:
                        System.out.println("2.Has seleccionado la opcion buscar por entrenador");
                        menuBuscarEntrenador();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion buscar por objecto ");
                        menuBuscarobjeto();
                        break;
                    case 4:
                        System.out.println("4.Has seleccionado la opcion buscar por movimiento");
                        menuBuscarMovivientos();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

    private static void menuAdmin() throws PokemonException, PersistenciaException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al menu administrador");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1.Insertar");
            System.out.println("2.Eliminar");
            System.out.println("3.Modificar");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion insertar ");
                        menuInsertar();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion eliminar ");
                        menuEliminar();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion modificar");
                        menuModificar();
                        break;
                    case 55:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
    
    private static void menuInsertar() throws PokemonException, PersistenciaException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al  opcion insertar");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Insertar por pokemon");
            System.out.println("2. Insertar por entrenador");
            System.out.println("3. Insertar por objecto");
            System.out.println("4. Insertar  por movimiento");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                
                
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion insertar por pokemon");
                        System.out.println("Di  que pokemon quuieres insertar");
                        
                        
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion insertar  por entrenador");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion insertar  por objecto");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion insertar un movimiento ");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    private static void menuEliminar() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {
            System.out.println("Bienvenido al  opcion Eliminar");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Eliminar por pokemon");
            System.out.println("2. Eliminar por entrenador");
            System.out.println("3. Eliminar por objecto");
            System.out.println("4. Eliminar  por movimiento");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion eliminar un pokemon");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion eliminar  por entrenador");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion eliminar por objecto");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion eliminar por movimiento");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    private static void menuModificar() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al  opcion Modificar");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Modificar por pokemon");
            System.out.println("2. Modificar por entrenador");
            System.out.println("3. Modificar por objecto");
            System.out.println("4. Modificar  por movimiento");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion modificar por pokemon");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion modificar por entrenador");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion modificar por objecto");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion modificar por movimiento");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    public static void menuBuscarPokemon() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al menu buscar por pokemon");
            System.out.println("¿Que quieres buscar?");
            System.out.println("1. Buscar Un pokemon por su numero en la pokedex");
            System.out.println("2. Buscar Un pokemon por su nombre");
            System.out.println("3. Buscar Pokemons por su tipo");
            System.out.println("4. Buscar Los pokemon de un entrenador");
            System.out.println("5. Buscar Pokemons que tienen un movimiento");
            System.out.println("6. Buscar Los pokemon que tiene un entrenador");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion Buscar Un pokemon por su numero en la pokedex");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion Buscar Un pokemon por su nombre");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion Buscar Pokemons por su tipo");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion Buscar Los pokemon de un entrenador");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion Buscar Pokemons que tienen un movimiento");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion Buscar Los pokemon que tiene un entrenador");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
    
    public static void menuBuscarEntrenador() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al menu buscar por pokemon");
            System.out.println("¿Que quieres buscar?");
            System.out.println("1. Buscar Un entrenador por nombre");
            System.out.println("2. Buscar Un entrenador por sus pokemons");
            System.out.println("3. Buscar Un entrenador por la cantidad de medallas que tiene");
            System.out.println("4. Buscar Los objectos que tiene un entrenador");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion Buscar  Un entrenador por nombre");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion Buscar Un entrenador por sus pokemons");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion Buscar Un entrenador por la cantidad de medallas que tiene");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion Buscar Los objectos que tiene un entrenador");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
    
    public static void menuBuscarMovivientos() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al menu buscar por pokemon");
            System.out.println("¿Que quieres buscar?");
            System.out.println("1. Buscar Movimiento por su nombre");
            System.out.println("2. Buscar Los movimientos que tiene un pokemon");
            System.out.println("3. Buscar Los movimientos por el estado que procova");
            System.out.println("4. Buscar Los moviminetos por su categoria");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion Buscar  Un entrenador por nombre");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion BuscarLos movimientos que tiene un pokemon");
                        break;
                    case 3:
                        System.out.println(
                                "Has seleccionado la opcion Buscar Los movimientos por el estado que procova");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion Buscar Los moviminetos por su categoria");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

    public static void menuBuscarobjeto() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
        while (!salir) {

            System.out.println("Bienvenido al menu buscar por pokemon");
            System.out.println("¿Que quieres buscar?");
            System.out.println("1. Buscar Un objeto por su nombre");
            System.out.println("2. Buscar Objetos por el modo de obtencion");
            System.out.println("3. Buscar Una maquina por el movimiento que tiene");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion Buscar  Un objeto por su nombre");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion Buscar Objetos por el modo de obtencion");
                        break;
                    case 3:
                        System.out
                                .println("Has seleccionado la opcion Buscar Los movimientos por el estado que procova");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion Buscar Una maquina por el movimiento que tiene");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
    private static void AdminContasenia() throws PokemonException, PersistenciaException {
        Scanner sn = new Scanner(System.in);
        String password = "SoyMew";
        System.out.println("Ingrese la Contraseña ");
        password = sn.nextLine();

        if (password.equals("SoyMew")) {
            menuAdmin();
        } else {
            // si la contraseña es incorrecta se solicita ingresar nuevamente
            System.out.println("Contraseña INCORRECTA");
            System.out.println("Ingrese la Contraseña nuevamente");
            password = sn.next();

            if (password.equals("SoyMew")) {
                password = sn.nextLine();
                menuAdmin();
            }
            menuPrincipal();
        }

    }
}
