package Tarea1;
/**
 * Clase que representa los precios de los productos
 * Se usa una enumeracion para definir los precios
 * de cada producto
 */
// Creacion de la enumeracion


public enum PrecioProducto {
    //cambio COCACOLA por COCA, porque daba error
    COCA(1200),
    FANTA(1200),
    SPRITE(1200),
    SUPER8(400),
    SNIKERS(600);
// se usa final para que no se modifiquen los precios

    private final int precio;
    /**
     *  Constructor de PrecioProducto
     */
    PrecioProducto(int precio){
        this.precio=precio;
    }
    // metodo getter
    /**
     * Metodo que devuelve el precio del producto
     *
     * @return el precio del producto
     */
    public int getPrecio(){
        return precio;
    }

}
