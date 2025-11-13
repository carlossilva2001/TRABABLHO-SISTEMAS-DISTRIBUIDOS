import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SIS extends Remote {

    String CalcularSIS(double S, double I, double N, double beta, double gama, int dias, double passo) throws RemoteException;
    
}
