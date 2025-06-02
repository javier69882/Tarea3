package Tarea1;

/**
 * Clase abstracta que representa las bebida
 * Se extiende de la clase {@link Producto}
 * Las subclases implementan {@code accionProducto}
 */

abstract class Bebida extends Producto {

    //private int numero_serie; , ya no es necesario
    /**
     * Constructor de la clase Bebida
     * @param serie numero de serie en las bebidas
     */

    public Bebida(int serie){
        //llamo al constructor de producto
        super(serie) ;       //serie
    }
    //public int getSerie(){
       // return numero_serie;
   // }, ya no es necesario
    /**
     * Accion que realiza la bebida
     * @return nombre de la bebida
     */
    public abstract String accionProducto();
}
//No hay necesidad de modificar subclase cocacola, sprite y fanta
