public class SIR_Paralelo {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.nanoTime();
        Dados dSIR = new Dados();

        // Variáveis para armazenar as derivadas
        final double[] novosSuceptivel = new double[1];
        final double[] novosInfectados = new double[1];
        final double[] novosRecuperados = new double[1];

        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        Thread t6;

        // Loop principal: um passo temporal por iteração
        for (int dia = 0; dia < dSIR.dias; dia++) {
            // Calcula as derivadas em paralelo
            t1 = new Thread(() -> novosSuceptivel[0] = (-dSIR.beta * dSIR.suceptivel * dSIR.infectados) / dSIR.populacaoTotal);
            t2 = new Thread(() -> novosInfectados[0] = (dSIR.beta * dSIR.suceptivel * dSIR.infectados) / dSIR.populacaoTotal - (dSIR.gamma * dSIR.infectados));
            t3 = new Thread(() -> novosRecuperados[0] = dSIR.gamma * dSIR.infectados);

            t1.start();
            t2.start();
            t3.start();

            // Aguarda cálculo das derivadas antes de atualizar os estados
            t1.join();
            t2.join();
            t3.join();

            // Agora aplica as atualizações
            t4 = new Thread(() -> dSIR.suceptivel += novosSuceptivel[0] * dSIR.passo);
            t5 = new Thread(() -> dSIR.infectados += novosInfectados[0] * dSIR.passo);
            t6 = new Thread(() -> dSIR.recuperados += novosRecuperados[0] * dSIR.passo);

            t4.start();
            t5.start();
            t6.start();

            // Aguarda término das atualizações para garantir consistência antes de imprimir
            t4.join();
            t5.join();
            t6.join();

            // Imprime o estado atual (dia atual e valores S, I, R)
            System.out.printf("%d\t%.2f\t%.2f\t%.2f\n", dia, dSIR.suceptivel, dSIR.infectados, dSIR.recuperados);
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0; // converte para milissegundos
        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);
    }
}