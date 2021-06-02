package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase donde se ven los posibles estados que puede provocar un movimiento
 */
public class Estado {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;
    int persistencia;
    String efecto;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Estado() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id           identificador del estado
     * @param nombre       del estado
     * @param persistencia 1 si el estado es duradero o 2 si no lo es
     * @param efecto       descripcion del estado
     */
    public Estado(int id, String nombre, int persistencia, String efecto) {
        this.id = id;
        this.nombre = nombre;
        this.persistencia = persistencia;
        this.efecto = efecto;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Estado(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.id = Integer.parseInt((String) elementos.get(0));
        this.nombre = (String) elementos.get(1);
        this.persistencia = Integer.parseInt((String) elementos.get(2));
        this.efecto = (String) elementos.get(3);
    }

    // Getters and Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPersistencia() {
        return this.persistencia;
    }

    public void setPersistencia(int persistencia) {
        this.persistencia = persistencia;
    }

    public String getEfecto() {
        return this.efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    // Metodos y funciones

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estado)) {
            return false;
        }
        Estado estado = (Estado) o;
        return id == estado.id && Objects.equals(nombre, estado.nombre) && persistencia == estado.persistencia
                && Objects.equals(efecto, estado.efecto);
    }

    @Override
    public String toString() {
        return getId() + DELIMITADOR + getNombre() + DELIMITADOR + getPersistencia() + DELIMITADOR + getEfecto();
    }

}
