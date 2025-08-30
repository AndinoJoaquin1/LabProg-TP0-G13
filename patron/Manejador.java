package tp1.patron;
public abstract class Manejador {
       protected  Manejador sigManejador;
       protected  String codManejador;
       protected String msgSolucion;

    public void setSigManejador(Manejador m) {
        this.sigManejador=m;
    }

    public boolean formatoValido(String codError) {
        return codError != null && codError.startsWith("0x") && codError.length() >= 4;
    }

    public void tratarError(String codError){
         char c1 = codError.charAt(2), c2 = codError.charAt(3);
        if (c1 == codManejador.charAt(0) && c2 == codManejador.charAt(1)) {
            System.out.println(msgSolucion + codError);
        } else {
               if (sigManejador != null) {
            sigManejador.tratarError(codError);
        } else {
            System.out.println("Nadie pudo manejar el error con codigo: " + codError);
        }
        }
    }
}
