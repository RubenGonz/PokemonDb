package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde estan los atributos de movimiento
 */
public class Movimiento {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;
    String tipo;
    String categoria;
    int pp;
    int potencia;
    int certeza;
    int idEstado;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Movimiento() {
    }

    /**
     * Constructor con todos los parametros
     * @param id identificador del movimiento
     * @param nombre del movimiento
     * @param tipo del movimiento
     * @param categoria del movimiento
     * @param pp cantida de veces que lo puede ejecutar hasta recuperarse
     * @param potencia fuerza del movimiento
     * @param certeza porcentaje de acierto sobre 100
     * @param idEstado numero que lo relaciona con el estado si lo provocase
     */
    public Movimiento(int id, String nombre, String tipo, String categoria, int pp, int potencia, int certeza, int idEstado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pp = pp;
        this.potencia = potencia;
        this.certeza = certeza;
        this.idEstado = idEstado;
    }

    //Getters and Setters

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

    public int getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getNombre() + DELIMITADOR +
            getTipo() + DELIMITADOR +
            getCategoria() + DELIMITADOR +
            getPp() + DELIMITADOR +
            getPotencia() + DELIMITADOR +
            getCerteza() + DELIMITADOR +
            getIdEstado();
    }


}
