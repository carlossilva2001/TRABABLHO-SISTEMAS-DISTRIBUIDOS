
public class SIR_Sequencial {
    public static void main(String[] args) {
        long inicio = System.nanoTime();
        Dados dSIR = new Dados();

        for (int dia = 0; dia < dSIR.dias; dia++) {
            // Calcula as variações derivadas
            double novosSuceptivel = (-dSIR.beta * dSIR.suceptivel * dSIR.infectados) / dSIR.populacaoTotal;
            double novosInfectados = (dSIR.beta * dSIR.suceptivel * dSIR.infectados) / dSIR.populacaoTotal - (dSIR.gamma * dSIR.infectados);
            double novosRecuperados = dSIR.gamma * dSIR.infectados;

            // Atualiza os estados
            dSIR.suceptivel += novosSuceptivel * dSIR.passo;
            dSIR.infectados += novosInfectados * dSIR.passo;
            dSIR.recuperados += novosRecuperados * dSIR.passo;

            // Imprime o estado atual: dia, S, I, R
            System.out.printf("%d\t%.2f\t%.2f\t%.2f\n", dia, dSIR.suceptivel, dSIR.infectados, dSIR.recuperados);
        }
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0; // converte para milissegundos

        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);
    }
}
