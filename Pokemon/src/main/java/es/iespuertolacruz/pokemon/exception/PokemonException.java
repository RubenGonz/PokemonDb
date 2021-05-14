package es.iespuertolacruz.pokemon.exception;



public class PokemonException  extends Exception{
     
    public PokemonException(String mensaje){
        super(mensaje);
    }

    public PokemonException(String mensaje ,Exception causa) {
        super(mensaje , causa);
    }

    
}
