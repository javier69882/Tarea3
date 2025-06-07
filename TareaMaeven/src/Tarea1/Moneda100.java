package Tarea1;

/**
 * Clase que representa una moneda de 100 pesos.
 * Extiende la clase {@link Moneda}.
 */
public class Moneda100 extends Moneda {
    /**
     * Constructor de Moneda100.
     * Llama al constructor de la clase padre {@link Moneda}.
     */
    private int numeroSerie;
    public Moneda100(){

        super();
    }
    public Moneda100(int numeroSerie){
        super(numeroSerie);
    }
    /**
     * Metodo que devuelve el valor de la moneda.
     *
     * @return el valor de la moneda (100)
     */
    public int getValor() {

        return 100;
    }
}
