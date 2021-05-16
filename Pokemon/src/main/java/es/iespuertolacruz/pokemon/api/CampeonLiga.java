package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CampeonLiga {
    
    //Variable clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    String region;

    //Constructores

    /**
     * Constructor por defecto
     */
    public CampeonLiga() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param region de donde es el campeon
     */
    public CampeonLiga(int idEntrenador, String region) {
        this.idEntrenador = idEntrenador;
        this.region = region;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public CampeonLiga(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idEntrenador = Integer.parseInt((String) elementos.get(0));
        this.region = (String) elementos.get(1);
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getRegion();
    }

}
