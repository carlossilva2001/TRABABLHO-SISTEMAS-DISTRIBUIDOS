import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServidorRMI {
    public static void main(String args[]){
        try {

            SIS ModeloSIS = new SISImpl();
//            SIR ModeloSIR = new SIRImpl();

            Registry registry = LocateRegistry.createRegistry(5000);

            registry.rebind("SIS", ModeloSIS);
//            Registry.rebind("SIR", ModeloSIR);

            System.out.println("Servidor ativo porta: 5000");
        }
        catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}