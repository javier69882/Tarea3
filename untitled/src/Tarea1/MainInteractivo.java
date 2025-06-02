package Tarea1;
import java.util.Scanner;
public class MainInteractivo {
    public static void main(String[] args) {
        //obejeto de la clase Scanner, para la entrada
        // lo puse como python
        Scanner input = new Scanner(System.in);
        System.out.println("Expendedor de productos, seleccione la cantidad de productos que quiere que haya:");
        //pido la cantidad de productos
        int cantidad = input.nextInt();
        //creo el expendedor con la cantidad de productos

        Expendedor exp = new Expendedor(cantidad);
        int continuar=1;
        int productonum;
        int monedanum;
        //cre las monedas
        Moneda m1 = new Moneda100();
        Moneda m2 = new Moneda500();
        Moneda m3 = new Moneda1000();
        Moneda m4 = new Moneda1500();

        Moneda monedaElegida = null;
        PrecioProducto productoElegido = null;
        // while para que el usuario pueda elegir el producto, hasta que el quiera
        while (continuar!=0){
            System.out.println("Seleccione el producto que desea, ingresando el numero:");
            System.out.println("1. CocaCola");
            System.out.println("2. Fanta");
            System.out.println("3. Sprite");
            System.out.println("4. Super8");
            System.out.println("5. Snickers");
            productonum = input.nextInt();
            //switch para elegir el producto
            switch (productonum){
                case 1:
                    productoElegido = PrecioProducto.COCA;
                    break;
                case 2:
                    productoElegido = PrecioProducto.FANTA;
                    break;
                case 3:
                    productoElegido = PrecioProducto.SPRITE;
                    break;
                case 4:
                    productoElegido = PrecioProducto.SUPER8;
                    break;
                case 5:
                    productoElegido = PrecioProducto.SNIKERS;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

            //usuario ingresa la moneda
            System.out.println("Ingrese la moneda que desea usar:");
            System.out.println("1. 100");
            System.out.println("2. 500");
            System.out.println("3. 1000");
            System.out.println("4. 1500");
            monedanum = input.nextInt();
            //switch para elegir la moneda
            switch (monedanum){
                case 1:
                    monedaElegida = m1;
                    break;
                case 2:
                    monedaElegida = m2;
                    break;
                case 3:
                    monedaElegida= m3;
                    break;
                case 4:
                    monedaElegida = m4;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            // hago la compra
            try {
                Comprador c = new Comprador(monedaElegida, productoElegido, exp);
                System.out.println(c.queAccionProducto());
                System.out.println("Vuelto: " + c.cuantoVuelto());
            } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
                System.out.println("Error: " + e.getMessage());
            }
            //pregunto si quiere seguir
            System.out.println("Desea seguir comprando? 1. Si 0. No");
            continuar = input.nextInt();









        }













    }
}
