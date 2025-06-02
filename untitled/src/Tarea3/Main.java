package Tarea3;
import javax.swing.*;



public class Main {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Ventana Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000); // Tamaño de la ventana

        // Crear una instancia de PanelPrincipal y agregarla al marco
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        frame.add(panelPrincipal);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}

