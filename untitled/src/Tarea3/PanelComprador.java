package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PanelComprador extends JPanel {

    private JButton botonComprar;
    private JPanel inventario;
    private boolean inventarioVisible;
    private JButton[] botonesMonedas = new JButton[4];

    private JButton botonMochila;
    private JPanel mochilaPanel;
    private boolean mochilaVisible;

    private JButton espacioProducto;
    private Map<String, ImageIcon> imagenesProductos;

    public PanelComprador() {
        this.setOpaque(false);
        this.setLayout(null);

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
        for (int i = 0; i < nombresImagenes.length; i++) {
            JButton botonFoto = new JButton();
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/monedas/" + nombresImagenes[i]));
            Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            botonFoto.setIcon(new ImageIcon(imagenRedimensionada));
            botonFoto.setFocusable(false);
            inventario.add(botonFoto);
            botonesMonedas[i] = botonFoto;
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

        // Panel para la mochila
        mochilaPanel = new JPanel();
        mochilaPanel.setBounds(20, 350, 160, 200);
        mochilaPanel.setBackground(Color.gray);
        mochilaPanel.setLayout(new GridLayout(1, 1, 5, 5));
        mochilaPanel.setVisible(false);

        // Cargar imágenes de productos
        imagenesProductos = new HashMap<>();
        cargarImagenProducto("COCA", "/imagenes/producto1.png");
        cargarImagenProducto("FANTA", "/imagenes/producto2.png");
        cargarImagenProducto("SPRITE", "/imagenes/producto3.png");
        cargarImagenProducto("SUPER8", "/imagenes/producto4.png");
        cargarImagenProducto("SNIKERS", "/imagenes/producto5.png");

        espacioProducto = new JButton();
        espacioProducto.setFocusable(false);
        espacioProducto.setContentAreaFilled(false); // sin fondo
        espacioProducto.setBorderPainted(false);     // sin borde
        mochilaPanel.add(espacioProducto);

        this.add(botonComprar);
        this.add(inventario);
        this.add(botonMochila);
        this.add(mochilaPanel);
    }

    private void cargarImagenProducto(String nombre, String ruta) {
        java.net.URL url = getClass().getResource(ruta);
        if (url != null) {
            ImageIcon iconoOriginal = new ImageIcon(url);
            Image img = iconoOriginal.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            imagenesProductos.put(nombre, new ImageIcon(img));
        } else {
            System.err.println("No se encontró la imagen: " + ruta);
        }
    }

    public JButton[] getBotonesMonedas() {
        return botonesMonedas;
    }

    // --- NUEVO: método para normalizar el nombre del producto --- //
    private String normalizaNombreProducto(String nombre) {
        if (nombre == null) return null;
        nombre = nombre.trim().toUpperCase();
        switch (nombre) {
            case "COCACOLA":
            case "COCA":
            case "COCA-COLA":
                return "COCA";
            case "FANTA":
                return "FANTA";
            case "SPRITE":
                return "SPRITE";
            case "SUPER8":
            case "SUPER 8":
                return "SUPER8";
            case "SNICKERS":
            case "SNIKERS":
                return "SNIKERS";
            default:
                return nombre; // intentar usarlo tal cual
        }
    }


    public void setProductoEnMochila(String nombreProducto, int numeroSerie) {
        String nombreKey = normalizaNombreProducto(nombreProducto);
        if (nombreKey != null && !nombreKey.isEmpty() && imagenesProductos.containsKey(nombreKey)) {
            espacioProducto.setIcon(imagenesProductos.get(nombreKey));
            espacioProducto.setText("Serie: " + numeroSerie);
            espacioProducto.setHorizontalTextPosition(SwingConstants.CENTER);
            espacioProducto.setVerticalTextPosition(SwingConstants.BOTTOM);
        } else {
            espacioProducto.setIcon(null);
            espacioProducto.setText("");
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
