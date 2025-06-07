package Tarea3;

import javax.sound.sampled.*;
import java.io.*;

public class MusicaFondo {
    private Clip clip;

    public MusicaFondo() {
        // Constructor por defecto
    }

    public MusicaFondo(String rutaArchivo) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito
            clip.start();
        }
    }

    public void detener() {
        if (clip != null) clip.stop();
    }
    public void reproducirEfecto(String ruta) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
