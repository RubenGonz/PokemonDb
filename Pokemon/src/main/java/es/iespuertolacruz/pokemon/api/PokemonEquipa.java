package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ven lod objetos que equipa un pokemon
 */
public class PokemonEquipa {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    int idObjeto;

    //Constructores

    /**
     * Constructor por defecto
     */
    public PokemonEquipa() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex identificador del pokemon que tiene el objeto
     * @param idObjeto identificador del pokemon que tiene
     */
    public PokemonEquipa(int numeroPokedex, int idObjeto) {
        this.numeroPokedex = numeroPokedex;
        this.idObjeto = idObjeto;
    }

    //Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public int getIdObjeto() {
        return this.idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getIdObjeto();
    }

}
