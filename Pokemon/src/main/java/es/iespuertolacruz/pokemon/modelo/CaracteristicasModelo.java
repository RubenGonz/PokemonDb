package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class CaracteristicasModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public CaracteristicasModelo() throws PersistenciaException {
          persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
        }

    public void insertar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" +caracteristicas.getId()+"','"+ caracteristicas.getPeso() + "','"
                + caracteristicas.getAltura()+"','"+ caracteristicas.getEspecie()+ "','"+caracteristicas.getHabilidad()+ "');";
        persistencia.update(sql);
    }

    public void eliminar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + caracteristicas.getId() + "';";
        persistencia.update(sql);
    }

    public Caracteristicas buscar(int Id_caracteristica) throws PersistenciaException {
        return (Caracteristicas) persistencia.buscarElemento(Id_caracteristica);
    }

    public void modificar(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Id_caracteristica = '" +caracteristicas.getPeso() + "','"
                + caracteristicas.getAltura()+"','"+ caracteristicas.getEspecie()+ "','"+caracteristicas.getHabilidad()+  "' WHERE " + CLAVE
                + " = '" + caracteristicas.getId() + "';";
        persistencia.update(sql);
    }

}
