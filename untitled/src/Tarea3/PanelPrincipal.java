package Tarea3;

import javax.swing.*;
import java.awt.*;
import Tarea1.*;

public class PanelPrincipal extends JPanel {

    private PanelExpendedor exp;
    private PanelComprador com;
    private Expendedor expendedorLogico;
    private Moneda monedaSeleccionada = null;
    private PrecioProducto productoSeleccionado = null;
    private Image fondo;
    private int contadorRestock = 0;
    private String mensajeEstado = "";
    private String productoEntregado = "";
    private int vuelto = 0;

    public PanelPrincipal() {
        this.setBackground(Color.white);
        this.setLayout(null); // Diseño absoluto
        // Cargar la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/Fondo/fondo.png")).getImage();
        // Inicializar el expendedor lógico  con 1 producto de cada 1
        expendedorLogico = new Expendedor(1);

        // Crear el PanelExpendedor
        exp = new PanelExpendedor();
        exp.setBounds(400, 0, 450, 792); // Posición y tamaño del PanelExpendedor
        com = new PanelComprador();
        com.setBounds(20, 100, 200, 600); // Posición y tamaño del PanelComprador

        this.add(exp); // Agregar el PanelExpendedor
        this.add(com); // Agregar el PanelComprador

        // Monedas disponibles
        Moneda[] monedas = { new Moneda100(), new Moneda500(), new Moneda1000(), new Moneda1500() };
        // Productos disponibles
        PrecioProducto[] productos = { PrecioProducto.COCA, PrecioProducto.FANTA, PrecioProducto.SPRITE, PrecioProducto.SUPER8, PrecioProducto.SNIKERS };

        // Listener para monedas (billetera)
        JButton[] botonesMonedas = com.getBotonesMonedas();
        for (int i = 0; i < botonesMonedas.length; i++) {
            int j = i;
            botonesMonedas[i].addActionListener(e -> {
                exp.setMensajeEstado(""); // Limpiar mensaje anterior
                exp.setProductoEntregado(""); // Limpiar mensaje de producto entregado
                exp.setVuelto(0); // Limpiar mensaje del vuelto
                monedaSeleccionada = monedas[j];
                exp.setValorMonedaSeleccionada(monedaSeleccionada.getValor());
                intentarCompra();
            });
        }
        // Listener para productos (expendedor)
        JButton[] botonesProductos = exp.getBotonesSeleccion();
        for (int i = 0; i < botonesProductos.length; i++) {
            int j = i;
            botonesProductos[i].addActionListener(e -> {
                exp.setMensajeEstado(""); // Limpiar mensaje anterior
                exp.setProductoEntregado(""); // Limpiar mensaje de producto entregado
                exp.setVuelto(0); // Limpiar mensaje del vuelto
                productoSeleccionado = productos[j];
                exp.setProductoSeleccionado(productos[j].name());
                intentarCompra();
            });
        }

        //restock
        exp.setExpendedorLogico(expendedorLogico);

        exp.setOnRestockCallback(() -> {
            contadorRestock++;
            expendedorLogico.restockUnoDeCadaProducto(contadorRestock);
            exp.actualizarEstadoProductos(expendedorLogico);
        });
    }

    private void intentarCompra() {
        if (monedaSeleccionada != null && productoSeleccionado != null) {
            try {
                Comprador comprador = new Comprador(monedaSeleccionada, productoSeleccionado, expendedorLogico);
                Producto productoComprado = comprador.getProducto();
                exp.setMensajeEstado("Compra exitosa!");
                exp.setProductoEntregado(productoComprado.accionProducto());
                exp.setVuelto(comprador.cuantoVuelto());
                setProductoEnMochila(productoComprado.accionProducto(), productoComprado.getSerie());
            } catch (Exception e) {
                exp.setMensajeEstado("Error: " + e.getMessage());
                exp.setProductoEntregado("");
                exp.setVuelto(0);
                setProductoEnMochila("", 0); // Limpia la mochila si hay error
            }
            exp.actualizarEstadoProductos(expendedorLogico);

            monedaSeleccionada = null;
            productoSeleccionado = null;
            exp.setValorMonedaSeleccionada(0);
            exp.setProductoSeleccionado(null);
        }
    }


    // Método para actualizar la mochila en el PanelComprador
    public void setProductoEnMochila(String producto, int serie) {
        com.setProductoEnMochila(producto, serie);
    }


    public void setMensajeEstado(String mensaje) {
        this.mensajeEstado = mensaje;
        repaint();
    }

    public void setProductoEntregado(String producto) {
        this.productoEntregado = producto;
        repaint();
    }

    public void setVuelto(int vuelto) {
        this.vuelto = vuelto;
        repaint();
    }

    private String mapearNombreProducto(String nombreOriginal) {
        return switch (nombreOriginal.toLowerCase()) {
            case "coca" -> "COCA";
            case "fanta" -> "FANTA";
            case "sprite" -> "SPRITE";
            case "super8" -> "SUPER8";
            case "snikers" -> "SNIKERS";
            default -> "";
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}