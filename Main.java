import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        try {
            int cantCol = 10, cantFilas = 10, cantHilos = 5, resultado = 0;
            int[][] disco = new int[cantFilas][cantCol];
            ExecutorService executor = Executors.newFixedThreadPool(cantHilos); // Inicializo una pool de hilos
            Contador[] contadores = new Contador[cantHilos];
            Future<Integer>[] resultados = new Future[cantHilos];

            // Inicializo el disco para simular un disco con bloques corrompidos (-1),
            // vacios (0), ocupados (1)
            for (int i = 0; i < disco.length; i++) {
                for (int j = 0; j < disco[0].length; j++) {
                    double r = Math.random();
                        if (r < 0.33) disco[i][j] = -1;
                        else if (r < 0.66) disco[i][j] = 0;
                        else disco[i][j] = 1;
                }
            }

            // Inicializo los contadores dandole la dirección del disco y las direcciones de
            // inicio y final
            for (int i = 0; i < cantHilos; i++) {
                contadores[i] = new Contador(disco, (cantFilas / cantHilos) * i, (cantFilas / cantHilos) * (i + 1) - 1);
            }

            // Le entrego los contadores a la pool de hilos para que los resuelvan
            for (int i = 0; i < cantHilos; i++) {
                resultados[i] = executor.submit(contadores[i]);
            }

            // Obtengo los resultados de cada contador y los sumo
            for (int i = 0; i < cantHilos; i++) {
                resultado += resultados[i].get();
            }

            // Imprimo el resultado por pantalla
            System.out.println(resultado);

            // Termino la execución de la pool de hilos
            executor.shutdown();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
