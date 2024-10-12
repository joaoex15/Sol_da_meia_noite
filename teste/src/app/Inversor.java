package app;

public class Inversor implements Equipamento {
    private String modelo;
    private double potencia; // em kW
    private double preco; // preço em R$
    private Fabricante fabricante;

    public Inversor(String modelo, double potencia, double preco, Fabricante fabricante) {
        this.modelo = modelo;
        this.potencia = potencia;
        this.preco = preco;
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPotencia() {
        return potencia;
    }

    public double getPreco() {
        return preco;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Inversor: " + modelo);
        System.out.println("Potência: " + potencia + " kW");
        System.out.println("Preço: R$ " + preco);
        System.out.println("Fabricante: " + fabricante.getNome());
    }

    @Override
    public String toString() {
        return "Inversor{" +
                "modelo='" + modelo + '\'' +
                ", potencia=" + potencia +
                " kW, preço=" + preco +
                " R$, fabricante=" + fabricante.getNome() +
                '}';
    }
}

