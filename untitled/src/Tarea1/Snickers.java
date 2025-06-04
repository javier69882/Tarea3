package Tarea1;


//Clase Snickers que hereda de Dulce
/**
 * Clase que representa un Snickers
 * Se extiende de la clase {@link Dulce}
 */

public class Snickers extends Dulce{
    /**
     * Constructor de Snickers
     * Llama al constructor de la clase padre {@link Dulce}
     *
     * @param serie numero de serie del dulce
     */


    public Snickers(int serie) {
        super(serie);
    }
    /**
     * Accion que realiza el dulce
     *
     * @return nombre del dulce
     */

    public String accionProducto() {
        return "SNIKERS";
    }
}
