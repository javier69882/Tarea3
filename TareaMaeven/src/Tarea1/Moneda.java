package Tarea1;
// Se implementa la interfaz de compareTo
/**
 * Clase abstracta moneda
 * Implementa la interfaz {@link Comparable} para usar el metodo {@code compareTo}
 */
public abstract class Moneda implements Comparable<Moneda>{

    private int numeroSerie;


    /**
     * Constructor vacio de Moneda
     */

    public Moneda(){

    }
    public Moneda(int numeroSerie){
        this.numeroSerie = numeroSerie;
    }
    /**
     * Devuelve el numero de serie de la moneda
     *
     * @return  instancia de {@code Moneda}
     */
    public int getSerie(){
        return numeroSerie;
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
