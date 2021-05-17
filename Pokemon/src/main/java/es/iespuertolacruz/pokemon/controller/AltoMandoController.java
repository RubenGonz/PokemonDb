package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.AltoMandoModelo;

public class AltoMandoController {
    private static final String EL_AltoMando_INDICADO_NO_EXISTE = null;

    public void validar(AltoMando altoMando) throws PokemonException {
        String mensaje = "";

        if (altoMando == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (altoMando.getIdEntrenador() < 0) {
            mensaje = "El el id_Entrenador es 0 o menor, ";
        }

        if (altoMando.getTipoPrincipal() == null || altoMando.getTipoPrincipal().isEmpty()) {
            mensaje += "El TipoPrincipal es nulo o vacio, ";
        }


        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pokemon a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException
     */
    public void insertar(AltoMando altoMando) throws PokemonException, PersistenciaException {
        validar(altoMando);
        if (existe(altoMando)) {
            throw new PokemonException("El altoMando indicado ya existe");
        }
        AltoMandoModelo.insertar(altoMando);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pokemon a eliminar
     * @throws PokemonException
     * @throws PersistenciaException
     */
    public void eliminar(AltoMando altoMando) throws PokemonException, PersistenciaException {
        validar(altoMando);
        if (!existe(altoMando)) {
            throw new PokemonException(EL_AltoMando_INDICADO_NO_EXISTE);
        }
        AltoMandoModelo.eliminar(altoMando);
    }

    /**
     * Metodos encargado de realizar la eliminacion de un pokemon usando el numero
     * de la pokedex
     * 
     * @param identificador del pokemon a eliminar
     * @throws PokemonException      controlada con el error
     * @throws PersistenciaException
     */
    public void eliminar(int IdEntrenador) throws PokemonException, PersistenciaException {
        AltoMando altoMando;
        altoMando = buscar(IdEntrenador);
        eliminar(altoMando);
    }

    /**
     * Metodo encargado de buscar por el numero de la pokedex
     * 
     * @param numeroPokedex para localizar el pokemon
     * @return pokemon a traves del numero de la pokedex
     * @throws PersistenciaException
     */
    public AltoMando buscar(int IdEntrenador) throws PersistenciaException {
        AltoMando altoMando = null;
        altoMando = AltoMandoModelo.buscar(IdEntrenador);
        return altoMando;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pokemon
     * 
     * @param pokemon a modficar
     * @throws PokemonException      controlada en caso de error
     * @throws PersistenciaException
     */
    public void modificar(AltoMando altoMando) throws PokemonException, PersistenciaException {

        validar(altoMando);
        if (!existe(altoMando)) {
            throw new PokemonException(EL_AltoMando_INDICADO_NO_EXISTE);
        }
        AltoMandoModelo.modificar(altoMando);
    }

    /**
     * Funcion encargada de verificar si un altoMando existe
     * 
     * @param altoMando a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException error controlado
     */
    private boolean existe(AltoMando altoMando) throws PersistenciaException {
        boolean encontrado = false;
        AltoMando AltoMandoEncontrado;

        AltoMandoEncontrado = buscar(altoMando.getIdEntrenador());
        if (AltoMandoEncontrado != null) {
            encontrado = true;
        }
        return encontrado;
    }
}
