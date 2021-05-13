package es.iespuertolacruz.pokemon.api;

/**
 * Clase de las estadisticas base de un pokemon
 */
public class EstadisticasBase {

    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int id;
    int psBase;
    int ataqueBase;
    int defensaBase;
    int ataqueEspecialBase;
    int defensaEspecialBase;
    int velocidadBase;
    
    //Constructores

    /**
     * Constructor por defecto
     */
    public EstadisticasBase() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id del pokemon al que se refiere y el identificador del objeto
     * @param psBase vida del pokemon
     * @param ataqueBase ataque fisico del pokemon
     * @param defensaBase defensa fisica del pokemon
     * @param ataqueEspecialBase ataque especial del pokemon
     * @param defensaEspecialBase defensa especial del pokemon
     * @param velocidadBase velocidad del pokemomn
     */
    public EstadisticasBase(int id, int psBase, int ataqueBase, int defensaBase, int ataqueEspecialBase, int defensaEspecialBase, int velocidadBase) {
        this.id = id;
        this.psBase = psBase;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.ataqueEspecialBase = ataqueEspecialBase;
        this.defensaEspecialBase = defensaEspecialBase;
        this.velocidadBase = velocidadBase;
    }

    //Getters and Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPsBase() {
        return this.psBase;
    }

    public void setPsBase(int psBase) {
        this.psBase = psBase;
    }

    public int getAtaqueBase() {
        return this.ataqueBase;
    }

    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public int getDefensaBase() {
        return this.defensaBase;
    }

    public void setDefensaBase(int defensaBase) {
        this.defensaBase = defensaBase;
    }

    public int getAtaqueEspecialBase() {
        return this.ataqueEspecialBase;
    }

    public void setAtaqueEspecialBase(int ataqueEspecialBase) {
        this.ataqueEspecialBase = ataqueEspecialBase;
    }

    public int getDefensaEspecialBase() {
        return this.defensaEspecialBase;
    }

    public void setDefensaEspecialBase(int defensaEspecialBase) {
        this.defensaEspecialBase = defensaEspecialBase;
    }

    public int getVelocidadBase() {
        return this.velocidadBase;
    }

    public void setVelocidadBase(int velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    // Metodos y funciones

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getPsBase() + DELIMITADOR +
            getAtaqueBase() + DELIMITADOR +
            getDefensaBase() + DELIMITADOR +
            getAtaqueEspecialBase() + DELIMITADOR +
            getDefensaEspecialBase() + DELIMITADOR +
            getVelocidadBase();
    }


}
