package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import Tarea1.PrecioProducto;
import Tarea1.Expendedor;
import Tarea1.Moneda100;
import Tarea1.Moneda500;
import Tarea1.Moneda1000;
import Tarea1.Moneda1500;
import Tarea1.Moneda;

public class PanelExpendedor extends JPanel {

    private JButton botonRestock;
    private JButton botonCajaFuerte;
    private Expendedor expendedorLogico;
    private Runnable onRestockCallback = null;
    private Runnable onCajaFuerteCallback = null;

    private ImageIcon[] productos;
    private JButton[] botonesSeleccion;
    private PrecioProducto[] productosEnum = {
            PrecioProducto.COCA,
            PrecioProducto.FANTA,
            PrecioProducto.SPRITE,
            PrecioProducto.SUPER8,
            PrecioProducto.SNIKERS
    };
    private boolean[] agotado = new boolean[productosEnum.length];

    private int valorMonedaSeleccionada = 0;
    private String productoSeleccionado = null;
    private String mensajeEstado = "";
    private String productoEntregado = "";
    private int vuelto = 0;

    private List<Integer> monedasVuelto = new ArrayList<>();
    private List<Rectangle> monedaAreas = new ArrayList<>();
    private Map<Integer, ImageIcon> imagenesMonedas = new HashMap<>();

    public PanelExpendedor() {
        this.setBackground(Color.lightGray);
        this.setLayout(null);

        productos = new ImageIcon[productosEnum.length];
        for (int i = 0; i < productosEnum.length; i++) {
            productos[i] = new ImageIcon(getClass().getResource("/imagenes/producto" + (i + 1) + ".png"));
        }

        cargarImagenesMonedas();

        botonesSeleccion = new JButton[productosEnum.length];
        for (int i = 0; i < productosEnum.length; i++) {
            botonesSeleccion[i] = new JButton(String.valueOf(i + 1));
            botonesSeleccion[i].setMargin(new Insets(0, 0, 0, 0));
            botonesSeleccion[i].setFocusable(false);
            this.add(botonesSeleccion[i]);
        }

        botonRestock = new JButton("Restock");
        botonRestock.setBounds(30, 20, 100, 30);
        botonRestock.setFocusable(false);
        this.add(botonRestock);

        // Botón Caja Fuerte
        botonCajaFuerte = new JButton("Caja Fuerte");
        botonCajaFuerte.setBounds(280, 700, 120, 30);
        botonCajaFuerte.setFocusable(false);
        this.add(botonCajaFuerte);

        botonRestock.addActionListener(e -> {
            if (expendedorLogico != null && onRestockCallback != null) {
                onRestockCallback.run();
            }
        });

        // Listener para abrir ventana de caja fuerte
        botonCajaFuerte.addActionListener(e -> {
            if (onCajaFuerteCallback != null) {
                onCajaFuerteCallback.run();
            }
        });

        // Listener para recoger monedas (vuelto)
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point click = e.getPoint();
                for (Rectangle area : monedaAreas) {
                    if (area.contains(click)) {
                        monedasVuelto.clear();
                        monedaAreas.clear();
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    public void abrirVentanaCajaFuerteConMonedas(List<Moneda> monedas) {
        new VentanaCajaFuerte(monedas);
    }

    private void cargarImagenesMonedas() {
        int[] valores = {100, 500, 1000, 1500};
        for (int valor : valores) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/monedas/" + valor + ".png"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            imagenesMonedas.put(valor, new ImageIcon(img));
        }
    }

    public void setMonedasVuelto(List<Integer> monedas) {
        this.monedasVuelto = monedas;
        repaint();
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

    public void setMensajeEstado(String mensaje) {
        this.mensajeEstado = mensaje;
        repaint();
    }

    public void actualizarEstadoProductos(Expendedor expendedor) {
        for (int i = 0; i < productosEnum.length; i++) {
            agotado[i] = (expendedor.getStock(productosEnum[i]) == 0);
        }
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

    public void setExpendedorLogico(Expendedor expendedor) {
        this.expendedorLogico = expendedor;
    }

    public void setOnRestockCallback(Runnable callback) {
        this.onRestockCallback = callback;
    }

    public void setOnCajaFuerteCallback(Runnable callback) {
        this.onCajaFuerteCallback = callback;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = this.getWidth();
        int boxWidth = 180;
        int boxHeight = 80;
        int boxX = panelWidth - boxWidth - 20;
        int boxY = 15;

        g.setColor(Color.white);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);
        g.setColor(Color.black);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);

        int y = boxY + 18;
        g.setFont(new Font("Arial", Font.PLAIN, 11));

        if (valorMonedaSeleccionada > 0) {
            g.drawString("Saldo: $" + valorMonedaSeleccionada, boxX + 10, y);
            y += 14;
        }

        if (productoSeleccionado != null) {
            g.drawString("Producto: " + productoSeleccionado, boxX + 10, y);
            y += 14;
        }

        if (!mensajeEstado.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 10));
            g.setColor(Color.RED);
            g.drawString(mensajeEstado, boxX + 10, y);
            y += 13;
        }

        if (vuelto > 0) {
            g.setFont(new Font("Arial", Font.BOLD, 10));
            g.setColor(new Color(7, 222, 7));
            g.drawString("Vuelto total: $" + vuelto, boxX + 10, y);
            y += 13;
        }

        if (!productoEntregado.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 11));
            g.setColor(Color.BLUE.darker());
            g.drawString("Entregado: " + productoEntregado, boxX + 10, y);
        }

        // Mostrar monedas del vuelto
        if (!monedasVuelto.isEmpty()) {
            monedaAreas.clear();
            int startX = boxX + 10;
            int startY = boxY + boxHeight + 20;
            g.setColor(Color.black);
            g.drawString("Vuelto:", startX, startY - 5);

            int offsetX = 0;
            for (int valor : monedasVuelto) {
                ImageIcon icon = imagenesMonedas.get(valor);
                if (icon != null) {
                    int x = startX + offsetX;
                    int yMoneda = startY;
                    icon.paintIcon(this, g, x, yMoneda);
                    monedaAreas.add(new Rectangle(x, yMoneda, 40, 40));
                    offsetX += 45;
                }
            }
        }

        // Dibujar productos
        int rectWidth = 120, rectHeight = 500;
        int offsetX = 20;
        int rectX = (panelWidth - rectWidth) / 2 - offsetX;
        int rectY = (getHeight() - rectHeight) / 2;

        g.setColor(Color.darkGray);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        int imageWidth = 100;
        int imageHeight = 80;
        int espacio = 10;

        for (int i = 0; i < productos.length; i++) {
            int xImg = rectX + (rectWidth - imageWidth) / 2;
            int yImg = rectY + espacio + i * (imageHeight + espacio);

            if (!agotado[i]) {
                g.drawImage(productos[i].getImage(), xImg, yImg, imageWidth, imageHeight, this);
                g.setColor(Color.white);
                g.fillRect(xImg, yImg + imageHeight - 10, imageWidth, 20);
                g.setColor(Color.black);
                g.drawString(productosEnum[i].name() + "  $" + productosEnum[i].getPrecio(), xImg + 5, yImg + imageHeight + 5);
                botonesSeleccion[i].setEnabled(true);
            } else {
                g.setColor(Color.gray);
                g.fillRect(xImg, yImg, imageWidth, imageHeight);
                g.setColor(Color.red.darker());
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("AGOTADO", xImg + 10, yImg + imageHeight / 2);
                botonesSeleccion[i].setEnabled(false);
            }

            int xBtn = xImg + imageWidth + 10;
            int yBtn = yImg + (imageHeight - 30) / 2;
            botonesSeleccion[i].setBounds(xBtn, yBtn, 45, 30);
        }
    }
}