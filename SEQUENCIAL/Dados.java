
public class Dados {
    public double suceptivel = 900000.0;
    public double infectados = 100000.0;
    public double recuperados = 0.0;
    public double populacaoTotal = Math.max(suceptivel + infectados + recuperados, 0.0);
    public double beta = 0.3;   // Taxa de transmissão
    public double gamma = 0.1;  // Taxa de recuperação
    public int dias = 365; // Número de dias para simulação
    public double passo = 0.1;
}
