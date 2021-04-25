package es.iespuertolacruz.pokemon.api;

import java.util.Objects;

/**
 * Clase principal de pokemon
 */
public class Pokemon {

    // Variables de clase

    int numeroPokedex;
    String nombre;
    String tipoPrincipal;
    String tipoSecundario;
    Caracteristicas caracteristicas;
    EstadisticasBase estadisticasBase;

    //Constructores

    /**
     * Construcor por defecto
     */
    public Pokemon() {
    }

    /**
     * Constructor con todos los parametros de pokemon
     * @param numeroPokedex identificador del pokemon 
     * @param nombre del pokemon
     * @param tipoPrincipal tipo obligatorio del pokemon
     * @param tipoSecundario tipo opcional del pokemon
     * @param caracteristicas del pokemon
     * @param estadisticasBase del pokemon
     */
    public Pokemon(int numeroPokedex, String nombre, String tipoPrincipal, String tipoSecundario, Caracteristicas caracteristicas, EstadisticasBase estadisticasBase) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipoPrincipal = tipoPrincipal;
        this.tipoSecundario = tipoSecundario;
        this.caracteristicas = caracteristicas;
        this.estadisticasBase = estadisticasBase;
    }

    //Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPrincipal() {
        return this.tipoPrincipal;
    }

    public void setTipoPrincipal(String tipoPrincipal) {
        this.tipoPrincipal = tipoPrincipal;
    }

    public String getTipoSecundario() {
        return this.tipoSecundario;
    }

    public void setTipoSecundario(String tipoSecundario) {
        this.tipoSecundario = tipoSecundario;
    }

    public Caracteristicas getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public EstadisticasBase getEstadisticasBase() {
        return this.estadisticasBase;
    }

    public void setEstadisticasBase(EstadisticasBase estadisticasBase) {
        this.estadisticasBase = estadisticasBase;
    }

    //Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return numeroPokedex == pokemon.numeroPokedex && Objects.equals(nombre, pokemon.nombre) && Objects.equals(tipoPrincipal, pokemon.tipoPrincipal) && Objects.equals(tipoSecundario, pokemon.tipoSecundario) && Objects.equals(caracteristicas, pokemon.caracteristicas) && Objects.equals(estadisticasBase, pokemon.estadisticasBase);
    }

    @Override
    public String toString() {
        return "Pokemon numero " + getNumeroPokedex() + " " + getNombre() + "\n" +
            "TipoPrincipal = " + getTipoPrincipal() + ", tipoSecundario = " + getTipoSecundario() + "\n" +
            "Caracteristicas" + "\n" + 
            getCaracteristicas() + "\n" +
            "Estadisticas base" + "\n" + 
            getEstadisticasBase() + "\n";
    }

}