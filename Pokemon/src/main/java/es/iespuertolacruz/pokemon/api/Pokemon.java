package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase principal de pokemon
 */
public class Pokemon {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    String nombre;
    int caracteristicas;
    int estadisticasBase;

    // Constructores

    /**
     * Construcor por defecto
     */
    public Pokemon() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex    identificador del pokemon
     * @param nombre           del pokemon
     * @param caracteristicas  numero que relaciona al pokemon con sus
     *                         caracteristicas
     * @param estadisticasBase numero que relaciona al pokemon con sus estadisticas
     */
    public Pokemon(int numeroPokedex, String nombre, int caracteristicas, int estadisticasBase) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.estadisticasBase = estadisticasBase;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Pokemon(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.numeroPokedex = Integer.parseInt((String) elementos.get(0));
        this.nombre = (String) elementos.get(1);
        this.caracteristicas = Integer.parseInt((String) elementos.get(2));
        this.estadisticasBase = Integer.parseInt((String) elementos.get(3));
    }

    // Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(int caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getEstadisticasBase() {
        return this.estadisticasBase;
    }

    public void setEstadisticasBase(int estadisticasBase) {
        this.estadisticasBase = estadisticasBase;
    }

    // Metosos y funciones

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return numeroPokedex == pokemon.numeroPokedex && Objects.equals(nombre, pokemon.nombre)
                && caracteristicas == pokemon.caracteristicas && estadisticasBase == pokemon.estadisticasBase;
    }

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR + getNombre() + DELIMITADOR + getCaracteristicas() + DELIMITADOR
                + getEstadisticasBase();
    }

}