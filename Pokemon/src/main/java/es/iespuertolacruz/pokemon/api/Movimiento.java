package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase donde estan los atributos de movimiento
 */
public class Movimiento {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;
    String tipo;
    String categoria;
    int pp;
    int potencia;
    int certeza;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Movimiento() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id        identificador del movimiento
     * @param nombre    del movimiento
     * @param tipo      del movimiento
     * @param categoria del movimiento
     * @param pp        cantida de veces que lo puede ejecutar hasta recuperarse
     * @param potencia  fuerza del movimiento
     * @param certeza   porcentaje de acierto sobre 100
     */
    public Movimiento(int id, String nombre, String tipo, String categoria, int pp, int potencia, int certeza) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pp = pp;
        this.potencia = potencia;
        this.certeza = certeza;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Movimiento(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.id = Integer.parseInt((String) elementos.get(0));
        this.nombre = (String) elementos.get(1);
        this.tipo = (String) elementos.get(2);
        this.categoria = (String) elementos.get(3);
        this.pp = Integer.parseInt((String) elementos.get(4));
        this.potencia = Integer.parseInt((String) elementos.get(5));
        this.certeza = Integer.parseInt((String) elementos.get(6));
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPp() {
        return this.pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPotencia() {
        return this.potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getCerteza() {
        return this.certeza;
    }

    public void setCerteza(int certeza) {
        this.certeza = certeza;
    }

    // Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Movimiento)) {
            return false;
        }
        Movimiento movimiento = (Movimiento) o;
        return id == movimiento.id && Objects.equals(nombre, movimiento.nombre) && Objects.equals(tipo, movimiento.tipo)
                && Objects.equals(categoria, movimiento.categoria) && pp == movimiento.pp
                && potencia == movimiento.potencia && certeza == movimiento.certeza;
    }

    @Override
    public String toString() {
        return getId() + DELIMITADOR + getNombre() + DELIMITADOR + getTipo() + DELIMITADOR + getCategoria()
                + DELIMITADOR + getPp() + DELIMITADOR + getPotencia() + DELIMITADOR + getCerteza();
    }

}
