package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MovimientoModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public MovimientoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Movimiento movimiento) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + movimiento.getId() + "','" + movimiento.getNombre() + "','"
                + movimiento.getTipo() + "','" + movimiento.getCategoria() + "','" + movimiento.getPp() + "','"
                + movimiento.getPotencia() + "','" + movimiento.getCerteza() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Movimiento movimiento) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + movimiento.getId() + "';";
        persistencia.update(sql);
    }

    public Movimiento buscar(int id) throws PersistenciaException {
        return (Movimiento) persistencia.buscarElemento(id);
    }

    public void modificar(Movimiento movimiento) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" + movimiento.getNombre() + "','" + movimiento.getTipo()
                + "','" + movimiento.getCategoria() + "','" + movimiento.getPp() + "','" + movimiento.getPotencia()
                + "','" + movimiento.getCerteza() + "' WHERE " + CLAVE + " = '" + movimiento.getId() + "';";
        persistencia.update(sql);
    }
}
