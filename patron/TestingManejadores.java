package tp1.patron;
public class TestingManejadores {

    public static void main(String[] args) {
        Manejador h1 = new ManejadorErroresHardware();
        Manejador h2 = new ManejadorErroresIO();
        Manejador h3 = new ManejadorErroresMemoria();
        Manejador h4 = new ManejadorErroresSeguridad();
        h1.setSigManejador(h2);
        h2.setSigManejador(h3);
        h3.setSigManejador(h4);

        h1.tratarError("0x0000");
        h1.tratarError("0x01BE"); 
        h1.tratarError("0x02C1"); 
        h1.tratarError("0x03FF");
        h1.tratarError("0xA3B7");
    }
}
