package tp1.cocurrencia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProgramaAnalizador {
    public static void main(String[] args) {
        int bloquesCorruptosTotales = 0;
        //"disco" fisico
        int[][] disco = {
                { 1, 0, 0, 1, 1, 0, 1, 0, 1, 0 },
                { 1, 1, 1, 0, -1, 0, 1, -1, -1, 1 },
                { 0, 0, 1, 1, -1, -1, 0, 0, 1, 1 },
                { 1, 0, -1, 0, 1, 1, 1, 0, 0, 1 },
                { 0, 1, 1, 0, 0, 1, 0,-1, -1, 0 }
        };
        //genera la pool con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < disco.length; i++) {
            //se ejecuta una tarea la clase AnalizadorDeDiscos
            Future<Integer> retorno = executor.submit(new AnalizadorDeDisco(disco[i]));
            try {
                //se obtiene el valor
                bloquesCorruptosTotales += retorno.get();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Hay un total de " + bloquesCorruptosTotales + " bloques corruptos");
    }
}
