package Tarea1;
import java.util.ArrayList;
import java.util.List;
// Modificacion con un deposito generico, se quito el anterior deposito de moneda y bebida
/**
 * Clase Deposito que representa un deposito generico
 * Se usa para almacenar productos o monedas
 *
 * @param <T> El tipo de elemento que se va a almacenar en el deposito
 */
public class Deposito<T>{
    /**
     * Lista que almacena los elementos del deposito
     */
    private ArrayList<T> almacen;
    /**
     * Constructor de deposito. Inicializa la el array para almacenar.
     */

    public Deposito() {
        almacen = new ArrayList<>();
    }
    /**
     * Agrega un elemento al deposito
     *
     * @param elemento elemento que ingresara al deposito
     */

    public void addElemento(T elemento) {
        almacen.add(elemento);
    }

    /**
     * Retorna el primer elemento del deposito y lo elimina, utiliza fifo
     *
     * @return elemento que se retira del deposito
     */

    public T getElemento() {
        if (almacen.size() > 0) {
            return almacen.remove(0);
        }
        return null;
    }
    public int size() {
        return almacen.size();
    }

    public void vaciar() {
        almacen.clear();
    }
    public List<T> getElementos() {
        return new ArrayList<>(almacen);
    }
}