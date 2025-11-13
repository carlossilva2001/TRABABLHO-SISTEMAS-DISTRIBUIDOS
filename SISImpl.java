import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SISImpl extends UnicastRemoteObject implements SIS {
    
    protected SISImpl() throws RemoteException {
        super();
    }

    public String CalcularSIS(double S, double I, double N, double beta, double gamma, int dias, double passo) throws RemoteException {
        String resultado = "Dia\tSusceptiveis\tInfectados\n";
        double S_t = S;
        double I_t = I;

        for(int dia = 0; dia < dias; dia++){

            //Probabilidade dos acontecimentos
            double chanceInfecacao = (beta * I)/ N * passo;
            double chanceRecuperacao = gamma * passo;

            //Atualização dos valores
            int novos = 0;
            for(int X = 0; X < S_t; X++){
                if(Math.random() < chanceInfecacao){
                    novos++;
                }
            }
            int recuperadosHoje = 0;
            for(int Y = 0; Y < I_t; Y++){
                if(Math.random() < chanceRecuperacao){
                    recuperadosHoje++;
                }
            }

            I_t += novos - recuperadosHoje;
            S_t += recuperadosHoje - novos;

            resultado += String.format("%d\t%.2f\t%.2f\t\n", (dia+1), S_t, I_t);

        }

        return resultado;

    }

}
