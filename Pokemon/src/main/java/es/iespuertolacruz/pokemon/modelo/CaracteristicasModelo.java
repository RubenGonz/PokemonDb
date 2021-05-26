package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void modificar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET peso = " + caracteristicas.getPeso() + "," + 
            "altura = " + caracteristicas.getAltura() + "," +
            "especie = '" + caracteristicas.getEspecie() + "'," +
            "habilidad = '" + caracteristicas.getHabilidad() + "'," +
            "categoria = '" + caracteristicas.getCategoria() + "' " +
            "WHERE " + CLAVE + " = " + caracteristicas.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una caracteristica
     * 
     * @param id_caracteristica del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Caracteristicas buscar(int idCaracteristica) throws PersistenciaException {
        Caracteristicas caracteristica = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idCaracteristica + ";";
        ArrayList<Caracteristicas> lista = transformarACaracteristica(sql);
        if (!lista.isEmpty()) {
            caracteristica = lista.get(0);
        }
        return caracteristica;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Caracteristicas> transformarACaracteristica(String sql) throws PersistenciaException {
        ArrayList<Caracteristicas> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Caracteristicas caracteristicas = new Caracteristicas();
                caracteristicas.setId(resultSet.getInt(CLAVE));
                caracteristicas.setPeso(resultSet.getFloat("peso"));
                caracteristicas.setAltura(resultSet.getFloat("altura"));
                caracteristicas.setEspecie(resultSet.getString("especie"));               
                caracteristicas.setHabilidad(resultSet.getString("habilidad"));
                caracteristicas.setCategoria(resultSet.getString("categoria"));           
                lista.add(caracteristicas);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
