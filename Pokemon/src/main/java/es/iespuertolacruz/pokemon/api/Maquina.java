package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Maquina(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.idObjeto = Integer.parseInt((String) elementos.get(0));
        this.idMovimiento = Integer.parseInt((String) elementos.get(1));
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Maquina)) {
            return false;
        }
        Maquina maquina = (Maquina) o;
        return idObjeto == maquina.idObjeto && idMovimiento == maquina.idMovimiento;
    }

    @Override
    public String toString() {
        return getIdObjeto() + DELIMITADOR +
            getIdMovimiento();
    }

}
