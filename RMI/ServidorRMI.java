import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServidorRMI {
    public static void main(String args[]){
        try {

            // Instancia as implementações remotas
            SIS ModeloSIS = new SISImpl();
            SIR ModeloSIR = new SIRImpl();

            // Cria um registry RMI na porta 5000
            Registry registry = LocateRegistry.createRegistry(5000);

            // Associa nomes aos objetos remotos para lookup pelos clientes
            registry.rebind("SIS", ModeloSIS);
            registry.rebind("SIR", ModeloSIR);

            System.out.println("Servidor ativo porta: 5000");
        }
        catch (Exception e) {
            // Caso aconteça algum erro de rede ou criação, exibe mensagem
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}