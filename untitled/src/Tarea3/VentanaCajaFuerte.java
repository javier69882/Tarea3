package Tarea3;

import javax.swing.*;
import java.awt.*;
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
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        cargarImagenesMonedas();

        setContentPane(new PanelCaja(monedas));
        setVisible(true);
    }

    private void cargarImagenesMonedas() {
        int[] valores = {100, 500, 1000, 1500};
        for (int valor : valores) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/monedas/" + valor + ".png"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            imagenesMonedas.put(valor, new ImageIcon(img));
        }
    }

    private class PanelCaja extends JPanel {
        private List<Moneda> monedas;

        public PanelCaja(List<Moneda> monedas) {
            this.monedas = monedas;
            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("Monedas almacenadas en caja fuerte:", 20, 30);

            int x = 20, y = 60;
            int total = 0;
            int count = 0;

            for (Moneda m : monedas) {
                int valor = m.getValor();
                total += valor;

                ImageIcon icon = imagenesMonedas.get(valor);
                if (icon != null) {
                    icon.paintIcon(this, g, x, y);
                } else {
                    g.drawString("$" + valor, x + 10, y + 25);
                }

                x += 50;
                count++;
                if (count % 8 == 0) { // 8 monedas por fila
                    x = 20;
                    y += 60;
                }
            }

            g.setColor(Color.BLACK);
            g.drawString("Total acumulado: $" + total, 20, y + 60);
        }
    }
}