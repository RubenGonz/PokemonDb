package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase del entrenador villano
 */
public class Villano {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    String proposito;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Villano() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param proposito    texto que describe su proposito como villano
     */
    public Villano(int idEntrenador, String proposito) {
        this.idEntrenador = idEntrenador;
        this.proposito = proposito;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Villano(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idEntrenador = Integer.parseInt((String) elementos.get(0));
        this.proposito = (String) elementos.get(1);
    }

    // Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getProposito() {
        return this.proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    // Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Villano)) {
            return false;
        }
        Villano villano = (Villano) o;
        return idEntrenador == villano.idEntrenador && Objects.equals(proposito, villano.proposito);
    }

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR + getProposito();
    }

}
