package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase del objeto pokeball
 */
public class Pokeball {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idObjeto;
    float ratio;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Pokeball() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idObjeto identificador del objeto padre
     * @param ratio porcentaje de acierto de la pokeball
     */
    public Pokeball(int idObjeto, float ratio) {
        this.idObjeto = idObjeto;
        this.ratio = ratio;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Pokeball(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idObjeto = Integer.parseInt((String) elementos.get(0));
        this.ratio =  Float.parseFloat((String) elementos.get(1));
    }

    //Getters and Setters
    
    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getIdObjeto() + DELIMITADOR +
            getRatio();
    }

}
