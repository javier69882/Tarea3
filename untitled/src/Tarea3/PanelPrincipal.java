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

    public PanelPrincipal() {
        this.setBackground(Color.white);
        this.setLayout(null); // Diseño absoluto
        // Cargar la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/Fondo/fondo.png")).getImage();
        // Inicializar el expendedor lógico  con 1 producto de cada 1
        expendedorLogico = new Expendedor(1);

        // Crear el PanelExpendedor
        exp = new PanelExpendedor();
        exp.setBounds(400, 100, 300, 600); // Posición y tamaño del PanelExpendedor
        com = new PanelComprador();
        com.setBounds(20, 100, 200, 600); // Posición y tamaño del PanelComprador
        // Agregar componentes al panel

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
                monedaSeleccionada = monedas[j];
                JOptionPane.showMessageDialog(this, "Moneda de " + monedas[j].getValor() + " seleccionada.");
                intentarCompra();
            });
        }
        // Listener para productos (expendedor)
        JButton[] botonesProductos = exp.getBotonesSeleccion();
        for (int i = 0; i < botonesProductos.length; i++) {
            int j = i;
            botonesProductos[i].addActionListener(e -> {
                productoSeleccionado = productos[j];
                JOptionPane.showMessageDialog(this, "Producto " + productos[j].name() + " seleccionado.");
                intentarCompra();
            });
        }




    }

    private void intentarCompra() {
        if (monedaSeleccionada != null && productoSeleccionado != null) {
            try {
                Comprador comprador = new Comprador(monedaSeleccionada, productoSeleccionado, expendedorLogico);
                JOptionPane.showMessageDialog(this, "Compraste: " + comprador.queAccionProducto() + "\nVuelto: " + comprador.cuantoVuelto());
                } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
            // Resetea para la próxima compra
            monedaSeleccionada = null;
            productoSeleccionado = null;
        }
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