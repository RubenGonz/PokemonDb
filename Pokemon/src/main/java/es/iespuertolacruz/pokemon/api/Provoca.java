package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ven los estados que provoca un movimiento
 */
public class Provoca {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idMovimiento;
    int idEstado;

    //Constructores

    /**
     * Constructores por defecto
     */
    public Provoca() {
    }

    /**
     * Constructores con todos los parametros
     * 
     * @param idMovimiento identificador del movimiento
     * @param idEstado identificador del estado
     */
    public Provoca(int idMovimiento, int idEstado) {
        this.idMovimiento = idMovimiento;
        this.idEstado = idEstado;
    }

    //Getters and Setters

    public int getIdMovimiento() {
        return this.idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getIdMovimiento() + DELIMITADOR +
            getIdEstado();
    }

}
