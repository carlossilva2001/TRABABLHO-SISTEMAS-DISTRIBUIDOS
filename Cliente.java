import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    
public static void main(String[] args) {
    double suceptivel = 900000.0;
    double infectados = 100000.0;
    double recuperados = 0.0;
    double populacaoTotal = Math.max(suceptivel + infectados + recuperados, 0.0);
    double beta = 0.3;   // Taxa de transmissão
    double gamma = 0.1;  // Taxa de recuperação
    int dias = 365; // Número de dias para simulação
    double passo = 0.1;

    try {
        Registry registry = LocateRegistry.getRegistry("localhost", 5000);
        SIS sis = (SIS) registry.lookup("SIS");

        String resultado = sis.CalcularSIS(suceptivel, infectados, populacaoTotal, beta, gamma, dias, passo);
        System.out.println("Resultados da simulação SIS:");
        System.out.println(resultado);
    } catch (Exception e) {
        System.out.println("Erro no cliente: " + e.getMessage());
    }
}

}
