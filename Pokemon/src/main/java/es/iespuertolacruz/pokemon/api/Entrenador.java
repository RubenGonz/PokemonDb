package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase principal de entrenador
 */
public class Entrenador {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Entrenador() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id     identificador del entrenador
     * @param nombre del entrenador
     */
    public Entrenador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Entrenador(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.id = Integer.parseInt((String) elementos.get(0));
        this.nombre = (String) elementos.get(1);
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

    // Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Entrenador)) {
            return false;
        }
        Entrenador entrenador = (Entrenador) o;
        return id == entrenador.id && Objects.equals(nombre, entrenador.nombre);
    }

    @Override
    public String toString() {
        return getId() + DELIMITADOR + getNombre();
    }

}
