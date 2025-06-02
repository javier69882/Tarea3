package Tarea3;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private PanelExpendedor exp;
    private PanelComprador com;

    public PanelPrincipal() {
        this.setBackground(Color.white);
        this.setLayout(null); // Diseño absoluto

        // Crear el PanelExpendedor
        exp = new PanelExpendedor();
        exp.setBounds(400, 100, 300, 600); // Posición y tamaño del PanelExpendedor
        com = new PanelComprador();
        com.setBounds(20, 100, 200, 600); // Posición y tamaño del PanelComprador
        // Agregar componentes al panel

        this.add(exp); // Agregar el PanelExpendedor
        this.add(com); // Agregar el PanelComprador
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ¡Siempre llama a esto primero!
    }
}