import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SISImpl extends UnicastRemoteObject implements SIS {
    
    protected SISImpl() throws RemoteException {
        super();
    }


    public String CalcularSIS(double S, double I, double N, double beta, double gamma, int dias, double passo) throws RemoteException {
        // Cabeçalho do resultado
        String resultado = "Dia\tSusceptiveis\tInfectados\n";

        // Valores atuais (podem ser fracionários, mas usados como contagens para loops)
        double S_t = S;
        double I_t = I;

        for(int dia = 0; dia < dias; dia++){

            // Probabilidade de um indivíduo suscetível ser infectado
            double chanceInfecacao = (beta * I)/ N * passo;
            // Probabilidade de um infectado recuperar-se
            double chanceRecuperacao = gamma * passo;

            // Atualização dos novos infectados hoje
            int novos = 0;
            for(int X = 0; X < S_t; X++){
                if(Math.random() < chanceInfecacao){
                    novos++;
                }
            }

            // Conta quantos infectados se recuperam hoje
            int recuperadosHoje = 0;
            for(int Y = 0; Y < I_t; Y++){
                if(Math.random() < chanceRecuperacao){
                    recuperadosHoje++;
                }
            }

            // Atualiza os estados a partir dos eventos calculados
            I_t += novos - recuperadosHoje;
            S_t += recuperadosHoje - novos;

            // Acumula a linha de saída com valores do dia
            resultado += String.format("%d\t%.2f\t%.2f\t\n", (dia+1), S_t, I_t);

        }

        return resultado;

    }

}
