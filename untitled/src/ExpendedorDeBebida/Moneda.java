package Tarea1;
// Se implementa la interfaz de compareTo
/**
 * Clase abstracta moneda
 * Implementa la interfaz {@link Comparable} para usar el metodo {@code compareTo}
 */
abstract class Moneda implements Comparable<Moneda>{

    /**
     * Constructor vacio de Moneda
     */

    public Moneda(){

    }
    /**
     * Devuelve el numero de serie de la moneda
     *
     * @return  instancia de {@code Moneda}
     */
    public Moneda getSerie(){
        return this;
    }

    /**
     * Devuelve el valor de la moneda
     *
     * @return  valor de la moneda
     */
    public abstract int getValor();
    // Se agrega el metodo compareTo
    /**
     * Compara dos monedas
     *
     * @param otra moneda a comparar
     * @return 1 si la moneda es mayor, -1 si es menor y 0 si son iguales
     */
    public int compareTo(Moneda otra){
        return Integer.compare(this.getValor(), otra.getValor());
    }

}
