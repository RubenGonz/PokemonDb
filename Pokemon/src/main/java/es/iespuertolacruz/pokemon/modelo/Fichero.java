package es.iespuertolacruz.pokemon.modelo;

import java.io.File;
import java.util.Scanner;

import es.iespuertolacruz.pokemon.excepciones.FicheroException;

public class Fichero {

    /**
     * Funcion encargada de leer un fichero
     * 
     * @param nombreFichero nombre del fichero
     * @throws FicheroException error controlado
     */
    public String leer(String nombreFichero) throws FicheroException {
        StringBuilder informacion = null;
        File fichero = new File(nombreFichero);
        if (!validarFichero(fichero)) {
            throw new FicheroException("El fichero a leer no existe");
        }
        try (Scanner scanner = new Scanner(fichero)) {
            informacion = new StringBuilder();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                informacion.append(linea + "\n");
            }
        } catch (Exception e) {
            throw new FicheroException("Se ha producido un error en la lectura del fichero", e);
        }
        return informacion.toString();
    }

    /**
     * Funcion que verifica si el fichero existe
     * 
     * @param fichero a verificar
     * @return true si existe o false si no existe
     */
    public boolean validarFichero(File fichero) {
        return fichero.exists();
    }
}