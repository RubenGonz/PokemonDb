package es.iespuertolacruz.pokemon.api;

/**
 * Clase del objeto maquina
 */
public class Maquina {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idObjeto;
    int idMovimiento;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Maquina() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idObjeto identificador del objeto padre
     * @param idMovimiento identificador del movimiento que contiene
     */
    public Maquina(int idObjeto, int idMovimiento) {
        this.idObjeto = idObjeto;
        this.idMovimiento = idMovimiento;
    }

    //Getters ans Setters

    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdMovimiento() {
        return this.idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdObjeto() + DELIMITADOR +
            getIdMovimiento();
    }

}
