package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Conoce(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.numeroPokedex = Integer.parseInt((String) elementos.get(0));
        this.idMovimiento = Integer.parseInt((String) elementos.get(1));
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Conoce)) {
            return false;
        }
        Conoce conoce = (Conoce) o;
        return numeroPokedex == conoce.numeroPokedex && idMovimiento == conoce.idMovimiento;
    }    

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getIdMovimiento();
    }

}
