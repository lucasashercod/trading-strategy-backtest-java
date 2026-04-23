public class Main {
    public static void main(String[] args) {

        String estrategia = "Scalping Simples";
        double capitalInicial = 1000.0;

        System.out.println("Estratégia: " + estrategia);
        System.out.println("Capital inicial: " + capitalInicial);

        // Ativo operado
        String ativo = "WIN";

        // Definição do valor por ponto
        double valorPorPonto;

        if (ativo.equals("WIN")) {
            valorPorPonto = 0.20;
        } else if (ativo.equals("WDO")) {
            valorPorPonto = 10.00;
        } else {
            System.out.println("Erro: ativo inválido!");
            return;
        }

        // =========================
        // PARÂMETROS DO DIA
        // =========================
        double maxima = 120150;
        double minima = 120000;
        double precoJusto = 120080;

        // =========================
        // SIMULAÇÃO DE PREÇOS
        // =========================
        double[] precos = {120000, 120050, 120100, 120080, 120150};

        // Controle de posição
        boolean comprado = false;
        double precoEntrada = 0;

        // =========================
        // MÉTRICAS
        // =========================
        double lucroTotal = 0;
        int totalTrades = 0;
        int tradesVencedores = 0;
        int tradesPerdedores = 0;

        // =========================
        // LOOP (MERCADO RODANDO)
        // =========================
        for (int i = 0; i < precos.length; i++) {
            double precoAtual = precos[i];

            System.out.println("Preço atual: " + precoAtual);

            // COMPRA: região da mínima + abaixo do preço justo
            if (!comprado && precoAtual <= minima + 20 && precoAtual < precoJusto) {
                comprado = true;
                precoEntrada = precoAtual;
                System.out.println(">>> COMPROU (abaixo do justo) em: " + precoEntrada);
            }

            // VENDA: região da máxima + acima do preço justo
            if (comprado && precoAtual >= maxima - 20 && precoAtual > precoJusto) {
                double precoSaida = precoAtual;
                double pontos = precoSaida - precoEntrada;
                double lucro = pontos * valorPorPonto;

                System.out.println(">>> VENDEU (acima do justo) em: " + precoSaida);
                System.out.println(">>> LUCRO DO TRADE: R$ " + lucro);

                // Classificação do trade
                if (lucro > 0) {
                    tradesVencedores++;
                } else {
                    tradesPerdedores++;
                }

                // Atualização geral
                lucroTotal += lucro;
                totalTrades++;
                capitalInicial += lucro;

                comprado = false;
            }
        }

        // =========================
        // RESULTADO FINAL
        // =========================
        double winRate = 0;

        if (totalTrades > 0) {
            winRate = (double) tradesVencedores / totalTrades * 100;
        }

        System.out.println("================================");
        System.out.println("RESULTADO FINAL");
        System.out.println("Lucro total: R$ " + lucroTotal);
        System.out.println("Total de trades: " + totalTrades);
        System.out.println("Capital final: R$ " + capitalInicial);
        System.out.println("Trades vencedores: " + tradesVencedores);
        System.out.println("Trades perdedores: " + tradesPerdedores);
        System.out.println("Win Rate: " + winRate + "%");

    }
}