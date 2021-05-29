package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de las estadisticas
 */
public class EstadisticasBaseModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ESTADISTICAS_BASE";
    private static final String CLAVE = "id_estadisticas_base";
    private static final String SQLCREARTABLA = "CREATE TABLE IF NOT EXISTS ESTADISTICAS_BASE ( "+
        "id_estadisticas_base INT CHECK (id_estadisticas_base > 0), "+
        "ps_base INT CHECK (ps_base > 0), "+
        "ataque_base INT CHECK (ataque_base > 0), "+
        "defensa_base INT CHECK (defensa_base > 0), "+
        "ataque_especial_base INT CHECK (ataque_especial_base > 0), "+
        "defensa_especial_base INT CHECK (defensa_especial_base > 0), "+
        "velocidad_base INT CHECK (velocidad_base > 0), "+
        "PRIMARY KEY (id_estadisticas_base));";

    // Constructores

    /**
     * Constructor de EstadisticasBaseModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     */
    public EstadisticasBaseModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, null, null, SQLCREARTABLA);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta estadisticasBases en la base de datos
     * 
     * @param estadisticasBases a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(EstadisticasBase estadisticasBases) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ( " 
        + estadisticasBases.getId() + ", "
        + estadisticasBases.getPsBase() + ", " 
        + estadisticasBases.getAtaqueBase() + ", "
        + estadisticasBases.getDefensaBase() + ", " 
        + estadisticasBases.getAtaqueEspecialBase() + ", "
        + estadisticasBases.getDefensaEspecialBase() + ", " 
        + estadisticasBases.getVelocidadBase() + ");";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina una EstadisticasBase de la base de datos
     * 
     * @param estadisticasBase a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(EstadisticasBase estadisticasBases) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + estadisticasBases.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica una EstadisticasBase de la base de datos
     * 
     * @param estadisticasBases a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(EstadisticasBase estadisticasBases) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET ps_base = " + estadisticasBases.getPsBase() + "," + "ataque_base = "
                + estadisticasBases.getAtaqueBase() + "," + "defensa_base = " + estadisticasBases.getDefensaBase() + ","
                + "ataque_especial_base = " + estadisticasBases.getAtaqueEspecialBase() + "," + "defensa_especial_base = "
                + estadisticasBases.getDefensaEspecialBase() + "," + "velocidad_base = "
                + estadisticasBases.getVelocidadBase() + " WHERE " + CLAVE + " = " + estadisticasBases.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una estadisticasBase
     * 
     * @param id_estadisticasBase del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public EstadisticasBase buscar(int idEstadisticasBase) throws PersistenciaException {
        EstadisticasBase estadisticasBase = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEstadisticasBase + ";";
        ArrayList<EstadisticasBase> lista = transformarAEstadisticasBase(sql);
        if (!lista.isEmpty()) {
            estadisticasBase = lista.get(0);
        }
        return estadisticasBase;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<EstadisticasBase> transformarAEstadisticasBase(String sql) throws PersistenciaException {
        ArrayList<EstadisticasBase> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EstadisticasBase estadisticasBases = new EstadisticasBase();
                estadisticasBases.setId(resultSet.getInt(CLAVE));
                estadisticasBases.setPsBase(resultSet.getInt("ps_base"));
                estadisticasBases.setAtaqueBase(resultSet.getInt("ataque_base"));
                estadisticasBases.setDefensaBase(resultSet.getInt("defensa_base"));
                estadisticasBases.setAtaqueEspecialBase(resultSet.getInt("ataque_especial_base"));
                estadisticasBases.setDefensaEspecialBase(resultSet.getInt("defensa_especial_base"));
                estadisticasBases.setVelocidadBase(resultSet.getInt("velocidad_base"));
                lista.add(estadisticasBases);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
