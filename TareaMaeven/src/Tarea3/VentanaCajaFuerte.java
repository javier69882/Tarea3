package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import Tarea1.Moneda;

public class VentanaCajaFuerte extends JFrame {

    private List<Moneda> monedas;
    private Map<Integer, ImageIcon> imagenesMonedas;

    public VentanaCajaFuerte(List<Moneda> monedas) {
        this.monedas = monedas;
        this.imagenesMonedas = new HashMap<>();

        setTitle("Caja Fuerte");
        setSize(1000, 1000); // Cambiar tamaño del JFrame
        setLocationRelativeTo(null);
        setResizable(false);

        cargarImagenesMonedas();

        PanelCaja panelCaja = new PanelCaja(monedas);
        setContentPane(panelCaja);
        setVisible(true);
    }

    private void cargarImagenesMonedas() {
        int[] valores = {100, 500, 1000, 1500};
        for (int valor : valores) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/monedas/" + valor + ".png"));
            Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Ajustar tamaño de las imágenes
            imagenesMonedas.put(valor, new ImageIcon(img));
        }
    }

    private class PanelCaja extends JPanel {
        private List<Moneda> monedas;

        public PanelCaja(List<Moneda> monedas) {
            this.monedas = monedas;
            this.setBackground(Color.WHITE);

            // Agregar MouseListener para cerrar la ventana al hacer clic
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Cerrar la ventana
                    Window w = SwingUtilities.getWindowAncestor(PanelCaja.this);
                    if (w != null) {
                        w.dispose();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 16)); // Aumentar tamaño de fuente
            g.drawString("Monedas almacenadas en caja fuerte:", 20, 40);

            int x = 20, y = 80;
            int total = 0;
            int count = 0;

            for (Moneda m : monedas) {
                int valor = m.getValor();
                int serie = m.getSerie(); // Obtener el número de serie
                total += valor;

                ImageIcon icon = imagenesMonedas.get(valor);
                if (icon != null) {
                    icon.paintIcon(this, g, x, y);
                } else {
                    g.drawString("$" + valor, x + 10, y + 25);
                }

                // Dibujar el número de serie debajo de la imagen
                g.setColor(Color.BLACK);
                g.drawString("Serie: " + serie, x, y + 80);

                x += 100; //  espacio horizontal entre monedas
                count++;
                if (count % 10 == 0) { // 10 monedas por fila
                    x = 20;
                    y += 120; // espacio vertical entre filas
                }
            }

            g.setColor(Color.BLACK);
            g.drawString("Total acumulado: $" + total, 20, y + 100);
        }
    }
}
