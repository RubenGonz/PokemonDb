package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase del entrenador entrenado casual
 */
public class EntrenadorCasual {
    
    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int cantidadMedallas;

    //Constructores

    /**
     * Constructor por defecto
     */
    public EntrenadorCasual() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param cantidadMedallas cantidad de medallas que ha conseguido el entrenador
     */
    public EntrenadorCasual(int idEntrenador, int cantidadMedallas) {
        this.idEntrenador = idEntrenador;
        this.cantidadMedallas = cantidadMedallas;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public EntrenadorCasual(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idEntrenador = Integer.parseInt((String) elementos.get(0));
        this.cantidadMedallas = Integer.parseInt((String) elementos.get(1));
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getCantidadMedallas() {
        return this.cantidadMedallas;
    }

    public void setCantidadMedallas(int cantidadMedallas) {
        this.cantidadMedallas = cantidadMedallas;
    }

    //Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EntrenadorCasual)) {
            return false;
        }
        EntrenadorCasual entrenadorCasual = (EntrenadorCasual) o;
        return idEntrenador == entrenadorCasual.idEntrenador && cantidadMedallas == entrenadorCasual.cantidadMedallas;
    }

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getCantidadMedallas();
    }

}
