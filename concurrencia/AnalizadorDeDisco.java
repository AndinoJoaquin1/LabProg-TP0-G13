package concurrencia;

import java.util.concurrent.Callable;

public class AnalizadorDeDisco implements Callable<Integer> {
    private int[] filaAsignada;

    public AnalizadorDeDisco(int[] f) {
        this.filaAsignada = f;
    }

    public Integer call() throws Exception {
        int bloquesCorruptos = 0;
        for (int i = 0; i < filaAsignada.length; i++) {
            if (filaAsignada[i] == -1) {
                bloquesCorruptos++;
            }
        }
        return bloquesCorruptos;
    }
}
