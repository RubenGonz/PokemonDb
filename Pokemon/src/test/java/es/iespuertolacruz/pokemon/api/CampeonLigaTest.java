package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampeonLigaTest {
    
    // Variables de la clase

    CampeonLiga campeonLiga1;
    CampeonLiga campeonLiga2;
    CampeonLiga campeonLiga3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        campeonLiga1 = crearCampeonLiga();
        campeonLiga2 = new CampeonLiga(1,"Johto");
        campeonLiga3 = new CampeonLiga("1'Johto");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Johto",campeonLiga1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(campeonLiga2,campeonLiga3,"Los objetos son iguales y equals no lo reconoce");
    }

    //Funciones y metodos

    private CampeonLiga crearCampeonLiga() {
        CampeonLiga campeonLiga;
        campeonLiga = new CampeonLiga();
        campeonLiga.setIdEntrenador(1);
        campeonLiga.setRegion("Johto");
        return campeonLiga;
    }
}
