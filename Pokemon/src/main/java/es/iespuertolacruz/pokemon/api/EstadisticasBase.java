package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public EstadisticasBase(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.id = Integer.parseInt((String) elementos.get(0));
        this.psBase = Integer.parseInt((String) elementos.get(1));
        this.ataqueBase = Integer.parseInt((String) elementos.get(2));
        this.defensaBase = Integer.parseInt((String) elementos.get(3));
        this.ataqueEspecialBase = Integer.parseInt((String) elementos.get(4));
        this.defensaEspecialBase = Integer.parseInt((String) elementos.get(5));
        this.velocidadBase = Integer.parseInt((String) elementos.get(6));
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EstadisticasBase)) {
            return false;
        }
        EstadisticasBase estadisticasBase = (EstadisticasBase) o;
        return id == estadisticasBase.id && psBase == estadisticasBase.psBase && ataqueBase == estadisticasBase.ataqueBase && defensaBase == estadisticasBase.defensaBase && ataqueEspecialBase == estadisticasBase.ataqueEspecialBase && defensaEspecialBase == estadisticasBase.defensaEspecialBase && velocidadBase == estadisticasBase.velocidadBase;
    }

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
