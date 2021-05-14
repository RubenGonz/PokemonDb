package es.iespuertolacruz.pokemon.api;

public class EntrenadorEquipa {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int idObjeto;
    int cantidad;

    // Constructores

    /**
     * Constructor por defecto
     */
    public EntrenadorEquipa() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador
     * @param idObjeto identificador del objeto
     * @param cantidad cantidad de objetos que tiene repetidos
     */
    public EntrenadorEquipa(int idEntrenador, int idObjeto, int cantidad) {
        this.idEntrenador = idEntrenador;
        this.idObjeto = idObjeto;
        this.cantidad = cantidad;
    }

    //Getters and Setters
    
    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Funciones y Metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getIdObjeto() + DELIMITADOR +
            getCantidad();
    }

}
