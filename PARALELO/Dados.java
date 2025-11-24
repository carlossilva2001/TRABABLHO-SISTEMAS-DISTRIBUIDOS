/**
 * Classe simples para agrupar os parâmetros e estados iniciais da simulação.
 *
 * Observação: todos os campos são públicos para facilitar acesso direto pelos
 * programas de simulação (ex.: SIR_Paralelo, SIS_Paralelo). Em projetos maiores
 * recomenda-se encapsular estes campos e fornecer getters/setters.
 */
public class Dados {
    // Número inicial de indivíduos suscetíveis
    public double suceptivel = 900000.0;

    // Número inicial de indivíduos infectados
    public double infectados = 100000.0;

    // Número inicial de indivíduos recuperados (usado no modelo SIR)
    public double recuperados = 0.0;

    // População total — recomputada com Math.max para evitar valores negativos
    public double populacaoTotal = Math.max(suceptivel + infectados + recuperados, 0.0);

    // Taxa de transmissão (beta) — controla a velocidade de novo contágio
    public double beta = 0.3;   // Taxa de transmissão

    // Taxa de recuperação (gamma) — controla a velocidade de recuperação
    public double gamma = 0.1;  // Taxa de recuperação

    // Número de passos (dias) a simular
    public int dias = 365; // Número de dias para simulação

    // Tamanho do passo temporal (Euler explícito ou probabilidade por passo)
    public double passo = 0.1;
}
