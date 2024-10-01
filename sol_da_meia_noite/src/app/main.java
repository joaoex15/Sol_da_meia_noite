package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Cadastro de Clientes");
            System.out.println("2. Cadastro de Equipamentos");
            System.out.println("3. Solicitação de Projetos");
            System.out.println("4. Dimensionamento do Sistema Fotovoltaico");
            System.out.println("5. Controle de Orçamentos");
            System.out.println("6. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    // Chamar método para cadastro de clientes
                    cadastrarClientes(scanner);
                    break;
                case 2:
                    // Chamar método para cadastro de equipamentos
                    cadastrarEquipamentos(scanner);
                    break;
                case 3:
                    // Chamar método para solicitação de projetos
                    solicitarProjetos(scanner);
                    break;
                case 4:
                    // Chamar método para dimensionamento
                    dimensionarSistema(scanner);
                    break;
                case 5:
                    // Chamar método para controle de orçamentos
                    controlarOrcamentos(scanner);
                    break;
                case 6:
                    // Chamar método para geração de relatórios
                    gerarRelatorios(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void cadastrarClientes(Scanner scanner) {
        // Implementar a lógica de cadastro de clientes
        System.out.println("Cadastro de Clientes");
        // Lógica para adicionar cliente
    }

    private static void cadastrarEquipamentos(Scanner scanner) {
        // Implementar a lógica de cadastro de equipamentos
        System.out.println("Cadastro de Equipamentos");
        // Lógica para adicionar equipamentos
    }

    private static void solicitarProjetos(Scanner scanner) {
        // Implementar a lógica de solicitação de projetos
        System.out.println("Solicitação de Projetos");
        // Lógica para solicitar projeto
    }

    private static void dimensionarSistema(Scanner scanner) {
        // Implementar a lógica de dimensionamento do sistema
        System.out.println("Dimensionamento do Sistema Fotovoltaico");
        // Lógica para dimensionar sistema
    }

    private static void controlarOrcamentos(Scanner scanner) {
        // Implementar a lógica de controle de orçamentos
        System.out.println("Controle de Orçamentos");
        // Lógica para controlar orçamentos
    }

    private static void gerarRelatorios(Scanner scanner) {
        // Implementar a lógica para geração de relatórios
        System.out.println("Geração de Relatórios");
        // Lógica para gerar relatórios
    }
}
