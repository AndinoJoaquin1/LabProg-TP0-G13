package tpo;

import java.util.Random;
import java.util.concurrent.Callable;

public abstract class Manejador implements Callable<String> {
    protected Manejador sigManejador;
    protected String resultado;
    protected int opcodeManejador;
    protected int opcodeEntrada;

    public String call() throws Exception {
        int tiempoProcesamiento;
        try {
            if (puedeProcerla(this.opcodeEntrada)) {
                Random random = new Random();
                tiempoProcesamiento = 1000 + random.nextInt(2000);
                Thread.sleep(tiempoProcesamiento);
                System.out.println("Se pudo procesar");
            } else {
                System.out.println("No se pudo procesar");
                sigManejador.setOpcodeEntrada(opcodeEntrada);
                sigManejador.call();
            }
        } catch (Exception e) {
        }
        return resultado;
    }

    public void setSigManejador(Manejador m) {
        this.sigManejador = m;
    }

    public void setOpcodeEntrada(int opcode) {
        this.opcodeEntrada = opcodeManejador;
    }

    private boolean puedeProcerla(int opcodeEntrada) {
        return this.opcodeEntrada == this.opcodeManejador;
    }
}
