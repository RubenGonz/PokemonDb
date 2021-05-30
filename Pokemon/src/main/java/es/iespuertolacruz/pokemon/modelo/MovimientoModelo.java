package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MovimientoModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "MOVIMIENTO";
    private static final String CLAVE = "id_movimiento";

    // Constructores
    public MovimientoModelo(DdBbSqLite persistencia) {
        this.persistencia = persistencia;
    }

    // Metodos y funciones

    /**
     * Metodo que inserta movimiento en la base de datos
     * 
     * @param movimiento
     * @throws PersistenciaException
     */
    public void insertar(Movimiento movimiento) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + movimiento.getId() + "," + movimiento.getNombre() + ","
                + movimiento.getTipo() + "," + movimiento.getCategoria() + "," + movimiento.getPp() + ","
                + movimiento.getPotencia() + "," + movimiento.getCerteza() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina movimiento de la base de datos
     * 
     * @param movimiento
     * @throws PersistenciaException
     */
    public void eliminar(Movimiento movimiento) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + movimiento.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica movimiento de la base de datos
     * 
     * @param movimiento a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Movimiento movimiento) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = " + movimiento.getNombre() + movimiento.getTipo()
                + movimiento.getCategoria() + movimiento.getPp() + movimiento.getPotencia() + movimiento.getCerteza()
                + "WHERE " + CLAVE + " = " + movimiento.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener movimiento
     * 
     * @param Id del movimiento
     * @return movimiento buscado
     * @throws PersistenciaException con error controlado
     */
    public Movimiento buscar(int Id) throws PersistenciaException {
        Movimiento movimiento = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + Id + ";";
        ArrayList<Movimiento> lista = transformarAMovimiento(sql);
        if (!lista.isEmpty()) {
            movimiento = lista.get(0);
        }
        return movimiento;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Movimiento> transformarAMovimiento(String sql) throws PersistenciaException {
        ArrayList<Movimiento> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setId(resultSet.getInt(CLAVE));
                movimiento.setNombre(resultSet.getString("Nombre"));
                movimiento.setTipo(resultSet.getString("Tipo"));
                movimiento.setCategoria(resultSet.getString("Categoria"));
                movimiento.setPp(resultSet.getInt("Pp"));
                movimiento.setPotencia(resultSet.getInt("Potencia"));
                movimiento.setCerteza(resultSet.getInt("Certeza"));
                lista.add(movimiento);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
