package app;
import com.google.gson.GsonBuilder; // Adicione esta linha

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList; // Importando ArrayList para usar listas mutáveis
import java.util.List;
import java.util.Scanner;

public class MenuCliente {
    private Cliente cliente; // Cliente logado
    private Scanner scanner;
    private static final String SOLICITACOES_FILE = "solicitacoes.json"; // Caminho do arquivo JSON

    public MenuCliente(Cliente cliente) {
        this.cliente = cliente;
        this.scanner = new Scanner(System.in);
        carregarSolicitacoes(); // Carregar solicitações ao inicializar o menu
    }

    public void exibirMenu() {
        int option;

        do {
            System.out.println("===== Menu do Cliente =====");
            System.out.println("1. Exibir Informações do Cliente");
            System.out.println("2. Exibir Equipamentos");
            System.out.println("3. Solicitar Projeto");
            System.out.println("4. Exibir Status de Solicitações");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    exibirInformacoes();
                    break;
                case 2:
                    exibirEquipamentos();
                    break;
                case 3:
                    solicitarProjeto();
                    break;
                case 4:
                    exibirStatusSolicitacoes();
                    break;
                case 0:
                    System.out.println("Saindo do menu do cliente...");
                    Main2.main(new String[0]); // Certifique-se de que este método existe
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 0);
    }

    private void exibirInformacoes() {
        System.out.println("\n=== Informações do Cliente ===");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Estado: " + cliente.getEstado());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("CPF: " + cliente.getCpf());
    }

    private void exibirEquipamentos() {
        System.out.println("\n=== Equipamentos Disponíveis ===");

        // Exibir placas solares
        for (PlacaSolar placa : Main2.placasSolares) {
            System.out.println("Placa Solar: " + placa.getModelo() +
                    " - Capacidade: " + placa.getCapacidade() + " kW" +
                    " - Fabricante: \"" + placa.getFabricante().getNome() + "\"");
        }

        // Exibir inversores
        for (Inversor inversor : Main2.inversores) {
            System.out.println("Inversor: " + inversor.getModelo() +
                    " - Potência: " + inversor.getPotencia() + " kW" +
                    " - Fabricante: \"" + inversor.getFabricante().getNome() + "\"");
        }
    }



    private void solicitarProjeto() {
        System.out.println("\n=== Solicitar Projeto ===");
        double consumoMedio = -1;

        // Validar entrada para o consumo médio
        while (consumoMedio < 0) {
            System.out.print("Informe seu consumo médio de energia (em kWh): ");
            if (scanner.hasNextDouble()) {
                consumoMedio = scanner.nextDouble();
                if (consumoMedio < 0) {
                    System.out.println("O consumo médio deve ser um valor positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            }
        }

        // Verificar se a solicitação já existe
        for (SolicitacaoProjeto solicitacao : Main2.solicitacoes) {
            if (solicitacao.getCliente().equals(cliente) &&
                    solicitacao.getConsumoMedio() == consumoMedio) {
                System.out.println("Uma solicitação semelhante já foi registrada.");
                return; // Sai do método se uma duplicata for encontrada
            }
        }

        // Criar uma nova solicitação de projeto, incluindo estado e email
        SolicitacaoProjeto solicitacao = new SolicitacaoProjeto(cliente, consumoMedio, cliente.getEstado(), cliente.getEmail());
        Main2.solicitacoes.add(solicitacao);
        System.out.println("Solicitação registrada com sucesso!");
        solicitacao.exibirSolicitacao();

        // Salvar as solicitações após a adição
        salvarSolicitacoes();
    }


    private void exibirStatusSolicitacoes() {
        System.out.println("\n=== Status de Solicitações ===");
        boolean temSolicitacoes = false;

        // Iterar sobre as solicitações e exibir apenas as do cliente logado
        for (SolicitacaoProjeto solicitacao : Main2.solicitacoes) {
            // Verifica se o CPF da solicitação corresponde ao CPF do cliente logado
            if (solicitacao.getCliente().getCpf().equals(cliente.getCpf())) {
                System.out.println("Solicitação: " + solicitacao.getDescricao());
                System.out.println("Status: " + solicitacao.getStatus());
                temSolicitacoes = true;
            }
        }

        if (!temSolicitacoes) {
            System.out.println("Nenhuma solicitação encontrada para o cliente.");
        }
    }


    private void salvarSolicitacoes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SOLICITACOES_FILE))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Cria o Gson com formatação
            gson.toJson(Main2.solicitacoes, writer);
            System.out.println("Solicitações salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar solicitações: " + e.getMessage());
        }
    }


    private void carregarSolicitacoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SOLICITACOES_FILE))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<SolicitacaoProjeto>>() {}.getType(); // Usando ArrayList

            // Verifica se o arquivo está vazio
            if (reader.readLine() == null) {
                System.out.println("O arquivo de solicitações está vazio.");
                Main2.solicitacoes = new ArrayList<>(); // Inicializa com uma lista vazia
                return; // Sai do método se o arquivo estiver vazio
            }

            // Reseta o BufferedReader para o início do arquivo
            reader.close(); // Fecha o reader atual
            // Reabre para leitura
            try (BufferedReader newReader = new BufferedReader(new FileReader(SOLICITACOES_FILE))) {
                Main2.solicitacoes = gson.fromJson(newReader, listType);
                System.out.println("Solicitações carregadas com sucesso!");

                // Exibir solicitações que correspondem ao CPF do cliente logado
                System.out.println("\n=== Solicitações para o CPF: " + cliente.getCpf() + " ===");
                boolean encontrouSolicitacoes = false; // Para verificar se encontrou alguma solicitação
                for (SolicitacaoProjeto solicitacao : Main2.solicitacoes) {
                    if (solicitacao.getCliente().getCpf().equals(cliente.getCpf())) {
                        solicitacao.exibirSolicitacao(); // Exibir detalhes da solicitação
                        encontrouSolicitacoes = true; // Marca que encontrou pelo menos uma solicitação
                    }
                }

                if (!encontrouSolicitacoes) {
                    System.out.println("Nenhuma solicitação encontrada para este CPF.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar solicitações: " + e.getMessage());
            // Se o arquivo não existe, cria um novo arquivo vazio
            if (e.getMessage().contains("no such file or directory")) {
                try {
                    new FileWriter(SOLICITACOES_FILE).close(); // Cria um arquivo vazio
                    System.out.println("Arquivo de solicitações criado: " + SOLICITACOES_FILE);
                    Main2.solicitacoes = new ArrayList<>(); // Inicializa a lista de solicitações
                } catch (IOException ex) {
                    System.err.println("Erro ao criar arquivo de solicitações: " + ex.getMessage());
                }
            } else {
                // Inicializa a lista de solicitações como vazia em caso de erro de leitura
                Main2.solicitacoes = new ArrayList<>();
            }
        }
    }


}
