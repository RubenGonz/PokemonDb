package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase donde se ven lod objetos que equipa un pokemon
 */
public class PokemonEquipa {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    int idObjeto;

    // Constructores

    /**
     * Constructor por defecto
     */
    public PokemonEquipa() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex identificador del pokemon que tiene el objeto
     * @param idObjeto      identificador del pokemon que tiene
     */
    public PokemonEquipa(int numeroPokedex, int idObjeto) {
        this.numeroPokedex = numeroPokedex;
        this.idObjeto = idObjeto;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public PokemonEquipa(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.numeroPokedex = Integer.parseInt((String) elementos.get(0));
        this.idObjeto = Integer.parseInt((String) elementos.get(1));
    }

    // Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    // Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PokemonEquipa)) {
            return false;
        }
        PokemonEquipa pokemonEquipa = (PokemonEquipa) o;
        return numeroPokedex == pokemonEquipa.numeroPokedex && idObjeto == pokemonEquipa.idObjeto;
    }

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR + getIdObjeto();
    }

}
