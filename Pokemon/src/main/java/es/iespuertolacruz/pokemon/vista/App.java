package es.iespuertolacruz.pokemon.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws InterruptedException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
       
      
       try {
           final String os = System.getProperty( "os.name");
            if ( os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }else{
                Runtime.getRuntime().exec("clear");
            }

       }catch(final Exception e){

       }
     

        while (!salir) {

            System.out.println("1. Modo Usuario 1");
            System.out.println("2. Modo Admin 2");
            System.out.println("3. Salir");

            try {
               
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                
                switch (opcion) {
                  
                    case 1:
                        System.out.println("Has seleccionado  Modo Usuario ");
                        while (!salir) {

                            System.out.println("1. Buscar  ");
                            System.out.println("2. Salir");

                            try {
                        
                                System.out.println("Escribe una de las opciones");
                                opcion = sn.nextInt();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Has seleccionado la opcion Buscar ");
                                        System.out.println("Que quiere buscar ");
                                        break;
                                    case 2:
                                        salir = true;
                                        break;
                                    default:
                                        System.out.println("Solo números entre 1 y 2");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debes insertar un número");
                                sn.next();
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Has seleccionado Modo Admin ");

                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
}
