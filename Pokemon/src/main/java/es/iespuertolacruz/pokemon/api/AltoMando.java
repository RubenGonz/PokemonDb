package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class AltoMando {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    String tipoPrincipal;

    //Constructores

    /**
     * Constructor por defecto
     */
    public AltoMando() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param tipoPrincipal tipo principal de los pokemon que usa el entrenador
     */
    public AltoMando(int idEntrenador, String tipoPrincipal) {
        this.idEntrenador = idEntrenador;
        this.tipoPrincipal = tipoPrincipal;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public AltoMando(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idEntrenador = Integer.parseInt((String) elementos.get(0));
        this.tipoPrincipal = (String) elementos.get(1);
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getTipoPrincipal() {
        return this.tipoPrincipal;
    }

    public void setTipoPrincipal(String tipoPrincipal) {
        this.tipoPrincipal = tipoPrincipal;
    }

    //Metodos y funciones


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AltoMando)) {
            return false;
        }
        AltoMando altoMando = (AltoMando) o;
        return idEntrenador == altoMando.idEntrenador && Objects.equals(tipoPrincipal, altoMando.tipoPrincipal);
    }
    
    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getTipoPrincipal();
    }

}
