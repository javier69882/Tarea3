package Tarea3;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Clase para manejar la reproducción de música de fondo y efectos de sonido
 * en una aplicación Java utilizando la API javax.sound.sampled.
 */
public class MusicaFondo{
    private Clip clip;

    /**
     * Constructor por defecto. No carga ningún archivo de audio.
     */
    public MusicaFondo(){
        // Constructor por defecto
    }

    /**
     * Constructor que carga un archivo de audio especificado para reproducirlo como música de fondo.
     */
    public MusicaFondo(String rutaArchivo) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia la reproducción de la música de fondo en bucle continuo.
     */
    public void reproducirLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito
            clip.start();
        }
    }

    /**
     * Detiene la reproducción actual de la música de fondo.
     */
    public void detener() {
        if (clip != null) clip.stop();
    }

    /**
     * Reproduce un efecto de sonido desde el archivo especificado.
     * No interfiere con la música de fondo que esté sonando.
     */
    public void reproducirEfecto(String ruta) {
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
