package es.iespuertolacruz.pokemon.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
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
            System.out.println("3. Salir");
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
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
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
            System.out.println("1. Buscar un pokemon por su numero en la pokedex");
            System.out.println("2. Buscar un pokemon por su nombre");
            System.out.println("3. Buscar pokemons por su tipo");
            System.out.println("4. Buscar la evolucion de un pokemon");
            System.out.println("5. Buscar los pokemon de un entrenador");
            System.out.println("6. Buscar pokemons que tienen un movimiento");
            System.out.println("7. Buscar los pokemon que tiene un entrenador");
            System.out.println("8. Buscar un entrenador por nombre");
            System.out.println("9. Buscar un entrenador por sus pokemons");
            System.out.println("10. Buscar un entrenador por la cantidad de medallas que tiene");
            System.out.println("11. Buscar los objectos que tiene un entrenador");
            System.out.println("12. Buscar movimiento por su nombre");
            System.out.println("13. Buscar los movimientos que tiene un pokemon");
            System.out.println("14. Buscar los movimientos por el estado que procova");
            System.out.println("15. Buscar los moviminetos por su categoria");
            System.out.println("16. Buscar un objeto por su nombre");
            System.out.println("17. Buscar objetos por el modo de obtencion");
            System.out.println("18. Buscar una maquina por el movimiento que tiene");
            System.out.println("19. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("1. Buscar un pokemon por su numero en la pokedex");
                        break;
                    case 2:
                        System.out.println("2. Buscar un pokemon por su nombre");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion buscar pokemons por su tipo ");
                        break;
                    case 4:
                        System.out.println("4. Buscar la evolucion de un pokemon");
                        break;
                    case 5:
                        System.out.println("5. Buscar los pokemon de un entrenador");
                        break;
                    case 6:
                        System.out.println("6. Buscar pokemons que tienen un movimiento");
                        break;
                    case 7:
                        System.out.println("7. Buscar los pokemon que tiene un entrenador");
                        break;
                    case 8:
                        System.out.println("8. Buscar un entrenador por nombre");
                        break;
                    case 9:
                        System.out.println("9. Buscar un entrenador por sus pokemons");
                        break;
                    case 10:
                        System.out.println("10. Buscar un entrenador por la cantidad de medallas que tiene");
                        break;
                    case 11:
                        System.out.println("11. Buscar los objectos que tiene un entrenador");
                        break;
                    case 12:
                        System.out.println("12. Buscar movimiento por su nombre");
                        break;
                    case 13:
                        System.out.println("13. Buscar los movimientos que tiene un pokemon");
                        break;
                    case 14:
                        System.out.println("14. Buscar los movimientos por el estado que procova");
                        break;
                    case 15:
                        System.out.println("15. Buscar los moviminetos por su categoria");
                        break;
                    case 16:
                        System.out.println("16. Buscar un objeto por su nombre");
                        break;
                    case 17:
                        System.out.println("17. Buscar objetos por el modo de obtencion");
                        break;
                    case 18:
                        System.out.println("18. Buscar una maquina por el movimiento que tiene");
                        break;
                    case 19:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

    private static void menuAdmin() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        String contrasenia = "SoyMew";
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al menu administrador");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1.Insertar");
            System.out.println("2.Eliminar");
            System.out.println("3.Modificar");
            System.out.println("4. Salir");

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
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

    private static void menuInsertar() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        String contrasenia = "SoyMew";
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al  opcion insertar");
            System.out.println("¿Que quieres hacer?");
            // insertar
            System.out.println("1. Insertar un pokemon por su numero en la pokedex");
            System.out.println("2. Insertar  un pokemon por su nombre");
            System.out.println("3. Insertar pokemons por su tipo");
            System.out.println("4. Insertar la evolucion de un pokemon");
            System.out.println("5. Insertar los pokemon de un entrenador");
            System.out.println("6. Insertar pokemon por un movimiento");
            System.out.println("7. Insertar los pokemon que tiene un entrenador");
            System.out.println("8. Insertar un entrenador por nombre");
            System.out.println("9. Insertar un entrenador por sus pokemons");
            System.out.println("10. Insertar un entrenador por la cantidad de medallas que tiene");
            System.out.println("11. Insertar los objectos que tiene un entrenador");
            System.out.println("12. Insertar movimiento por su nombre");
            System.out.println("13. Insertar los movimientos que tiene un pokemon");
            System.out.println("14. Insertar los movimientos por el estado que procova");
            System.out.println("15. Insertar los moviminetos por su categoria");
            System.out.println("16. Insertar un objeto por su nombre");
            System.out.println("17. Insertar objetos por el modo de obtencion");
            System.out.println("18. Insertar una maquina por el movimiento que tiene");
            System.out.println("19. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion insertar un pokemon por su numero en la pokedex ");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion insertar  un pokemon por su nombre ");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion insertar pokemons por su tipo ");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion insertar la evolucion de un pokemon ");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion insertar los pokemon de un entrenador");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion insertar pokemon por un movimiento ");
                        break;
                    case 7:
                        System.out.println("Has seleccionado la opcion insertar los pokemon que tiene un entrenador ");
                        break;
                    case 8:
                        System.out.println("Has seleccionado la opcion insertar un entrenador por nombre ");
                        break;
                    case 9:
                        System.out.println("Has seleccionado la opcion insertar un entrenador por sus pokemons ");
                        break;
                    case 10:
                        System.out.println("Has seleccionado la opcion insertar un entrenador por la cantidad de medallas que tiene ");
                        break;
                    case 11:
                        System.out.println("Has seleccionado la opcion insertar los objectos que tiene un entrenador ");
                        break;
                    case 12:
                        System.out.println("Has seleccionado la opcion insertar movimiento por su nombre ");
                        break;
                    case 13:
                        System.out.println("Has seleccionado la opcion insertar los movimientos que tiene un pokemon ");
                        break;
                    case 14:
                        System.out.println("Has seleccionado la opcion insertar los movimientos por el estado que procova ");
                        break;
                    case 15:
                        System.out.println("Has seleccionado la opcion insertar los moviminetos por su categoria ");
                        break;
                    case 16:
                        System.out.println("Has seleccionado la opcion insertar un objeto por su nombre ");
                        break;
                    case 17:
                        System.out.println("Has seleccionado la opcion insertar objetos por el modo de obtencion ");
                        break;
                    case 18:
                        System.out.println("Has seleccionado la opcion insertar una maquina por el movimiento que tiene");
                        break;
                    case 19:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 19");
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
        String contrasenia = "SoyMew";
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al  opcion Eliminar");
            System.out.println("¿Que quieres hacer?");
            // insertar
            System.out.println("1. Eliminar un pokemon por su numero en la pokedex");
            System.out.println("2. Eliminar  un pokemon por su nombre");
            System.out.println("3. Eliminar pokemons por su tipo");
            System.out.println("4. Eliminar la evolucion de un pokemon");
            System.out.println("5. Eliminar los pokemon de un entrenador");
            System.out.println("6. Eliminar pokemon por un movimiento");
            System.out.println("7. Eliminar los pokemon que tiene un entrenador");
            System.out.println("8. Eliminar un entrenador por nombre");
            System.out.println("9. Eliminar un entrenador por sus pokemons");
            System.out.println("10. Eliminar un entrenador por la cantidad de medallas que tiene");
            System.out.println("11. Eliminar los objectos que tiene un entrenador");
            System.out.println("12. Eliminar movimiento por su nombre");
            System.out.println("13. Eliminar los movimientos que tiene un pokemon");
            System.out.println("14. Eliminar los movimientos por el estado que procova");
            System.out.println("15. Eliminar los moviminetos por su categoria");
            System.out.println("16. Eliminar un objeto por su nombre");
            System.out.println("17. Eliminar objetos por el modo de obtencion");
            System.out.println("18. Eliminar una maquina por el movimiento que tiene");
            System.out.println("19. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out
                                .println("Has seleccionado la opcion eliminar un pokemon por su numero en la pokedex ");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion eliminar  un pokemon por su nombre ");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion eliminar pokemons por su tipo ");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion eliminar la evolucion de un pokemon ");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion eliminar los pokemon de un entrenador");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion eliminar pokemon por un movimiento ");
                        break;
                    case 7:
                        System.out.println("Has seleccionado la opcion eliminar los pokemon que tiene un entrenador ");
                        break;
                    case 8:
                        System.out.println("Has seleccionado la opcion eliminar un entrenador por nombre ");
                        break;
                    case 9:
                        System.out.println("Has seleccionado la opcion eliminar un entrenador por sus pokemons ");
                        break;
                    case 10:
                        System.out.println(
                                "Has seleccionado la opcion eliminar un entrenador por la cantidad de medallas que tiene ");
                        break;
                    case 11:
                        System.out.println("Has seleccionado la opcion eliminar los objectos que tiene un entrenador ");
                        break;
                    case 12:
                        System.out.println("Has seleccionado la opcion eliminar movimiento por su nombre ");
                        break;
                    case 13:
                        System.out.println("Has seleccionado la opcion eliminar los movimientos que tiene un pokemon ");
                        break;
                    case 14:
                        System.out.println(
                                "Has seleccionado la opcion eliminar los movimientos por el estado que procova ");
                        break;
                    case 15:
                        System.out.println("Has seleccionado la opcion eliminar los moviminetos por su categoria ");
                        break;
                    case 16:
                        System.out.println("Has seleccionado la opcion eliminar un objeto por su nombre ");
                        break;
                    case 17:
                        System.out.println("Has seleccionado la opcion eliminar objetos por el modo de obtencion ");
                        break;
                    case 18:
                        System.out
                                .println("Has seleccionado la opcion eliminar una maquina por el movimiento que tiene");
                        break;
                    case 19:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 19");
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
        String contrasenia = "SoyMew";
        int opcion; // Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Bienvenido al  opcion modificar");
            System.out.println("¿Que quieres hacer?");
            // insertar
            System.out.println("1. Modificar un pokemon por su numero en la pokedex");
            System.out.println("2. Modificar  un pokemon por su nombre");
            System.out.println("3. Modificar pokemons por su tipo");
            System.out.println("4. Modificar la evolucion de un pokemon");
            System.out.println("5. Modificar los pokemon de un entrenador");
            System.out.println("6. Modificar pokemon por un movimiento");
            System.out.println("7. Modificar los pokemon que tiene un entrenador");
            System.out.println("8. Modificar un entrenador por nombre");
            System.out.println("9. Modificar un entrenador por sus pokemons");
            System.out.println("10. Modificar un entrenador por la cantidad de medallas que tiene");
            System.out.println("11. Modificar los objectos que tiene un entrenador");
            System.out.println("12. Modificar movimiento por su nombre");
            System.out.println("13. Modificar los movimientos que tiene un pokemon");
            System.out.println("14. Modificar los movimientos por el estado que procova");
            System.out.println("15. Modificar los moviminetos por su categoria");
            System.out.println("16. Modificar un objeto por su nombre");
            System.out.println("17. Modificar objetos por el modo de obtencion");
            System.out.println("18. Modificar una maquina por el movimiento que tiene");
            System.out.println("19. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out
                                .println("Has seleccionado la opcion modificar un pokemon por su numero en la pokedex ");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcionmodificar  un pokemon por su nombre ");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion modificar pokemons por su tipo ");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion modificar la evolucion de un pokemon ");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion modificar los pokemon de un entrenador");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion modificar pokemon por un movimiento ");
                        break;
                    case 7:
                        System.out.println("Has seleccionado la opcion modificar los pokemon que tiene un entrenador ");
                        break;
                    case 8:
                        System.out.println("Has seleccionado la opcion modificar un entrenador por nombre ");
                        break;
                    case 9:
                        System.out.println("Has seleccionado la opcion modificar un entrenador por sus pokemons ");
                        break;
                    case 10:
                        System.out.println(
                                "Has seleccionado la opcion modificar un entrenador por la cantidad de medallas que tiene ");
                        break;
                    case 11:
                        System.out.println("Has seleccionado la opcion modificar los objectos que tiene un entrenador ");
                        break;
                    case 12:
                        System.out.println("Has seleccionado la opcion modificar movimiento por su nombre ");
                        break;
                    case 13:
                        System.out.println("Has seleccionado la opcion modificar los movimientos que tiene un pokemon ");
                        break;
                    case 14:
                        System.out.println(
                                "Has seleccionado la opcion modificar los movimientos por el estado que procova ");
                        break;
                    case 15:
                        System.out.println("Has seleccionado la opcion modificar los moviminetos por su categoria ");
                        break;
                    case 16:
                        System.out.println("Has seleccionado la opcion modificar un objeto por su nombre ");
                        break;
                    case 17:
                        System.out.println("Has seleccionado la opcion modificar objetos por el modo de obtencion ");
                        break;
                    case 18:
                        System.out
                                .println("Has seleccionado la opcion modificar una maquina por el movimiento que tiene");
                        break;
                    case 19:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 19");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    private static void AdminContasenia() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        String contrasenia = "SoyMew";
        int opcion; // Guardaremos la opcion del usuario
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
