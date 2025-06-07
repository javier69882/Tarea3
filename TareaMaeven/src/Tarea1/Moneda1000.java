package Tarea1;
/**
 * Clase que representa una moneda de 1000 pesos
 * Extiende la clase {@link Moneda}
 */

public class Moneda1000 extends Moneda{
    private int numeroSerie;
    /**
     * Constructor de Moneda1000
     * Llama al constructor de la clase padre {@link Moneda}
     */
    public Moneda1000(){
        super();
    }
    public Moneda1000(int numeroSerie){
        super(numeroSerie);
    }
    /**
     * Metodo que devuelve el valor de la moneda
     *
     * @return el valor de la moneda (1000)
     */
    public int getValor() {
        return 1000;
    }
}
