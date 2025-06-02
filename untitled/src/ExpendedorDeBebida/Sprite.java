package Tarea1;
/**
 * Clase que representa sprite
 * Se extiende de la clase {@link Bebida}
 */

class Sprite  extends Bebida{
    /**
     * Constructor de Sprite
     *
     * @param serie numero de serie de la bebida
     */
    public Sprite(int serie) {
        super(serie);
    }
    /**
     * Accion que realiza la bebida
     *
     * @return nombre de la bebida
     */

    public String accionProducto() {
        return  "sprite";
    }
}
