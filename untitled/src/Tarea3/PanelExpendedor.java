package Tarea3;

import javax.swing.*;
import java.awt.*;
import Tarea1.PrecioProducto;

public class PanelExpendedor extends JPanel {

    private ImageIcon[] productos;
    private JButton[] botonesSeleccion;
    private PrecioProducto[] productosEnum = {
            PrecioProducto.COCA,
            PrecioProducto.FANTA,
            PrecioProducto.SPRITE,
            PrecioProducto.SUPER8,
            PrecioProducto.SNIKERS
    };
    private int valorMonedaSeleccionada = 0;
    private String productoSeleccionado = null;

    // Nuevas variables para estado visual
    private String mensajeEstado = "";
    private String productoEntregado = "";
    private int vuelto = 0;

    public PanelExpendedor() {
        this.setBackground(Color.lightGray);
        this.setLayout(null); // Diseño absoluto

        productos = new ImageIcon[productosEnum.length];
        for (int i = 0; i < productosEnum.length; i++) {
            productos[i] = new ImageIcon(getClass().getResource("/imagenes/producto" + (i + 1) + ".png"));
        }

        botonesSeleccion = new JButton[productosEnum.length];
        for (int i = 0; i < productosEnum.length; i++) {
            botonesSeleccion[i] = new JButton(String.valueOf(i + 1));
            botonesSeleccion[i].setMargin(new Insets(0, 0, 0, 0));
            botonesSeleccion[i].setFocusable(false);
            this.add(botonesSeleccion[i]);
        }
    }

    public JButton[] getBotonesSeleccion() {
        return botonesSeleccion;
    }

    public void setValorMonedaSeleccionada(int valor) {
        this.valorMonedaSeleccionada = valor;
        repaint();
    }

    public void setProductoSeleccionado(String nombreProducto) {
        this.productoSeleccionado = nombreProducto;
        repaint();
    }

    // Métodos nuevos para actualizar visualización
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = this.getWidth();
        int boxWidth = 180;
        int boxHeight = 80;
        int boxX = panelWidth - boxWidth - 20;
        int boxY = 15;

        // Dibuja el rectángulo blanco
        g.setColor(Color.white);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);
        g.setColor(Color.black);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);

        // Ajusta el tamaño de fuente y el espaciado para que todo quepa
        int y = boxY + 18;
        g.setFont(new Font("Arial", Font.PLAIN, 11));

        if (valorMonedaSeleccionada > 0) {
            String textoMoneda = "Saldo: $" + valorMonedaSeleccionada;
            int textWidth = g.getFontMetrics().stringWidth(textoMoneda);
            int textX = boxX + (boxWidth - textWidth) / 2;
            g.setColor(Color.black);
            g.drawString(textoMoneda, textX, y);
            y += 14;
        }

        if (productoSeleccionado != null) {
            String textoProducto = "Producto: " + productoSeleccionado;
            int textWidth = g.getFontMetrics().stringWidth(textoProducto);
            int textX = boxX + (boxWidth - textWidth) / 2;
            g.setColor(Color.black);
            g.drawString(textoProducto, textX, y);
            y += 14;
        }

        if (!mensajeEstado.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 10));
            g.setColor(Color.RED);
            g.drawString(mensajeEstado, boxX + 8, y);
            y += 13;
            g.setFont(new Font("Arial", Font.PLAIN, 11));
        }

        if (vuelto > 0) {
            g.setFont(new Font("Arial", Font.BOLD, 10));
            g.setColor(new Color(0, 128, 0));
            String textoVuelto = "Vuelto: $" + vuelto;
            g.drawString(textoVuelto, boxX + 8, y);
            y += 13;
            g.setFont(new Font("Arial", Font.PLAIN, 11));
        }

        if (!productoEntregado.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 11));
            g.setColor(Color.BLUE.darker());
            String textoEntregado = "Entregado: " + productoEntregado;
            g.drawString(textoEntregado, boxX + 8, y);
        }

        // Dibujar productos (igual que antes)
        int panelHeight = this.getHeight();
        int rectWidth = 120, rectHeight = 500;
        int offsetX = 20;
        int rectX = (panelWidth - rectWidth) / 2 - offsetX;
        int rectY = (panelHeight - rectHeight) / 2;

        g.setColor(Color.darkGray);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        int imageWidth = 100;
        int imageHeight = 80;
        int espacio = 10;

        for (int i = 0; i < productos.length; i++) {
            Image img = productos[i].getImage();
            int xImg = rectX + (rectWidth - imageWidth) / 2;
            int yImg = rectY + espacio + i * (imageHeight + espacio);

            g.drawImage(img, xImg, yImg, imageWidth, imageHeight, this);

            String textoPrecio = productosEnum[i].name() + "  $" + productosEnum[i].getPrecio();
            g.setColor(Color.white);
            g.fillRect(xImg, yImg + imageHeight - 10, imageWidth, 20);
            g.setColor(Color.black);
            g.drawString(textoPrecio, xImg + 5, yImg + imageHeight + 5);

            int xBtn = xImg + imageWidth + 10;
            int yBtn = yImg + (imageHeight - 30) / 2;
            botonesSeleccion[i].setBounds(xBtn, yBtn, 40, 30);
        }
    }
}