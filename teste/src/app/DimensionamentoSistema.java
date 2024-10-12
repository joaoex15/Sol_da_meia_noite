package app;

import java.util.List;

public class DimensionamentoSistema {

    // Método para dimensionar o sistema com base no consumo mensal e na irradiação solar
    public static void dimensionarSistema(double consumoMensal, double irradacaoSolar, List<PlacaSolar> placas, List<Inversor> inversores) {
        double energiaNecessaria = consumoMensal; // em kWh
        double energiaDiariaNecessaria = energiaNecessaria / 30; // em kWh por dia

        // Calcular a energia gerada por cada painel solar
        for (PlacaSolar placa : placas) {
            double energiaDiariaPorPainel = placa.getCapacidade() * irradacaoSolar; // energia gerada por painel em kWh
            // Calcular o número de painéis solares necessários
            int numPainel = (int) Math.ceil(energiaDiariaNecessaria / energiaDiariaPorPainel);
            System.out.println("Número de painéis solares necessários para " + placa.getModelo() + ": " + numPainel);
        }

        // Calcular o número de inversores necessários
        for (Inversor inversor : inversores) {
            double potenciaTotalPainel = 0; // potência total dos painéis em W
            for (PlacaSolar placa : placas) {
                potenciaTotalPainel += placa.getCapacidade() * 1000; // convertendo kW para W
            }
            // Calcular o número de inversores necessários com base na potência total dos painéis
            int numInversores = (int) Math.ceil(potenciaTotalPainel / inversor.getPotencia()); // em W
            System.out.println("Número de inversores necessários para " + inversor.getModelo() + ": " + numInversores);
        }
    }


    // Método para calcular o orçamento total
    public static double calcularOrcamento(List<PlacaSolar> placas, List<Inversor> inversores, double margemLucro, double maoDeObra) {
        double custoTotal = 0;

        // Calcular o custo total dos painéis solares
        for (PlacaSolar placa : placas) {
            custoTotal += placa.getPreco();
        }

        // Calcular o custo total dos inversores
        for (Inversor inversor : inversores) {
            custoTotal += inversor.getPreco();
        }

        // Adicionar a margem de lucro
        custoTotal += custoTotal * (margemLucro / 100);

        // Adicionar o custo de mão de obra
        custoTotal += maoDeObra;

        return custoTotal;
    }
}
