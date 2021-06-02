package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase principal de los pokemon que tiene un entrenador
 */
public class Tiene {

    // Variables de la clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int numeroPokedex;
    int cantidad;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Tiene() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador  identificador del entrenador
     * @param numeroPokedex identificador del pokemon
     * @param cantidad      cantidad de pokemons que tiene repetidos
     */
    public Tiene(int idEntrenador, int numeroPokedex, int cantidad) {
        this.idEntrenador = idEntrenador;
        this.numeroPokedex = numeroPokedex;
        this.cantidad = cantidad;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Tiene(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idEntrenador = Integer.parseInt((String) elementos.get(0));
        this.numeroPokedex = Integer.parseInt((String) elementos.get(1));
        this.cantidad = Integer.parseInt((String) elementos.get(2));
    }

    // Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tiene)) {
            return false;
        }
        Tiene tiene = (Tiene) o;
        return idEntrenador == tiene.idEntrenador && numeroPokedex == tiene.numeroPokedex && cantidad == tiene.cantidad;
    }

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR + getNumeroPokedex() + DELIMITADOR + getCantidad();
    }

}
