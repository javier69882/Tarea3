package Tarea1;

//Creacion de la clase Producto
// Clase padre para dulce y bebida
//Me baso en la anterior clase bebida, para crearlo
/**
 * Clase abstracta que representa un producto
 * Es la clase base para productos como {@link Bebida} y {@link Dulce}
 */



public abstract class Producto {
    /**
     * numero de serie
     */


    private int numero_serie;

    //Mismo constructor que bebida
    /**
     * Constructor de la clase Producto
     *
     * @param serie numero de serie del producto
     */


    public Producto(int serie){
        this.numero_serie = serie;
    }
    //Metodo para obtener el numero de serie
    /**
     * Metodo para obtener el numero de serie
     *
     * @return numero de serie del producto
     */
    public int getSerie(){
        return numero_serie;
    }
    //Metodo para beber, no lo implemento, porque ya no sera solo beber
    //ahora modifico bebida y creo dulce
    /**
     * Accion que realiza el producto
     *
     * @return nombre del producto
     */
    public abstract String accionProducto();

}
