package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.ObjetoComun;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ObjetoComunModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public ObjetoComunModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(ObjetoComun objetoComun) throws PersistenciaException {
        persistencia.insertarObjetoComun(objetoComun);
    }

    public void eliminar(ObjetoComun objetoComun) throws PersistenciaException {
        persistencia.eliminarObjetoComun(objetoComun);
    }

    public ObjetoComun buscar(int idObjeto) throws PersistenciaException {
        return persistencia.obtenerObjetoComun(idObjeto);
    }

    public void modificar(ObjetoComun objetoComun) throws PersistenciaException {
        persistencia.modificarObjetoComun(objetoComun);
    }

}
