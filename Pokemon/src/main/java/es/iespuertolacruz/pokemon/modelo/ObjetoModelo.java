package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ObjetoModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public ObjetoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Objeto objeto) throws PersistenciaException {
        persistencia.insertarObjeto(objeto);
    }

    public void eliminar(Objeto objeto) throws PersistenciaException {
        persistencia.eliminarObjeto(objeto);
    }

    public Objeto buscar(int id) throws PersistenciaException {
        return persistencia.obtenerObjeto(id);
    }

    public void modificar(Objeto objeto) throws PersistenciaException {
        persistencia.modificarObjeto(objeto);
    }

}
