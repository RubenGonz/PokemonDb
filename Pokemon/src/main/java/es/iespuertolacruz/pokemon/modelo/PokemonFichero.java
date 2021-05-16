package es.iespuertolacruz.pokemon.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokemonFichero {

    private static final String SE_HA_PRODUCIDO_UN_ERROR_EN_EL_VOLCADO_DEL_FICHERO = "Se ha producido un error en el volcado del fichero";
    private static final String RETORNO_CARRO = "\n";
    private static final String NOMBRE_FICHERO = "Fichero-pokemons.txt";

    /**
     * Metodo encargado de almacenar un pokemon en el fichero
     * 
     * @param pokemon a insertar
     * @throws PersistenciaException controlado
     */
    public void insertar(Pokemon pokemon) throws PersistenciaException {
        ArrayList<Pokemon> listado;
        listado = (ArrayList<Pokemon>) obtenerListado();
        listado.add(pokemon);
        try {
            crear(NOMBRE_FICHERO, listado.toString());
        } catch (PersistenciaException exception) {
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_EL_VOLCADO_DEL_FICHERO, exception);
        }
    }

    /**
     * Metodo encargado de eliminar un pokemon del fichero
     * 
     * @param pokemon a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Pokemon pokemon) throws PersistenciaException {
        ArrayList<Pokemon> listado;
        listado = (ArrayList<Pokemon>) obtenerListado();
        listado.remove(pokemon);
        try {
            crear(NOMBRE_FICHERO, listado.toString());
        } catch (PersistenciaException exception) {
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_EL_VOLCADO_DEL_FICHERO, exception);
        }
    }

    /**
     * Metodo encargado de modificar un elemento del fichero
     * 
     * @param pokemonAlmacenado elemento a actualizar
     * @param pokemon           elemento con la informacion actualizada
     * @throws PersistenciaException
     */
    public void modificar(Pokemon pokemonAlmacenado, Pokemon pokemon) throws PersistenciaException {
        ArrayList<Pokemon> listado = (ArrayList<Pokemon>) obtenerListado();
        int posicion = -1;
        posicion = listado.indexOf(pokemonAlmacenado);
        listado.remove(posicion);
        listado.add(posicion, pokemon);
        crear(NOMBRE_FICHERO, listado.toString());
    }

    /**
     * Funcion encargada de obtener el listado de pokemons del fichero
     * 
     * @return listado de pokemons
     * @throws PersistenciaException
     */
    public List<Pokemon> obtenerListado() throws PersistenciaException {
        ArrayList<Pokemon> lista = new ArrayList<>();
        File fichero = null;
        Scanner scanner = null;

        try {
            fichero = new File(NOMBRE_FICHERO);
            if (!validarFichero(fichero)) {
                throw new PersistenciaException("El fichero a leer no existe");
            }
            scanner = new Scanner(fichero);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine(); // Guardamos la linea en un String
                if (linea != null && !linea.isEmpty()) {
                    Pokemon pokemon = new Pokemon(linea);
                    lista.add(pokemon);
                }
            }
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la lectura del fichero", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return lista;
    }

    /**
     * Funcion encargada de leer un fichero
     * 
     * @param nombre nombre del fichero a leer
     * @throws PersistenciaException Error controlado en la lectura del fichero
     */
    public String leer(String nombre) throws PersistenciaException {
        StringBuilder informacion = null;
        File fichero = null;
        Scanner scanner = null;

        try {
            fichero = new File(nombre);
            if (!validarFichero(fichero)) {
                throw new PersistenciaException("El fichero a leer no existe");
            }
            informacion = new StringBuilder();
            scanner = new Scanner(fichero);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine(); // Guardamos la linea en un String
                informacion.append(linea + RETORNO_CARRO);
            }
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la lectura del fichero", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return informacion.toString();
    }

    /**
     * Metodo encargado de crear un fichero
     * 
     * @param nombre del fichero a crear
     * @throws PersistenciaException
     */
    public void crear(String nombre, String cadenaTexto) throws PersistenciaException {
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(nombre);
            fichero.write(cadenaTexto + "\n");
        } catch (Exception ex) {
            throw new PersistenciaException("Se ha producido un error en la escritura del fichero", ex);
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    throw new PersistenciaException("No ha sido podible cerrar el fichero", e);
                }
            }
        }
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

    /**
     * Metodo encargado de elimianr un fichero/directorio
     * 
     * @param nombre del fichero/directorio a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(String nombre) throws PersistenciaException {
        File fichero = new File(nombre);
        if (validarFichero(fichero)) {
            fichero.delete();
        } else {
            throw new PersistenciaException("No se puede eliminar un fichero que no existe");
        }
    }

    /**
     * Funcion que verifica si se trata de un directorio no
     * 
     * @param path de la ruta a validad
     * @return boolean Si/No se trata de un directorio
     */
    public boolean esDirectorio(String path) {
        File fichero = new File(path);
        return fichero.isDirectory();
    }
}
