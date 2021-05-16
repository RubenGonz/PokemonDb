package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Provoca(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idMovimiento = Integer.parseInt((String) elementos.get(0));
        this.idEstado = Integer.parseInt((String) elementos.get(1));
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
