import java.util.concurrent.Callable;

public class Contador implements Callable<Integer> {
    private int[][] disco;
    private int filaInicio;
    private int filaFin;

    public Contador(int[][] disco, int filaInicio, int filaFin) {
        this.disco = disco;
        this.filaInicio = filaInicio;
        this.filaFin = filaFin;
    }

    @Override
    public Integer call() throws Exception {
        int contador = 0;
        for (int i = filaInicio; i <= filaFin; i++) {
            for (int j = 0; j < disco[0].length; j++) {
                if (disco[i][j] < 0) {
                    contador++;
                }
            }
        }
        return contador;
    }
}
