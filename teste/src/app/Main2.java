package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main2 {
    private static Map<String, Cliente> clientes = new HashMap<>();
    public static List<Fabricante> fabricantes = new ArrayList<>();
    public static List<PlacaSolar> placasSolares = new ArrayList<>();
    public static List<Inversor> inversores = new ArrayList<>();
    public static List<SolicitacaoProjeto> solicitacoes = new ArrayList<>();
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        carregarClientesDeArquivo("clientes.json");
        Main2.carregarDados();

        int option;

        do {
            try {
                exibirMenuPrincipal();
                option = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (option) {
                    case 1:
                        loginCliente();
                        break;
                    case 2:
                        cadastrarCliente();
                        break;
                    case 3:
                        loginAdministrador();
                        break;
                    case 0:
                        salvarClientesEmArquivo("clientes.json");
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Digite apenas o número correspondente à sua solicitação.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        } while (true);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("===== Tela de Login =====");
        System.out.println("1. Login como Cliente");
        System.out.println("2. Cadastro de Cliente");
        System.out.println("3. Login como Administrador");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void loginCliente() {
        System.out.print("Informe seu CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = encontrarClientePorCpf(cpf);

        if (cliente != null) {
            System.out.println("Login de Cliente realizado com sucesso!");
            MenuCliente menuCliente = new MenuCliente(cliente);
            menuCliente.exibirMenu();
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente encontrarClientePorCpf(String cpf) {
        return clientes.get(cpf);
    }

    private static void cadastrarCliente() {
        System.out.println("\n=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        String estado = escolherEstado();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (clientes.containsKey(cpf)) {
            System.out.println("CPF já cadastrado. Tente novamente.");
        } else {
            Cliente novoCliente = new Cliente(nome, endereco, telefone, cpf, email, estado);
            clientes.put(cpf, novoCliente);
            System.out.println("Cliente cadastrado com sucesso: " + nome);
        }
    }

    private static String escolherEstado() {
        System.out.println("Escolha seu estado:");
        System.out.println("1-Acre/AC              || 2-Alagoas/AL            || 3-Amapá/AP");
        System.out.println("4-Amazonas/AM          || 5-Bahia/BA              || 6-Ceará/CE");
        System.out.println("7-Distrito Federal/DF  || 8-Espírito Santo/ES     || 9-Goiás/GO");
        System.out.println("10-Maranhão/MA         || 11-Mato Grosso/MT       || 12-Mato Grosso do Sul/MS");
        System.out.println("13-Minas Gerais/MG     || 14-Pará/PA              || 15-Paraíba/PB");
        System.out.println("16-Paraná/PR           || 17-Pernambuco/PE        || 18-Piauí/PI");
        System.out.println("19-Rio de Janeiro/RJ   || 20-Rio Grande do Norte/RN || 21-Rio Grande do Sul/RS");
        System.out.println("22-Rondônia/RO         || 23-Roraima/RR           || 24-Santa Catarina/SC");
        System.out.println("25-São Paulo/SP        || 26-Sergipe/SE           || 27-Tocantins/TO");
        System.out.print("Escolha seu estado digitando o número correspondente: ");

        int opcaoEstado = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (opcaoEstado) {
            case 1: return "Acre/AC";
            case 2: return "Alagoas/AL";
            case 3: return "Amapá/AP";
            case 4: return "Amazonas/AM";
            case 5: return "Bahia/BA";
            case 6: return "Ceará/CE";
            case 7: return "Distrito Federal/DF";
            case 8: return "Espírito Santo/ES";
            case 9: return "Goiás/GO";
            case 10: return "Maranhão/MA";
            case 11: return "Mato Grosso/MT";
            case 12: return "Mato Grosso do Sul/MS";
            case 13: return "Minas Gerais/MG";
            case 14: return "Pará/PA";
            case 15: return "Paraíba/PB";
            case 16: return "Paraná/PR";
            case 17: return "Pernambuco/PE";
            case 18: return "Piauí/PI";
            case 19: return "Rio de Janeiro/RJ";
            case 20: return "Rio Grande do Norte/RN";
            case 21: return "Rio Grande do Sul/RS";
            case 22: return "Rondônia/RO";
            case 23: return "Roraima/RR";
            case 24: return "Santa Catarina/SC";
            case 25: return "São Paulo/SP";
            case 26: return "Sergipe/SE";
            case 27: return "Tocantins/TO";
            default:
                System.out.println("Opção inválida.");
                return escolherEstado();
        }
    }

    private static void loginAdministrador() {
        System.out.print("Informe seu nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Informe sua senha: ");
        String senha = scanner.nextLine();

        if ("adm".equals(usuario) && "adm".equals(senha)) {
            System.out.println("Login de Administrador realizado com sucesso!");
            MenuAdm menuAdm = new MenuAdm();
            menuAdm.exibirMenu();
        } else {
            System.out.println("Usuário ou senha inválidos. Tente novamente.");
        }
    }

    private static void salvarClientesEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            String json = gson.toJson(clientes.values());
            writer.write(json);
            System.out.println("Dados dos clientes e suas solicitações salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void carregarClientesDeArquivo(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                Type tipo = new TypeToken<Collection<Cliente>>() {}.getType();
                Collection<Cliente> listaClientes = gson.fromJson(reader, tipo);

                // Verifique se listaClientes é null ou vazia
                if (listaClientes != null) {
                    for (Cliente cliente : listaClientes) {
                        clientes.put(cliente.getCpf(), cliente);
                    }
                    System.out.println("Dados dos clientes carregados com sucesso.");
                } else {
                    System.out.println("Arquivo está vazio ou inválido. Nenhum cliente foi carregado.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo de clientes não encontrado. Um novo será criado ao sair.");
        }
    }

    public static void carregarDados() {
        try (FileReader reader = new FileReader("dados.json")) {
            JsonObject dados = JsonParser.parseReader(reader).getAsJsonObject();
            Type tipoFabricante = new TypeToken<List<Fabricante>>() {}.getType();
            Type tipoPlacaSolar = new TypeToken<List<PlacaSolar>>() {}.getType();
            Type tipoInversor = new TypeToken<List<Inversor>>() {}.getType();

            Main2.fabricantes = gson.fromJson(dados.get("fabricantes"), tipoFabricante);
            Main2.placasSolares = gson.fromJson(dados.get("placasSolares"), tipoPlacaSolar);
            Main2.inversores = gson.fromJson(dados.get("inversores"), tipoInversor);

            System.out.println("Dados carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }



}

