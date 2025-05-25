package sudoku.controlador;

import javax.swing.*;

public class TemporizadorLogica {
    private int segundosRestantes;
    private final Timer timer;
    private final Runnable onTick;
    private final Runnable onTimeout;

    public TemporizadorLogica(int segundosIniciales, Runnable onTick, Runnable onTimeout) {
        this.segundosRestantes = segundosIniciales;
        this.onTick = onTick;
        this.onTimeout = onTimeout;

        this.timer = new Timer(1000, e -> tick());
    }

    private void tick() {
        segundosRestantes--;
        onTick.run();
        if (segundosRestantes <= 0) {
            detener();
            onTimeout.run();
        }
    }

    public void iniciar() {
        timer.start();
    }

    public void detener() {
        timer.stop();
    }

    public void reiniciar(int nuevosSegundos) {
        detener();
        this.segundosRestantes = nuevosSegundos;
        onTick.run();
        iniciar();
    }

    public int getSegundosRestantes() {
        return segundosRestantes;
    }
}
