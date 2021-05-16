package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase del objeto objetoComun
 */
public class ObjetoComun {
    
    //Variables de clase
    
    private static final String DELIMITADOR = "'";
    int idObjeto;
    String efecto;

    //Constructores

    /**
     * Constructor por defecto
     */
    public ObjetoComun() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idObjeto identificador del objeto padre
     * @param efecto que provoca el objeto
     */
    public ObjetoComun(int idObjeto, String efecto) {
        this.idObjeto = idObjeto;
        this.efecto = efecto;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public ObjetoComun(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idObjeto = Integer.parseInt((String) elementos.get(0));
        this.efecto = (String) elementos.get(1);
    }

    // Getters and Setters

    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getEfecto() {
        return this.efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdObjeto() + DELIMITADOR +
            getEfecto();
    }

}
