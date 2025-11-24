
public class SIS_Paralelo {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.nanoTime();
        Dados dSIS = new Dados();

        // Variáveis para chance dos eventos
        final double[] chanceInfecacao = new double[1];
        final double[] chanceRecuperacao = new double[1];
        final int[] novos = {0};
        final int[] recuperadosHoje = {0};

        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        Thread t6;

        for(int dia = 0; dia < dSIS.dias; dia++){
            // Calcula probabilidades
            t1 = new Thread(() -> chanceInfecacao[0] = (dSIS.beta * dSIS.infectados)/ dSIS.populacaoTotal * dSIS.passo);
            t2 = new Thread(() -> chanceRecuperacao[0] = dSIS.gamma * dSIS.passo);

            t1.start();
            t2.start();

            // Aguarda as probabilidades estarem disponíveis
            t1.join();
            t2.join();

            // Conta quantas novas infecções ocorrem (percorre cada suscetível)
            t3 = new Thread(() -> {
                for (int S = 0; S < dSIS.suceptivel; S++) {
                    if (Math.random() < chanceInfecacao[0]) {
                        novos[0]++;
                    }
                }
            });

            // Conta quantas recuperações ocorrem (percorre cada infectado)
            t4 = new Thread(() -> {
                for (int S = 0; S < dSIS.infectados; S++) {
                    if (Math.random() < chanceRecuperacao[0]) {
                        recuperadosHoje[0]++;
                    }
                }
            });

            t3.start();
            t4.start();

            // Aguarda o término da contagem
            t3.join();
            t4.join();

            // Atualiza os estados em paralelo
            t5 = new Thread(() -> dSIS.infectados += novos[0] - recuperadosHoje[0]);
            t6 = new Thread(() -> dSIS.suceptivel += recuperadosHoje[0] - novos[0]);
            t5.start();
            t6.start();

            t5.join();
            t6.join();

            // Imprime o estado do dia
            System.out.printf("%d\t%.2f\t%.2f\n", dia, dSIS.suceptivel, dSIS.infectados);

            // Reinicia contadores para a próxima iteração
            novos[0] = 0;
            recuperadosHoje[0] = 0;
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);
    }
}
