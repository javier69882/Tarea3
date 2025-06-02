package Tarea1;
/**
 * Clase que representa fanta
 * Se extiende de la clase {@link Bebida}
 */

class Fanta extends Bebida{
    /**
     * Constructor de Fanta
     *
     * @param serie numero de serie de la bebida
     */


    public Fanta(int serie) {
        super(serie);
    }
    /**
     * Accion que realiza la bebida
     *
     * @return nombre de la bebida
     */
    public String accionProducto() {
        return "fanta";
    }
}
