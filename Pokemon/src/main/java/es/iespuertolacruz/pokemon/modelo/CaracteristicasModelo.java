package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class CaracteristicasModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "CARACTERISTICAS";
    private static final String CLAVE = "id_caracteristica";

    // Constructores

    public CaracteristicasModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(null, null);
    }

    public void insertar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + 
            caracteristicas.getId() + "," + 
            caracteristicas.getPeso() + "," + 
            caracteristicas.getAltura() + ",'" + 
            caracteristicas.getEspecie() + "','" + 
            caracteristicas.getHabilidad() + "','" + 
            caracteristicas.getCategoria() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + 
            caracteristicas.getId() + ";";
        persistencia.update(sql);
    }

    public Caracteristicas buscar(int idCaracteristica) throws PersistenciaException {
        return (Caracteristicas) persistencia.buscarCaracteristicaPorId(idCaracteristica);
    }

    public void modificar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET peso = " + caracteristicas.getPeso() + "," + 
            "altura = " + caracteristicas.getAltura() + "," +
            "especie = '" + caracteristicas.getEspecie() + "'," +
            "habilidad = '" + caracteristicas.getHabilidad() + "'," +
            "categoria = '" + caracteristicas.getCategoria() + "' " +
            "WHERE " + CLAVE + " = " + caracteristicas.getId() + ";";
        persistencia.update(sql);
    }

}
