package Tarea1;
/**
 * Clase que representa coca cola
 * Se extiende de la clase {@link Bebida}
 */
class CocaCola extends Bebida{

    /**
     * Constructor de CocaCola
     *
     * @param serie numero de serie de la bebida
     */


    public CocaCola(int serie) {
        super(serie);
    }
    /**
     * Accion que realiza la bebida
     *
     * @return nombre de la bebida
     */

    public String accionProducto() {
        return "cocacola";
    }
}
