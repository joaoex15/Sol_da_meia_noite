package app;

public class SolicitacaoProjeto {
    private Cliente cliente;
    private double consumoMedio;
    private String status;
    private String estado;
    private String email; // Corrigido - Adicionada vírgula e campo email

    public SolicitacaoProjeto(Cliente cliente, double consumoMedio, String estado, String email) {
        this.cliente = cliente;
        this.consumoMedio = consumoMedio;
        this.estado = estado; // Estado do cliente
        this.email = email;   // Email do cliente
        this.status = "Pendente"; // Status inicial da solicitação
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    public String getStatus() {
        return status; // Getter para o status
    }

    public void setStatus(String status) {
        this.status = status; // Setter para atualizar o status
    }

    public String getEstado() {
        return estado; // Getter para o estado do cliente
    }

    public String getEmail() {
        return email; // Getter para o email do cliente
    }

    public String getDescricao() {
        return "Consumo médio de energia: " + consumoMedio + " kWh | Status: " + status;
    }

    public void exibirSolicitacao() {
        System.out.println("Solicitação de Projeto:");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Consumo Médio de Energia: " + consumoMedio + " kWh");
        System.out.println("Estado: " + estado);
        System.out.println("Email: " + email);
        System.out.println("Status: " + status); // Exibe o status
    }
}
