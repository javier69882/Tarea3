package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComprador extends JPanel {

    private JButton botonComprar;
    private JPanel inventario;
    private boolean inventarioVisible;

    public PanelComprador() {
        this.setBackground(Color.white);
        this.setLayout(null); // Diseño absoluto

        // Crear el botón "Mi billetera"
        botonComprar = new JButton("Mi billetera");
        botonComprar.setBounds(50, 50, 100, 40); // Posición y tamaño del botón
        botonComprar.setFocusable(false);

        // Crear el inventario (rectángulo negro)
        inventario = new JPanel();
        inventario.setBounds(20, 100, 160, 200); // Posición y tamaño del inventario
        inventario.setBackground(Color.black);
        inventario.setLayout(new GridLayout(2, 2, 5, 5)); // Diseño de cuadrícula 2x2 con espacio entre componentes
        inventario.setVisible(false); // Inicialmente oculto

        String[] nombresImagenes = {"100.png", "500.png", "1000.png", "1500.png"};
        for (String nombreImagen : nombresImagenes) {
            JButton botonFoto = new JButton();
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/monedas/" + nombreImagen)); // Cargar la imagen original
            Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Ajustar tamaño
            botonFoto.setIcon(new ImageIcon(imagenRedimensionada)); // Asignar imagen redimensionada
            botonFoto.setFocusable(false);
            inventario.add(botonFoto);
        }

        // Agregar ActionListener al botón "Mi billetera"
        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventarioVisible = !inventarioVisible; // Alternar visibilidad
                inventario.setVisible(inventarioVisible);
            }
        });

        // Agregar componentes al panel
        this.add(botonComprar);
        this.add(inventario);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ¡Siempre llama a esto primero!
    }
}