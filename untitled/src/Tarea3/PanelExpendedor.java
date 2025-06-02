package Tarea3;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {

    private ImageIcon[] productos;
    private JButton[] botonesSeleccion;

    public PanelExpendedor() {
        this.setBackground(Color.lightGray);
        this.setLayout(null); // Diseño absoluto

        // Cargar imágenes de productos
        productos = new ImageIcon[5];
        for (int i = 0; i < 5; i++) {
            productos[i] = new ImageIcon(getClass().getResource("/imagenes/producto" + (i + 1) + ".png"));
        }

        // Inicializar botones de selección
        botonesSeleccion = new JButton[5];
        for (int i = 0; i < 5; i++) {
            botonesSeleccion[i] = new JButton(String.valueOf(i + 1));
            botonesSeleccion[i].setMargin(new Insets(0, 0, 0, 0)); // Quitar espacio extra
            botonesSeleccion[i].setFocusable(false);
            this.add(botonesSeleccion[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        int rectWidth = 120, rectHeight = 500;
        int offsetX = 20;
        int rectX = (panelWidth - rectWidth) / 2 - offsetX;
        int rectY = (panelHeight - rectHeight) / 2;

        // Dibujar rectángulo del expendedor
        g.setColor(Color.darkGray);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Dibujar imágenes y posicionar botones
        int imageWidth = 100;
        int imageHeight = 80;
        int espacio = 10;

        for (int i = 0; i < productos.length; i++) {
            Image img = productos[i].getImage();
            int xImg = rectX + (rectWidth - imageWidth) / 2;
            int yImg = rectY + espacio + i * (imageHeight + espacio);

            // Dibujar imagen
            g.drawImage(img, xImg, yImg, imageWidth, imageHeight, this);

            // Posicionar botón al lado derecho de la imagen
            int xBtn = xImg + imageWidth + 10;
            int yBtn = yImg + (imageHeight - 30) / 2;
            botonesSeleccion[i].setBounds(xBtn, yBtn, 40, 30);
        }
    }
}