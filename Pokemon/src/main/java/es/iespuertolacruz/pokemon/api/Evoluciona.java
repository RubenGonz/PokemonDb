package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase donde se ve a que a que pokemon evoluciona otro pokemon
 */
public class Evoluciona {
    
    //variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedexOrigen;
    int numeroPokedexDestino;
    String modoEvoluciona;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Evoluciona() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedexOrigen pokemon que va a evolucionar
     * @param numeroPokedexDestino pokemon al que evoluciona
     * @param modoEvoluciona manera en la que el pokemon evoluciona
     */
    public Evoluciona(int numeroPokedexOrigen, int numeroPokedexDestino, String modoEvoluciona) {
        this.numeroPokedexOrigen = numeroPokedexOrigen;
        this.numeroPokedexDestino = numeroPokedexDestino;
        this.modoEvoluciona = modoEvoluciona;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Evoluciona(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.numeroPokedexOrigen = Integer.parseInt((String) elementos.get(0));
        this.numeroPokedexDestino = Integer.parseInt((String) elementos.get(1));
        this.modoEvoluciona = (String) elementos.get(2);
    }

    //Getters and Setters

    public int getNumeroPokedexOrigen() {
        return this.numeroPokedexOrigen;
    }

    public void setNumeroPokedexOrigen(int numeroPokedexOrigen) {
        this.numeroPokedexOrigen = numeroPokedexOrigen;
    }

    public int getNumeroPokedexDestino() {
        return this.numeroPokedexDestino;
    }

    public void setNumeroPokedexDestino(int numeroPokedexDestino) {
        this.numeroPokedexDestino = numeroPokedexDestino;
    }

    public String getModoEvoluciona() {
        return this.modoEvoluciona;
    }

    public void setModoEvoluciona(String modoEvoluciona) {
        this.modoEvoluciona = modoEvoluciona;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getNumeroPokedexOrigen() + DELIMITADOR +
            getNumeroPokedexDestino() + DELIMITADOR +
            getModoEvoluciona();
    }

}
