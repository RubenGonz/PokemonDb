package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase donde se ven los tipos de los pokemon
 */
public class Pertenece {
    
    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    String tipo;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Pertenece() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex identificador del pokemon
     * @param tipo tipo del pokemon
     */
    public Pertenece(int numeroPokedex, String tipo) {
        this.numeroPokedex = numeroPokedex;
        this.tipo = tipo;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Pertenece(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.numeroPokedex = Integer.parseInt((String) elementos.get(0));
        this.tipo = (String) elementos.get(1);
    }

    //Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getTipo();
    }

}
