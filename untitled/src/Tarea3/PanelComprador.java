package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComprador extends JPanel {

    private JButton botonComprar;
    private JPanel inventario;
    private boolean inventarioVisible;

    private JButton botonMochila;
    private JPanel mochilaPanel;
    private boolean mochilaVisible;


    public PanelComprador() {
        this.setBackground(Color.white);
        this.setLayout(null); // Diseño absoluto

        // Botón "Mi billetera"
        botonComprar = new JButton("Mi billetera");
        botonComprar.setBounds(50, 50, 100, 40);
        botonComprar.setFocusable(false);

        // Inventario (billetera)
        inventario = new JPanel();
        inventario.setBounds(20, 100, 160, 200);
        inventario.setBackground(Color.black);
        inventario.setLayout(new GridLayout(2, 2, 5, 5));
        inventario.setVisible(false);

        String[] nombresImagenes = {"100.png", "500.png", "1000.png", "1500.png"};
        for (String nombreImagen : nombresImagenes) {
            JButton botonFoto = new JButton();
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/monedas/" + nombreImagen));
            Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            botonFoto.setIcon(new ImageIcon(imagenRedimensionada));
            botonFoto.setFocusable(false);
            inventario.add(botonFoto);
        }

        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventarioVisible = !inventarioVisible;
                inventario.setVisible(inventarioVisible);
            }
        });

        botonMochila = new JButton("Mochila");
        botonMochila.setBounds(50, 300, 100, 40);
        botonMochila.setFocusable(false);
        botonMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mochilaVisible = !mochilaVisible;
                mochilaPanel.setVisible(mochilaVisible);
            }
        });


        mochilaPanel = new JPanel();
        mochilaPanel.setBounds(20, 350, 160, 160);
        mochilaPanel.setBackground(Color.gray);
        mochilaPanel.setLayout(new GridLayout(1, 1, 5, 5)); // 1 espacio para producto
        mochilaPanel.setVisible(false);




        JButton espacioProducto = new JButton();
        espacioProducto.setEnabled(false); // Inicialmente vacío
        mochilaPanel.add(espacioProducto);

        this.add(botonComprar);
        this.add(inventario);
        this.add(botonMochila);
        this.add(mochilaPanel);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}