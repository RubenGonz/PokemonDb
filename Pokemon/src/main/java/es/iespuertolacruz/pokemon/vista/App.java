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
        String password ="SoyMew" ;
        int login = 0;
        int intentos = 1;
        int maximo = 3;
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
                        do {
                            if (password == "SoyMew") {
                                password = sn.next();
                            } else {
                                // si la contraseña es incorrecta se solicita ingresar nuevamente
                                System.out.println("Contraseña INCORRECTA");
                                System.out.println("Ingrese la Contraseña nuevamente");
                                password = sn.next();

                                if (password == "SoyMew") {
                                    menu(sn);
                                }
                            }

                        } while (password != "SoyMew");
                        menuAdmin();
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

    private static void menu(Scanner sn) {
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

        System.out.println("Introduce la contrsenia");
        

        while (!salir) {

            System.out.println("Bienvenido al menu administrador");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. para insertar datos para pokemon  ");
            System.out.println("2. para eliminar informacion ");
            System.out.println("3. para modificar informacion ");
            System.out.println("4. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion insertar ");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion eliminar ");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion modificar");
                        break;
                    case 4:
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
   
}
