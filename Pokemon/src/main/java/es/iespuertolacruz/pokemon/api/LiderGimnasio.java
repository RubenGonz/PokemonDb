package es.iespuertolacruz.pokemon.api;

/**
 * Clase del entrenador lider de gimnasio
 */
public class LiderGimnasio {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int medalla;

    //Constructores

    /**
     * Constructor por defecto
     */
    public LiderGimnasio() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param medalla medalla identificatoria del lider de gimnasio
     */
    public LiderGimnasio(int idEntrenador, int medalla) {
        this.idEntrenador = idEntrenador;
        this.medalla = medalla;
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getMedalla() {
        return this.medalla;
    }

    public void setMedalla(int medalla) {
        this.medalla = medalla;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getMedalla();
    }

}
