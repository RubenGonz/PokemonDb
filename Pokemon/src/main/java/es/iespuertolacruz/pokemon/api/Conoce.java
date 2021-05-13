package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ve que movimientos puede tener un pokemon
 */
public class Conoce {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    int idMovimiento;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Conoce() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex numero del pokemon que conoce el movimiento
     * @param idMovimiento identificador del movimiento
     */
    public Conoce(int numeroPokedex, int idMovimiento) {
        this.numeroPokedex = numeroPokedex;
        this.idMovimiento = idMovimiento;
    }

    //Getters and Setters
    
    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public int getIdMovimiento() {
        return this.idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getIdMovimiento();
    }

}
