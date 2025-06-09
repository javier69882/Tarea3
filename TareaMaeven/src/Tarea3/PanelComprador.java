package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase {@code PanelComprador} representa un panel de usuario que permite simular
 * la interacción de un comprador con una billetera virtual y una mochila de productos
 * dentro de una interfaz gráfica Swing.
 * <p>
 * Incluye funcionalidades para:
 * <ul>
 *   <li>Mostrar un inventario de monedas (billetera)</li>
 *   <li>Visualizar productos adquiridos en una mochila</li>
 *   <li>Reproducir efectos de sonido al interactuar</li>
 * </ul>
 *
 * Este panel es parte de una simulación de compra como en una máquina expendedora.
 */
public class PanelComprador extends JPanel {

    /** Botón para mostrar/ocultar la billetera del usuario. */
    private JButton botonComprar;

    /** Panel que contiene los botones con imágenes de monedas. */
    private JPanel inventario;

    /** Bandera que indica si la billetera está visible. */
    private boolean inventarioVisible;

    /** Botones que representan las distintas monedas disponibles. */
    private JButton[] botonesMonedas = new JButton[4];

    /** Botón para mostrar/ocultar la mochila del usuario. */
    private JButton botonMochila;

    /** Panel que muestra los productos adquiridos por el usuario. */
    private JPanel mochilaPanel;

    /** Bandera que indica si la mochila está visible. */
    private boolean mochilaVisible;

    /** Espacio para mostrar un producto dentro de la mochila. */
    private JButton espacioProducto;

    /** Mapa que asocia el nombre del producto con su imagen. */
    private Map<String, ImageIcon> imagenesProductos;

    /**
     * Constructor que inicializa y configura todos los componentes gráficos
     * del panel del comprador.
     */
    public PanelComprador() {
        // Configuración del panel principal
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

        // Carga de imágenes de monedas
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

        // Acción al presionar "Mi billetera"
        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventarioVisible = !inventarioVisible;
                inventario.setVisible(inventarioVisible);

                MusicaFondo musica = new MusicaFondo();
                musica.reproducirEfecto("/Musica/Billetera.wav");
            }
        });

        // Botón y panel para la mochila
        botonMochila = new JButton("Mochila");
        botonMochila.setBounds(50, 300, 100, 40);
        botonMochila.setFocusable(false);
        botonMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mochilaVisible = !mochilaVisible;
                mochilaPanel.setVisible(mochilaVisible);

                MusicaFondo musica = new MusicaFondo();
                musica.reproducirEfecto("/Musica/Mochila.wav");
            }
        });

        mochilaPanel = new JPanel();
        mochilaPanel.setBounds(20, 350, 160, 200);
        mochilaPanel.setBackground(Color.gray);
        mochilaPanel.setLayout(new GridLayout(1, 1, 5, 5));
        mochilaPanel.setVisible(false);

        // Carga de imágenes de productos
        imagenesProductos = new HashMap<>();
        cargarImagenProducto("COCA", "/imagenes/producto1.png");
        cargarImagenProducto("FANTA", "/imagenes/producto2.png");
        cargarImagenProducto("SPRITE", "/imagenes/producto3.png");
        cargarImagenProducto("SUPER8", "/imagenes/producto4.png");
        cargarImagenProducto("SNIKERS", "/imagenes/producto5.png");

        espacioProducto = new JButton();
        espacioProducto.setFocusable(false);
        espacioProducto.setContentAreaFilled(false);
        espacioProducto.setBorderPainted(false);
        mochilaPanel.add(espacioProducto);

        // Agregar componentes al panel principal
        this.add(botonComprar);
        this.add(inventario);
        this.add(botonMochila);
        this.add(mochilaPanel);
    }

    /**
     * Carga una imagen de producto y la asocia a un nombre clave.
     */
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

    /**
     * Devuelve el arreglo de botones de monedas (billetera).
     */
    public JButton[] getBotonesMonedas() {
        return botonesMonedas;
    }

    /**
     * Normaliza el nombre del producto para que coincida con los nombres
     * de clave en el mapa de imágenes.
     */
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
                return nombre;
        }
    }

    /**
     * Muestra un producto en la mochila del usuario con su número de serie.
     */
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

    /**
     * Método sobrescrito para dibujar el componente personalizado.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
