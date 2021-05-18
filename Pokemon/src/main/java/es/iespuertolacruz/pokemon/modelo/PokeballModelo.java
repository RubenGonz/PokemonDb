package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Pokeball;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokeballModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public PokeballModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Pokeball pokeball) throws PersistenciaException {
        persistencia.insertarPokeball(pokeball);
    }

    public void eliminar(Pokeball pokeball) throws PersistenciaException {
        persistencia.eliminarPokeball(pokeball);
    }

    public Pokeball buscar(int idObjeto) throws PersistenciaException {
        return persistencia.obtenerPokeball(idObjeto);
    }

    public void modificar(Pokeball pokeball) throws PersistenciaException {
        persistencia.modificarPokeball(pokeball);
    }

}
