package es.iespuertolacruz.pokemon.api;

/**
 * Clase de las estadisticas base de un pokemon
 */
public class EstadisticasBase {

    //Variables de la clase

    int idEstadisticaBase;
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
     * @param idEstadisticaBase
     * @param psBase
     * @param ataqueBase
     * @param defensaBase
     * @param ataqueEspecialBase
     * @param defensaEspecialBase
     * @param velocidadBase
     */
    public EstadisticasBase(int idEstadisticaBase, int psBase, int ataqueBase, int defensaBase, int ataqueEspecialBase, int defensaEspecialBase, int velocidadBase) {
        this.idEstadisticaBase = idEstadisticaBase;
        this.psBase = psBase;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.ataqueEspecialBase = ataqueEspecialBase;
        this.defensaEspecialBase = defensaEspecialBase;
        this.velocidadBase = velocidadBase;
    }

    //Getter and Setter

    public int getIdEstadisticaBase() {
        return this.idEstadisticaBase;
    }

    public void setIdEstadisticaBase(int idEstadisticaBase) {
        this.idEstadisticaBase = idEstadisticaBase;
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

    //Metodos y funciones

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EstadisticasBase)) {
            return false;
        }
        EstadisticasBase estadisticasBase = (EstadisticasBase) o;
        return idEstadisticaBase == estadisticasBase.idEstadisticaBase && psBase == estadisticasBase.psBase && ataqueBase == estadisticasBase.ataqueBase && defensaBase == estadisticasBase.defensaBase && ataqueEspecialBase == estadisticasBase.ataqueEspecialBase && defensaEspecialBase == estadisticasBase.defensaEspecialBase && velocidadBase == estadisticasBase.velocidadBase;
    }

    @Override
    public String toString() {
        return "idEstadisticaBase='" + getIdEstadisticaBase() + "'" +
            ", psBase='" + getPsBase() + "'" +
            ", ataqueBase='" + getAtaqueBase() + "'" +
            ", defensaBase='" + getDefensaBase() + "'" +
            ", ataqueEspecialBase='" + getAtaqueEspecialBase() + "'" +
            ", defensaEspecialBase='" + getDefensaEspecialBase() + "'" +
            ", velocidadBase='" + getVelocidadBase();
    }


}
