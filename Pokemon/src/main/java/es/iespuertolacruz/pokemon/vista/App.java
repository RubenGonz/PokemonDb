package es.iespuertolacruz.pokemon.vista;

import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    
      public static void main(String[] args)  {
      
    }

    private void menuUsuario(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
         while (!salir) {

                System.out.println("Bienvenido usuario");
                System.out.println(" Que quieres buscar");
                System.out.println("1. buscar por pokemon ");
                System.out.println("2. buscar por entrenadores .");
                System.out.println("3. buscar por movimientos  .");
                System.out.println("4. buscar por Objeto  .");
                System.out.println("5. Salir");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Has seleccionado la opcion  buscar por pokemon  ");
                            break;
                        case 2:
                            System.out.println("Has seleccionado la opcion buscar por entrenadores ");
                            break;
                        case 3:
                            System.out.println("Has seleccionado la opcion ebuscar por movimientos ");
                            break;
                        case 4:
                            System.out.println("Has seleccionado la opcion buscar por Objeto ");
                            break;
                        case 5:
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

    private void menuAdmin(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
    
        while (!salir) {

            System.out.println("Bienvenido administrador");
            System.out.println("1. para crear  datos .");
            System.out.println("2. para insertar datos .");
            System.out.println("3. para eliminar informacion .");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion Crear ");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion insertar ");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion eliminar ");
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
