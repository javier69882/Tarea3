package Tarea1;

public class NoHayProductoException extends Exception {
    //constructor
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
    //constructor con mensaje ya elegido
    public NoHayProductoException() {
        super("No hay producto disponible");
    }


}
