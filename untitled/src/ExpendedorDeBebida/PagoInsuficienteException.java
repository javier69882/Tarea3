package Tarea1;

public class PagoInsuficienteException extends Exception{

    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
    // Constructor con mensaje ya elegido
    public PagoInsuficienteException() {
        super("Pago insuficiente");
    }




}
