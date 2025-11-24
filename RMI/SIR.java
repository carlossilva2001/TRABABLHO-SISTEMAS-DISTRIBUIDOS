import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SIR extends Remote{

    String CalcularSIR(double S, double I, double R, double N, double beta, double gamma, int dias, double passo) throws RemoteException;
    
}
