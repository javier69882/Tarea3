package Tarea1;

/**
 * Clase Comprador que representa al comprador de un producto
 * Realiza la compra de un producto en el expendedor , almacena el sonido del producto y toma el vuelto
 */

class Comprador {
    private String sonido;
    private int vuelto;
// reemplazo cualBebida por cual Producto
//cambio int por PrecioProducto

    /**
     * Constructor de la clase Comprador
     *
     * @param m            moneda que se va a usar para comprar el producto
     * @param cualProducto  producto que se va a comprar
     * @param exp          expendedor donde se va a comprar el producto
     * @throws PagoIncorrectoException si la moneda no es correcta
     * @throws PagoInsuficienteException si la moneda no es suficiente
     * @throws NoHayProductoException si no hay producto disponible
     */
    public Comprador(Moneda m, PrecioProducto cualProducto, Expendedor exp)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        sonido = null;
        vuelto = 0;

        if (m == null) return;

        limpiarVueltoAnterior(exp);
// se cambia todo lo referente a bebida por producto

        Producto p = exp.comprarProducto(m, cualProducto);// comprarPrducto da error, por los throws
        //asi que voy a disipar los throws en el constructor, para usarlos try y catch en el main
        //como sale en la pauta
        if (p == null) {
            vuelto = m.getValor();
        }
        else {
            // cambio los metodos de sonido
            sonido = p.accionProducto();
            Moneda mon;
            while ((mon = exp.getVuelto()) != null) {
                vuelto += mon.getValor();
            }
        }
    }
    /**
     * Limpia el vuelto anterior del expendedor
     *
     * @param exp  expendedor usado
     */
    private void limpiarVueltoAnterior(Expendedor exp) {
        while (exp.getVuelto() != null) {

        }
    }
    /**
     * Retorna el vuelto recibido
     *
     * @return Cantidad de vuelto recibido en pesos.
     */


    public int cuantoVuelto() {
        return vuelto;
    }
    /**
     * Retorna el sonido del producto comprado
     *
     * @return Sonido del producto comprado
     */
// se cambia el queBebiste por queAccionProducto
    public String queAccionProducto() {
        return sonido;
    }
}

