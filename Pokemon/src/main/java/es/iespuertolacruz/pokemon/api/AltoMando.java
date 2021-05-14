package es.iespuertolacruz.pokemon.api;

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
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getTipoPrincipal();
    }

}
