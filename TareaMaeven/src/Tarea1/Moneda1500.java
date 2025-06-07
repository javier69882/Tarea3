package Tarea1;

/**
 * Clase que representa una moneda de 1500 pesos
 * Extiende la clase {@link Moneda}
 */
public class Moneda1500 extends Moneda {
    private int numeroSerie;
    /**
     * Constructor de Moneda1500
     * Llama al constructor de la clase padre {@link Moneda}
     */
    public Moneda1500(){
        super();
    }
    public Moneda1500(int numeroSerie){
        super(numeroSerie);
    }
    /**
     * Metodo que devuelve el valor de la moneda
     *
     * @return el valor de la moneda (1500)
     */
    public int getValor(){
        return 1500;
    }
}
