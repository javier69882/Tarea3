package Tarea3;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Ventana emergente que muestra un GIF animado del producto comprado
 * y reproduce un efecto de sonido para simular la entrega del producto.
 * La ventana se cierra automáticamente después de 5 segundos.
 *
 * Ejemplo: Si el producto es "COCA", se buscará el archivo "/gifs/coca.gif".
 */


public class VentanaGifProducto extends JFrame {
    private MusicaFondo musicaFondo;

    /**
     * Constructor que crea y muestra una ventana animada con el producto adquirido.
     * También reproduce un efecto de sonido de compra.
     */

    public VentanaGifProducto(String producto) {
        MusicaFondo musicaFondo = new MusicaFondo();
        musicaFondo.reproducirEfecto("/Musica/compra.wav");
        setTitle("¡Disfruta tu " + producto + "!");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Ajusta la ruta según donde pongas los gifs
        String rutaGif = "/gifs/" + producto.toLowerCase() + ".gif";
        ImageIcon gif = new ImageIcon(getClass().getResource(rutaGif));
        JLabel label = new JLabel(gif);

        add(label);
        setVisible(true);

        // Cerrar la ventana después de 5 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                dispose();
            }
        }, 5000);
    }
}
