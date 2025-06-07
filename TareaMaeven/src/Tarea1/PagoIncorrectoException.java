package Tarea1;

public class PagoIncorrectoException extends Exception{
    // Constructor
    public PagoIncorrectoException(String mensaje){
        super(mensaje);
    }
    // Constructor con mensaje ya elegido
    public PagoIncorrectoException(){
        super("Pago incorrecto");
    }
}
