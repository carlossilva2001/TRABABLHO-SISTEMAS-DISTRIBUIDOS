import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);

        double suceptivel = 0.0;
        double infectados = 0.0;
        double recuperados = 0.0;
        double populacaoTotal = 0.0;
        double beta = 0.0;   // Taxa de transmissão
        double gamma = 0.0;  // Taxa de recuperação
        int dias = 0; // Número de dias para simulação
        double passo = 0.0;
        String resultado = "";

        System.out.println("Bem vindo ao Cliente RMI para simulação SIR e SIS.");

        System.out.println("Opções de Serviços: ");
        System.out.println("1 - Modelo SIR");
        System.out.println("2 - Modelo SIS");
        System.out.print("Escolha o modelo (1 ou 2): ");
        int escolha = entrada.nextInt();

        // Coleta dos estados iniciais
        System.out.print("Digite o número de suscetíveis iniciais: ");
        suceptivel = entrada.nextDouble();
        System.out.print("Digite o número de infectados iniciais: ");
        infectados = entrada.nextDouble();
        if(escolha == 1){
            // Apenas o modelo SIR usa recuperados iniciais
            System.out.print("Digite o número de recuperados iniciais: ");
            recuperados = entrada.nextDouble();
        }
        populacaoTotal = suceptivel + infectados + recuperados;
        System.out.print("Digite a taxa de transmissão (beta): ");
        beta = entrada.nextDouble();
        System.out.print("Digite a taxa de recuperação (gamma): ");
        gamma = entrada.nextDouble();
        System.out.print("Digite o número de dias para simulação: ");
        dias = entrada.nextInt();
        System.out.print("Digite o passo de tempo (ex: 0.1): ");
        passo = entrada.nextDouble();
        entrada.close();

        try {
            // Obtém o registry local e faz lookup pelas implementações remotas
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);
            SIR sir = (SIR) registry.lookup("SIR");
            SIS sis = (SIS) registry.lookup("SIS");

            if(escolha == 1){
                long inicio = System.currentTimeMillis();
                // Chamada remota para calcular o modelo SIR
                resultado = sir.CalcularSIR(suceptivel, infectados, recuperados, populacaoTotal, beta, gamma, dias, passo);
                long fim = System.currentTimeMillis();
                System.out.println("Tempo de execução SIR: " + (fim - inicio) + " ms");
            } else if (escolha == 2){
                long inicio = System.currentTimeMillis();
                // Chamada remota para calcular o modelo SIS
                resultado = sis.CalcularSIS(suceptivel, infectados, populacaoTotal, beta, gamma, dias, passo);
                long fim = System.currentTimeMillis();
                System.out.println("Tempo de execução SIS: " + (fim - inicio) + " ms");
            } else {
                System.out.println("Opção inválida. Encerrando o cliente.");
            }

        } catch (Exception e) {
            // Imprime mensagem de erro caso a chamada RMI falhe
            System.out.println("Erro no cliente: " + e.getMessage());
        }
        
        // Exibe o resultado retornado pelo servidor
        System.out.println("Resultados da simulação:");
        System.out.println(resultado);
    }

}
