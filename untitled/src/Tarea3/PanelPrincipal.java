package Tarea3;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import Tarea1.*;

public class PanelPrincipal extends JPanel {

    private PanelExpendedor exp;
    private PanelComprador com;
    private Expendedor expendedorLogico;
    private Moneda monedaSeleccionada = null;
    private PrecioProducto productoSeleccionado = null;
    private Image fondo;
    private int contadorRestock = 0;

    private Deposito<Moneda> registroMonedas; // Aquí se guarda cada moneda usada en compras exitosas

    public PanelPrincipal() {
        this.setBackground(Color.white);
        this.setLayout(null);
        fondo = new ImageIcon(getClass().getResource("/Fondo/fondo.png")).getImage();
        expendedorLogico = new Expendedor(1);

        registroMonedas = new Deposito<>(); // Inicialización del deposito de monedas usadas

        exp = new PanelExpendedor();
        exp.setBounds(400, 0, 450, 792);
        com = new PanelComprador();
        com.setBounds(20, 100, 200, 600);

        this.add(exp);
        this.add(com);

        Moneda[] monedas = { new Moneda100(), new Moneda500(), new Moneda1000(), new Moneda1500() };
        PrecioProducto[] productos = {
                PrecioProducto.COCA,
                PrecioProducto.FANTA,
                PrecioProducto.SPRITE,
                PrecioProducto.SUPER8,
                PrecioProducto.SNIKERS
        };

        JButton[] botonesMonedas = com.getBotonesMonedas();
        for (int i = 0; i < botonesMonedas.length; i++) {
            int j = i;
            botonesMonedas[i].addActionListener(e -> {
                exp.setMensajeEstado("");
                exp.setProductoEntregado("");
                exp.setVuelto(0);
                monedaSeleccionada = monedas[j];
                exp.setValorMonedaSeleccionada(monedaSeleccionada.getValor());
                intentarCompra();
            });
        }

        JButton[] botonesProductos = exp.getBotonesSeleccion();
        for (int i = 0; i < botonesProductos.length; i++) {
            int j = i;
            botonesProductos[i].addActionListener(e -> {
                exp.setMensajeEstado("");
                exp.setProductoEntregado("");
                exp.setVuelto(0);
                productoSeleccionado = productos[j];
                exp.setProductoSeleccionado(productos[j].name());
                intentarCompra();
            });
        }

        exp.setExpendedorLogico(expendedorLogico);

        exp.setOnRestockCallback(() -> {
            contadorRestock++;
            expendedorLogico.restockUnoDeCadaProducto(contadorRestock);
            exp.actualizarEstadoProductos(expendedorLogico);
        });

        // Set callback para abrir ventana Caja Fuerte
        exp.setOnCajaFuerteCallback(() -> {
            List<Moneda> monedasCaja = getMonedasEnCajaFuerte();
            exp.abrirVentanaCajaFuerteConMonedas(monedasCaja);
        });
    }

    private void intentarCompra() {
        if (monedaSeleccionada != null && productoSeleccionado != null) {
            try {
                Comprador comprador = new Comprador(monedaSeleccionada, productoSeleccionado, expendedorLogico);
                Producto productoComprado = comprador.getProducto();

                int vuelto = comprador.cuantoVuelto();
                List<Integer> monedas = calcularMonedas(vuelto);

                // Guardar la moneda usada en el registro de monedas
                registroMonedas.addElemento(monedaSeleccionada);

                exp.setMensajeEstado("Compra exitosa!");
                exp.setProductoEntregado(productoComprado.accionProducto());
                exp.setVuelto(vuelto);
                exp.setMonedasVuelto(monedas);

                setProductoEnMochila(productoComprado.accionProducto(), productoComprado.getSerie());
            } catch (Exception e) {
                exp.setMensajeEstado("Error: " + e.getMessage());
                exp.setProductoEntregado("");
                exp.setVuelto(0);
                exp.setMonedasVuelto(new ArrayList<>());
                setProductoEnMochila("", 0);
            }

            exp.actualizarEstadoProductos(expendedorLogico);

            monedaSeleccionada = null;
            productoSeleccionado = null;
            exp.setValorMonedaSeleccionada(0);
            exp.setProductoSeleccionado(null);
        }
    }

    private List<Integer> calcularMonedas(int vuelto) {
        List<Integer> monedas = new ArrayList<>();
        int[] valores = {1000, 500, 100};
        for (int valor : valores) {
            while (vuelto >= valor) {
                monedas.add(valor);
                vuelto -= valor;
            }
        }
        return monedas;
    }

    public void setProductoEnMochila(String producto, int serie) {
        com.setProductoEnMochila(producto, serie);
    }

    public List<Moneda> getMonedasEnCajaFuerte() {
        return registroMonedas.getElementos();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}