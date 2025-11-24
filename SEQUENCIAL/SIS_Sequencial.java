
public class SIS_Sequencial{

    public static void main(String[] args) {
        long inicio = System.nanoTime();
        Dados dSIS = new Dados();

        for(int dia = 0; dia < dSIS.dias; dia++){
            double chanceInfecacao = (dSIS.beta * dSIS.infectados)/ dSIS.populacaoTotal * dSIS.passo;
            double chanceRecuperacao = dSIS.gamma * dSIS.passo;

            int novos = 0;
            for(int S = 0; S < dSIS.suceptivel; S++){
                // Para cada suscetível, sorteia evento de infecção
                if(Math.random() < chanceInfecacao){
                    novos++;
                }
            }
            int recuperadosHoje = 0;
            for(int I = 0; I < dSIS.infectados; I++){
                // Para cada infectado, sorteia evento de recuperação
                if(Math.random() < chanceRecuperacao){
                    recuperadosHoje++;
                }
            }

            // Atualiza os estados conforme os eventos do dia
            dSIS.infectados += novos - recuperadosHoje;
            dSIS.suceptivel += recuperadosHoje - novos;

            System.out.printf("%d\t%.2f\t%.2f\n", dia, dSIS.suceptivel, dSIS.infectados);
        }
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0; // converte para milissegundos

        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);

    }
}