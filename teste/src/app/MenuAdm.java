package app;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject; // Import necessário para JsonObject
import com.google.gson.JsonParser; // Import necessário para JsonParser
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Type;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static app.Main2.gson;

public class MenuAdm {
    private Scanner scanner;

    public MenuAdm() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int option;

        do {
            System.out.println("===== Menu do Administrador =====");
            System.out.println("1. Visualizar Notificações de Solicitação de Projeto");
            System.out.println("2. Dimensionamento do Sistema Fotovoltaico");
            System.out.println("3. Controle de Orçamentos");
            System.out.println("4. Relatórios");
            System.out.println("5. Cadastro de Equipamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    notificacoesSolicitacao();
                    break;
                case 2:
                    dimensionamentoSistema();
                    break;
                case 3:
                    controleOrcamentos();
                    break;
                case 4:
                    relatorios();
                    break;
                case 5:
                    cadastrarEquipamentos(scanner);
                    break;
                case 0:
                    salvarDados();
                    Main2.main(new String[0]); // Certifique-se de que este método existe

                    System.out.println("Saindo do menu do administrador...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 0);

        scanner.close();
    }

    private void notificacoesSolicitacao() {
        System.out.println("\n=== Notificações de Solicitação de Projeto Pendentes ===");
        List<SolicitacaoProjeto> pendentes = new ArrayList<>();
        for (SolicitacaoProjeto solicitacao : Main2.solicitacoes) {
            if (solicitacao.getStatus().equals("Pendente")) {
                solicitacao.exibirSolicitacao();
                System.out.println("-------------------------");
                pendentes.add(solicitacao);
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma solicitação pendente.");
        }
    }

    private static void cadastrarEquipamentos(Scanner scanner) {
        int option;
        do {
            System.out.println("\n=== Cadastro de Equipamentos ===");
            System.out.println("1. Cadastrar Fabricante");
            System.out.println("2. Cadastrar Placa Solar");
            System.out.println("3. Cadastrar Inversor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    cadastrarFabricante(scanner);
                    break;
                case 2:
                    cadastrarPlacaSolar(scanner);
                    break;
                case 3:
                    cadastrarInversor(scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private void dimensionamentoSistema() {
        System.out.println("=== Solicitações Pendentes ===");
        List<SolicitacaoProjeto> pendentes = new ArrayList<>();

        for (int i = 0; i < Main2.solicitacoes.size(); i++) {
            SolicitacaoProjeto solicitacao = Main2.solicitacoes.get(i);
            if (solicitacao.getStatus().equals("Pendente")) {
                System.out.println(i + ". " + solicitacao.getCliente().getNome()
                        + " - Consumo médio: " + solicitacao.getConsumoMedio()
                        + " kWh - Email: " + solicitacao.getCliente().getEmail()
                        + " - Estado: " + solicitacao.getCliente().getEstado());
                pendentes.add(solicitacao);
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma solicitação pendente.");
            return;
        }

        System.out.print("Escolha uma solicitação para dimensionamento (digite o índice): ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (indice >= 0 && indice < pendentes.size()) {
            SolicitacaoProjeto solicitacao = pendentes.get(indice);
            double consumoMensal = solicitacao.getConsumoMedio();
            System.out.println("Dimensionando o sistema para o cliente: " + solicitacao.getCliente().getNome());
            System.out.println("Consumo mensal informado na solicitação: " + consumoMensal + " kWh");

            String estadoCliente = solicitacao.getCliente().getEstado();
            double irradiacaoSolar = obterIrradiacaoPorEstado(estadoCliente);
            System.out.println("Irradiância solar média para o estado " + estadoCliente + ": " + irradiacaoSolar + " kWh/m²/dia");

            DimensionamentoSistema.dimensionarSistema(consumoMensal, irradiacaoSolar, Main2.placasSolares, Main2.inversores);

            System.out.print("Informe a margem de lucro (%): ");
            double margemLucro = scanner.nextDouble();
            System.out.print("Informe o custo da mão de obra: ");
            double maoDeObra = scanner.nextDouble();

            double custoTotal = DimensionamentoSistema.calcularOrcamento(Main2.placasSolares, Main2.inversores, margemLucro, maoDeObra);
            System.out.println("Orçamento total estimado: R$ " + custoTotal);

            solicitacao.setStatus("Em andamento");
            System.out.println("Status atualizado para: " + solicitacao.getStatus());
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private double obterIrradiacaoPorEstado(String estado) {
        switch (estado) {
            case "Acre/AC":
                return 33902;
            case "Alagoas/AL":
                return 33653;
            case "Amapá/AP":
                return 67144;
            case "Amazonas/AM":
                return 59297;
            case "Bahia/BA":
                return 25494;
            case "Ceará/CE":
                return 57652;
            case "Distrito Federal/DF":
                return 19243;
            case "Espírito Santo/ES":
                return 10781;
            case "Goiás/GO":
                return 17366;
            case "Maranhão/MA":
                return 61200;
            case "Mato Grosso/MT":
                return 19591;
            case "Mato Grosso do Sul/MS":
                return 10458;
            case "Minas Gerais/MG":
                return 11465;
            case "Pará/PA":
                return 21381;
            case "Paraíba/PB":
                return 15559;
            case "Paraná/PR":
                return 11258;
            case "Pernambuco/PE":
                return 16964;
            case "Piauí/PI":
                return 42832;
            case "Rio de Janeiro/RJ":
                return 16725;
            case "Rio Grande do Norte/RN":
                return 15776;
            case "Rio Grande do Sul/RS":
                return 12429;
            case "Rondônia/RO":
                return 67838;
            case "Roraima/RR":
                return 19533;
            case "São Paulo/SP":
                return 15629;
            case "Sergipe/SE":
                return 24396;
            case "Tocantins/TO":
                return 14553;
            default:
                return 0.0;
        }
    }

    private void controleOrcamentos() {
        System.out.println("=== Controle de Orçamentos ===");
        // Implementar lógica para controle de orçamentos
    }

    private void relatorios() {
        System.out.println("=== Relatórios ===");
        // Implementar lógica para relatórios
    }



    private static void cadastrarFabricante(Scanner scanner) {
        System.out.print("Nome do fabricante: ");
        String nomeFabricante = scanner.nextLine();
        Fabricante fabricante = new Fabricante(nomeFabricante);
        Main2.fabricantes.add(fabricante);
        System.out.println("Fabricante cadastrado com sucesso.");
    }

    private static void cadastrarPlacaSolar(Scanner scanner) {
        System.out.print("Modelo da placa solar: ");
        String modelo = scanner.nextLine();

        System.out.print("Capacidade da placa solar (em kW): ");
        double capacidade = scanner.nextDouble();

        System.out.print("Preço da placa solar: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.println("Escolha o fabricante pelo número:");
        for (int i = 0; i < Main2.fabricantes.size(); i++) {
            System.out.println((i + 1) + ". " + Main2.fabricantes.get(i).getNome());
        }

        int fabricanteIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (fabricanteIndex < 0 || fabricanteIndex >= Main2.fabricantes.size()) {
            System.out.println("Índice de fabricante inválido.");
            return;
        }

        Fabricante fabricante = Main2.fabricantes.get(fabricanteIndex);
        PlacaSolar placa = new PlacaSolar(modelo, capacidade, preco, fabricante);
        Main2.placasSolares.add(placa);
        System.out.println("Placa solar cadastrada com sucesso.");
    }

    private static void cadastrarInversor(Scanner scanner) {
        System.out.print("Modelo do inversor: ");
        String modelo = scanner.nextLine();

        System.out.print("Potência do inversor (em kW): ");
        double potencia = scanner.nextDouble();

        System.out.print("Preço do inversor: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.println("Escolha o fabricante pelo número:");
        for (int i = 0; i < Main2.fabricantes.size(); i++) {
            System.out.println((i + 1) + ". " + Main2.fabricantes.get(i).getNome());
        }

        int fabricanteIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (fabricanteIndex < 0 || fabricanteIndex >= Main2.fabricantes.size()) {
            System.out.println("Índice de fabricante inválido.");
            return;
        }

        Fabricante fabricante = Main2.fabricantes.get(fabricanteIndex);
        Inversor inversor = new Inversor(modelo, potencia, preco, fabricante);
        Main2.inversores.add(inversor);
        System.out.println("Inversor cadastrado com sucesso.");
    }















    private void salvarDados() {
        JsonObject dados = new JsonObject();
        dados.add("fabricantes", gson.toJsonTree(Main2.fabricantes));
        dados.add("placasSolares", gson.toJsonTree(Main2.placasSolares));
        dados.add("inversores", gson.toJsonTree(Main2.inversores));

        try (FileWriter writer = new FileWriter("dados.json")) {
            gson.toJson(dados, writer);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
