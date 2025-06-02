package Tarea1;
/**
 * Clase que representa una moneda de 1000 pesos
 * Extiende la clase {@link Moneda}
 */

class Moneda1000 extends Moneda{
    /**
     * Constructor de Moneda1000
     * Llama al constructor de la clase padre {@link Moneda}
     */
    public Moneda1000(){
        super();
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
