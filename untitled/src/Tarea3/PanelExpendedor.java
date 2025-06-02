package Tarea3;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {

    public PanelExpendedor() {
        this.setBackground(Color.lightGray);
        this.setLayout(null); // Diseño absoluto

        // Crear componentes
        JLabel etiqueta = new JLabel("Panel Expendedor");
        etiqueta.setBounds(10, 10, 150, 30); // Posición y tamaño de la etiqueta

        // Agregar componentes al panel
        this.add(etiqueta);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ¡Siempre llama a esto primero!

        // Obtener el tamaño del panel
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        // Calcular las coordenadas para centrar el rectángulo
        int rectWidth = 120, rectHeight = 500;
        int offsetX = 20; // Desplazamiento hacia la izquierda
        int rectX = (panelWidth - rectWidth) / 2 - offsetX;
        int rectY = (panelHeight - rectHeight) / 2;

        // Dibujar el rectángulo desplazado
        g.setColor(Color.darkGray);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
    }
}