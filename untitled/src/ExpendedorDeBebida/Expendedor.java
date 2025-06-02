package Tarea1;

/**
 * Clase representa un expendedor de productos
 * Adminisra los productos y el dinero
 */

public class Expendedor {
    /** Constante para seleccionar CocaCola */
    public static final int COCA = 1;
    /** Constante para seleccionar Sprite */
    public static final int SPRITE = 2;
    /** Constante para seleccionar Fanta */
    public static final int FANTA = 3;
    /** Constante para seleccionar Super8 */
    public static final int SUPER8 = 4;
    /** Constante para seleccionar Snickers */
    public static final int SNIKERS = 5;

    private Deposito<Bebida> depositoCocaCola;
    private Deposito<Bebida> depositoSprite;
    //creo los depositos que faltan
    private Deposito<Bebida> depositoFanta;
    private Deposito<Dulce> depositoSuper8;
    private Deposito<Dulce> depositoSnickers;
    private Deposito<Moneda> monVu;
    // private int precio; ya no se necesita

    // modifico el constructor, cambio numBebidas por numProducto,

    /**
     * Constructor de expendedor
     * Inicializa los depositos de bebidas y dulces
     * @param numProductos cantidad de productos a agregar
     */
    public Expendedor(int numProductos) {
        // this.precio = precioProductos; ya no se necesita
        depositoCocaCola = new Deposito <Bebida>();
        depositoSprite = new Deposito <Bebida>();
        //inicializo los depositos que faltan
        depositoFanta = new Deposito<Bebida>();
        depositoSuper8 = new Deposito<Dulce>();
        depositoSnickers = new Deposito<Dulce>();

        monVu = new Deposito<Moneda> ();

        // agrego los elementos a los depositos, osea los nuevos productos, y reemplazo numBebida por numProducto
        for (int i = 0; i < numProductos; i++) {
            depositoCocaCola.addElemento(new CocaCola(1000 + i));
            depositoSprite.addElemento(new Sprite(2000 + i));
            depositoFanta.addElemento(new Fanta(3000 + i));
            depositoSuper8.addElemento(new Super8(4000 + i));
            depositoSnickers.addElemento(new Snickers(5000 + i));
        }
    }

    //modifico el metodo para que reciba producto
    // cambio de int a PrecioProducto
    /**
     * Metodo para comprar un producto
     * @param m moneda que se va a usar para comprar el producto
     * @param seleccion del producto que se va a comprar
     * @return devuelve el producto comprado
     * @throws PagoIncorrectoException si la moneda no es correcta
     * @throws PagoInsuficienteException si la moneda no es suficiente
     * @throws NoHayProductoException si no hay producto disponible
     */
    public Producto comprarProducto(Moneda m, PrecioProducto seleccion)
            //agrego las excepciones
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException{
        if (m == null)
            //AGREGO EXCEPCION
            throw new PagoIncorrectoException();
// cambio precio por seleccion.getPrecio()
        if (m.getValor() < seleccion.getPrecio()){
            //DEVUELVE DINERO, AQUI IRA UN EXCEPCION
            int vuelto = m.getValor();
            while (vuelto >= 100) {
                monVu.addElemento(new Moneda100());
                vuelto -= 100;
            }
            //AGREGO EXCEPCION
            throw new PagoInsuficienteException();
        }
        Producto b = null;
//se cambia de if y else a switch y case
        switch(seleccion) {
            case COCA:
                b = depositoCocaCola.getElemento();
                break;
            case SPRITE:
                b = depositoSprite.getElemento();
                break;
            case FANTA:
                b = depositoFanta.getElemento();
                break;
            case SUPER8:
                b = depositoSuper8.getElemento();
                break;
            case SNIKERS:
                b = depositoSnickers.getElemento();
                break;
        }

        if (b == null) {
            // NO HAY BEBIDA, DEVUELVE DINERO

            int vuelto = m.getValor();
            while (vuelto >= 100) {
                monVu.addElemento(new Moneda100());
                vuelto -= 100;
            }
            throw new NoHayProductoException();
        }
        // da vuelto
        //cambio precio por seleccion.getPrecio
        int diferencia = m.getValor() - seleccion.getPrecio();
        while (diferencia >= 100) {
            monVu.addElemento(new Moneda100());
            diferencia -= 100;
        }

        return b;
    }
    /**
     * Devuelve el vuelto
     *
     * @return Una moneda del vuelto, o null si no hay mas monedas
     */

    public Moneda getVuelto() {


        return monVu.getElemento();
    }
    // public int getPrecio() {  ya no se usa el getter

     //   return precio;
   // }
}
