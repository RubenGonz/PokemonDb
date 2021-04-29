package es.iespuertolacruz.pokemon.api;

/**
 * Clase de las estadisticas base de un pokemon
 */
public class EstadisticasBase {

    //Variables de la clase

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
     * @param psBase del pokemon
     * @param ataqueBase del pokemon
     * @param defensaBase del pokemon
     * @param ataqueEspecialBase del pokemon
     * @param defensaEspecialBase del pokemon
     * @param velocidadBase del pokemon
     */
    public EstadisticasBase(int psBase, int ataqueBase, int defensaBase, int ataqueEspecialBase, int defensaEspecialBase, int velocidadBase) {
        this.psBase = psBase;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.ataqueEspecialBase = ataqueEspecialBase;
        this.defensaEspecialBase = defensaEspecialBase;
        this.velocidadBase = velocidadBase;
    }

    //Getters and Setters

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

    //Funciones y metodos

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EstadisticasBase)) {
            return false;
        }
        EstadisticasBase estadisticasBase = (EstadisticasBase) o;
        return psBase == estadisticasBase.psBase && ataqueBase == estadisticasBase.ataqueBase && defensaBase == estadisticasBase.defensaBase && ataqueEspecialBase == estadisticasBase.ataqueEspecialBase && defensaEspecialBase == estadisticasBase.defensaEspecialBase && velocidadBase == estadisticasBase.velocidadBase;
    }

    @Override
    public String toString() {
        return "PsBase = " + getPsBase() +
            ", ataqueBase = " + getAtaqueBase() +
            ", defensaBase = " + getDefensaBase() +
            ", ataqueEspecialBase = " + getAtaqueEspecialBase() +
            ", defensaEspecialBase = " + getDefensaEspecialBase() +
            ", velocidadBase = " + getVelocidadBase() + ".";
    }

}
