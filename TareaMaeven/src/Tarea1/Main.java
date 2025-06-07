package Tarea1;

public class Main {
    public static void main(String[] args) {
        Expendedor exp = new Expendedor(1);
        Moneda m1500 = new Moneda1500();
        //se crea una instancia para cada moneda
        Moneda m100=new Moneda100();
        Moneda m500=new Moneda500();
        Moneda m1000=new Moneda1000();
        // se prueba que se pueda comprar una coca cola
        try {
            System.out.println("\nPrueba 1: Comprar CocaCola");
            Expendedor a=new Expendedor(5);
            //pongo PrecioProducto en vez de Expendedor
            Comprador c = new Comprador(m1500, PrecioProducto.COCA, a);
            System.out.println("producto comprado: "+c.queAccionProducto());
            System.out.println("Vuelto: " + c.cuantoVuelto());
        }
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
        System.out.print("------------------------------------------------------");
        // se prueba a comprar una fanta con 100, lo que no alcanza para comprarla
        try{
            System.out.println("\nPrueba 2: Comprar Fanta con moneda de 100");
            Expendedor b=new Expendedor(5);
            Comprador c=new Comprador(m100, PrecioProducto.FANTA, b);
            System.out.println("Producto comprado: "+c.queAccionProducto());
            System.out.println("Vuelto: "+c.cuantoVuelto());
        }
        catch(PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }
        System.out.print("------------------------------------------------------");
        // Se prueba comprar un producto agotado
        try{
            System.out.println("\nPrueba 3: Intentando comprar mas Sprite de las que hay");
            Comprador c=new Comprador(m1500, PrecioProducto.SPRITE, exp);
            System.out.println("Producto comprado: " + c.queAccionProducto());
            System.out.println("Vuelto: " + c.cuantoVuelto());
        }
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }
        try{
            Comprador c=new Comprador(m1500, PrecioProducto.SPRITE, exp);
            System.out.println("Producto comprado: " + c.queAccionProducto());
            System.out.println("Vuelto: " + c.cuantoVuelto());
        }
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }
        System.out.print("------------------------------------------------------");
        // Se prueba comprar un super 8
        try {
            System.out.println("\nPrueba 4: Comprar Super8");
            Expendedor e=new Expendedor(5);
            Comprador c= new Comprador(m1000, PrecioProducto.SUPER8, e);
            System.out.println("Producto comprado: "+c.queAccionProducto());
            System.out.println("Vuelto: "+c.cuantoVuelto());
        }
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
        System.out.print("------------------------------------------------------");
        // Se prueba comprar un snickers
        try{
            System.out.println("\nPrueba 5: Comprar Snickers");
            Expendedor f=new Expendedor(5);
            Comprador c=new Comprador(m1500, PrecioProducto.SNIKERS, f);
            System.out.println("Producto comprado: "+c.queAccionProducto());
            System.out.println("Vuelto: "+c.cuantoVuelto());
        }
        catch(PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
        System.out.print("------------------------------------------------------");
        // Se prueba comprar con una moneda nula
        try {
            System.out.println("\nPrueba 6: Intentando comprar con moneda nula");
            Expendedor g=new Expendedor(5);
            Comprador c=new Comprador(null, PrecioProducto.COCA, g);
            System.out.println("Producto comprado: "+c.queAccionProducto());
            System.out.println("Vuelto: "+c.cuantoVuelto());
        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }

    }
}