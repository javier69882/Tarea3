package Tarea1;
/**
 * Clase abstracta que representa un dulce
 * Se extiende de la clase {@link Producto}
 * Las subclases implementan {@code accionProducto}
 */

public abstract class Dulce extends Producto {

    // llamo constructor de producto
    /**
     * Constructor del dulce
     *
     * @param serie numero de serie del dulce
     */


    public Dulce(int serie) {
        super(serie);
    }
    // disitinto a bebida

    /**
     * Accion que realiza el dulce
     *
     * @return nombre del dulce
     */

    public abstract String accionProducto();


}
