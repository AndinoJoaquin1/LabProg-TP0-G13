package concurrencia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProgramaAnalizador {
    public static void main(String[] args) {
        int bloquesCorruptosTotales = 0;
        int[][] disco = new int[10][10];

        // Inicializo el disco para simular un disco con bloques corrompidos (-1),
        // vacios (0), ocupados (1)
        for (int i = 0; i < disco.length; i++) {
            for (int j = 0; j < disco[0].length; j++) {
                double r = Math.random();
                if (r < 0.33)
                    disco[i][j] = -1;
                else if (r < 0.66)
                    disco[i][j] = 0;
                else
                    disco[i][j] = 1;
            }
        }

        // genera la pool con 4 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < disco.length; i++) {
            // se ejecuta una tarea la clase AnalizadorDeDiscos
            Future<Integer> retorno = executor.submit(new AnalizadorDeDisco(disco[i]));
            try {
                // se obtiene el valor
                bloquesCorruptosTotales += retorno.get();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Hay un total de " + bloquesCorruptosTotales + " bloques corruptos");

        // Termino la execución de la pool de hilos
        executor.shutdown();
    }
}
