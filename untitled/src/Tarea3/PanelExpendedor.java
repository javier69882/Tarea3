package Tarea3;

import javax.swing.*;
import java.awt.*;
import Tarea1.PrecioProducto;

public class PanelExpendedor extends JPanel{

    private ImageIcon[] productos;
    private JButton[] botonesSeleccion;
    // Agrega el array de productos
    private PrecioProducto[] productosEnum = {
            PrecioProducto.COCA,
            PrecioProducto.FANTA,
            PrecioProducto.SPRITE,
            PrecioProducto.SUPER8,
            PrecioProducto.SNIKERS
    };
    private int valorMonedaSeleccionada = 0;
    private String productoSeleccionado = null;

    public PanelExpendedor() {
        this.setBackground(Color.lightGray);
        this.setLayout(null); // Diseño absoluto

        // Cargar imágenes de productos
        productos = new ImageIcon[productosEnum.length];
        for (int i = 0; i < productosEnum.length; i++) {
            productos[i] = new ImageIcon(getClass().getResource("/imagenes/producto" + (i + 1) + ".png"));
        }

        // Inicializar botones de selección
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

    public void setValorMonedaSeleccionada(int valor){
        this.valorMonedaSeleccionada = valor;
        repaint(); // Redibujar el panel para que se actualice el valor mostrado
    }

    public void setProductoSeleccionado(String nombreProducto){
        this.productoSeleccionado = nombreProducto;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = this.getWidth();
        int boxWidth = 444;
        int boxHeight = 137;
        int boxX = panelWidth - boxWidth - 4;
        int boxY = 4;

        g.setColor(Color.white);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);
        g.setColor(Color.black);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);

        // Mostrar valor de moneda seleccionada
        if (valorMonedaSeleccionada > 0) {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String textoMoneda = "$" + valorMonedaSeleccionada;
            int textWidth = g.getFontMetrics().stringWidth(textoMoneda);
            int textX = boxX + (boxWidth - textWidth) / 2;
            int textY = boxY + 30;
            g.drawString(textoMoneda, textX, textY);
        }

        // Mostrar nombre del producto seleccionado
        if (productoSeleccionado != null) {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String textoProducto = "Producto: " + productoSeleccionado;
            int textWidthProducto = g.getFontMetrics().stringWidth(textoProducto);
            int textXProducto = boxX + (boxWidth - textWidthProducto) / 2;
            int textYProducto = boxY + 60;
            g.drawString(textoProducto, textXProducto, textYProducto);
        }


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

            // Dibuja la imagen del producto
            g.drawImage(img, xImg, yImg, imageWidth, imageHeight, this);

            // NOMBRE y PRECIO desde el enum
            String textoPrecio = productosEnum[i].name() + "  $" + productosEnum[i].getPrecio();
            g.setColor(Color.white);
            g.fillRect(xImg, yImg + imageHeight - 10, imageWidth, 20); // Fondo para el texto
            g.setColor(Color.black);
            g.drawString(textoPrecio, xImg + 5, yImg + imageHeight + 5);

            // Botón de selección
            int xBtn = xImg + imageWidth + 10;
            int yBtn = yImg + (imageHeight - 30) / 2;
            botonesSeleccion[i].setBounds(xBtn, yBtn, 40, 30);
        }
    }
}
