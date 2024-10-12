package app;

import java.util.ArrayList;
import java.util.List;

class Cliente {

    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    private String estado;
     // Lista para armazenar solicitações

    // Construtor
    public Cliente(String nome, String endereco, String telefone, String cpf, String email, String estado) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.estado = estado;
    }

    // Métodos para acessar as informações
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }


    // Método para adicionar uma nova solicitação


    // Exibir informações do cliente
    public void exibirCliente() {
        System.out.println("=== Informações do Cliente ===");
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Estado: " + estado);
        System.out.println("Telefone: " + telefone);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);

    }
}
