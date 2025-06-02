package Tarea1;

//Clase Super8 que hereda de Dulce
/**
 * Clase que representa un Super8
 * Se extiende de la clase {@link Dulce}
 */


public class Super8 extends Dulce{
    /**
     * Constructor de Super8
     * Llama al constructor de la clase padre {@link Dulce}
     *
     * @param serie numero de serie del dulce
     */


    public Super8(int serie) {
        super(serie);
    }
    /**
     * Accion que realiza el dulce
     *
     * @return nombre del dulce
     */

    public String accionProducto() {
        return "super8";
    }
}
