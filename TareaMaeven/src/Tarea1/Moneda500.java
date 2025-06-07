package Tarea1;

// se agrega extends Moneda a la clase
/**
 * Clase que representa una moneda de 500 pesos
 * Extiende la clase {@link Moneda}
 */
public class Moneda500 extends Moneda {
    private int numeroSerie;
    /**
     * Constructor de Moneda500
     * Llama al constructor de la clase padre {@link Moneda}
     */
    public Moneda500(){
        super();

    }
    public Moneda500(int numeroSerie){
        super(numeroSerie);
    }
    /**
     * Metodo que devuelve el valor de la moneda
     *
     * @return el valor de la moneda (500)
     */
    public int getValor(){
        return 500;
    }

}
