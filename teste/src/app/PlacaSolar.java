package app;

public class PlacaSolar implements Equipamento {
    private String modelo;
    private double capacidade; // em kW
    private double preco; // preço em R$
    private Fabricante fabricante;

    public PlacaSolar(String modelo, double capacidade, double preco, Fabricante fabricante) {
        this.modelo = modelo;
        this.capacidade = capacidade;
        this.preco = preco;
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public double getPreco() {
        return preco;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Placa Solar: " + modelo);
        System.out.println("Capacidade: " + capacidade + " kW");
        System.out.println("Preço: R$ " + preco);
        System.out.println("Fabricante: " + fabricante.getNome());
    }

    @Override
    public String toString() {
        return "PlacaSolar{" +
                "modelo='" + modelo + '\'' +
                ", capacidade=" + capacidade +
                " kW, preço=" + preco +
                " R$, fabricante=" + fabricante.getNome() +
                '}';
    }
}
