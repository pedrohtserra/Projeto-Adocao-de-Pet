package view;

import controller.FornecedorController;
import model.Fornecedor;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewFornecedor {
    public static void menu(Scanner scanner, FornecedorController fornecedorController) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n--- Gerenciamento de Fornecedores ---");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Listar Todos os Fornecedores");
            System.out.println("3. Buscar Fornecedor por CNPJ/CPF");
            System.out.println("4. Listar por Categoria de Produto");
            System.out.println("5. Atualizar ou Remover Fornecedor");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFornecedor(scanner, fornecedorController);
                    break;
                case 2:
                    listarTodos(fornecedorController);
                    break;
                case 3:
                    buscarFornecedor(scanner, fornecedorController);
                    break;
                case 4:
                    listarPorCategoria(scanner, fornecedorController);
                    break;
                case 5:
                    atualizarOuRemover(scanner, fornecedorController);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarFornecedor(Scanner scanner, FornecedorController fornecedorController) {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();

        System.out.print("CNPJ ou CPF: ");
        String cpf = scanner.nextLine();

        if (fornecedorController.buscarPorCpf(cpf) != null) {
            System.out.println("Já existe um fornecedor cadastrado com esse CNPJ/CPF.");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.println("Categoria principal de fornecimento:");
        System.out.println("  1. Ração");
        System.out.println("  2. Brinquedo");
        System.out.println("  3. Remédio");
        System.out.print("Escolha: ");
        int catOpcao = scanner.nextInt();
        scanner.nextLine();

        String categoria;
        switch (catOpcao) {
            case 1: categoria = "Ração"; break;
            case 2: categoria = "Brinquedo"; break;
            case 3: categoria = "Remédio"; break;
            default:
                System.out.println("Categoria inválida. Fornecedor não cadastrado.");
                return;
        }

        fornecedorController.cadastrar(new Fornecedor(nome, cpf, telefone, email, categoria));
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    private static void listarTodos(FornecedorController fornecedorController) {
        ArrayList<Fornecedor> lista = fornecedorController.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
            return;
        }
        System.out.println("\n--- Todos os Fornecedores ---");
        for (Fornecedor f : lista) {
            System.out.println(f);
        }
        System.out.println("Total: " + lista.size() + " fornecedor(es).");
    }

    private static void buscarFornecedor(Scanner scanner, FornecedorController fornecedorController) {
        System.out.print("Digite o CNPJ/CPF do fornecedor: ");
        String cpf = scanner.nextLine();

        Fornecedor f = fornecedorController.buscarPorCpf(cpf);
        if (f != null) {
            System.out.println("\nFornecedor encontrado:");
            System.out.println(f);
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private static void listarPorCategoria(Scanner scanner, FornecedorController fornecedorController) {
        System.out.println("Escolha a categoria:");
        System.out.println("  1. Ração");
        System.out.println("  2. Brinquedo");
        System.out.println("  3. Remédio");
        System.out.print("Escolha: ");
        int catOpcao = scanner.nextInt();
        scanner.nextLine();

        String categoria;
        switch (catOpcao) {
            case 1: categoria = "Ração"; break;
            case 2: categoria = "Brinquedo"; break;
            case 3: categoria = "Remédio"; break;
            default:
                System.out.println("Categoria inválida.");
                return;
        }

        ArrayList<Fornecedor> lista = fornecedorController.listarPorCategoria(categoria);
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado para a categoria: " + categoria);
            return;
        }
        System.out.println("\n--- Fornecedores de " + categoria + " ---");
        for (Fornecedor f : lista) {
            System.out.println(f);
        }
    }

    private static void atualizarOuRemover(Scanner scanner, FornecedorController fornecedorController) {
        System.out.print("Digite o CNPJ/CPF do fornecedor: ");
        String cpf = scanner.nextLine();

        Fornecedor f = fornecedorController.buscarPorCpf(cpf);
        if (f == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.println("Fornecedor encontrado: " + f.getNome());
        System.out.println("O que deseja fazer?");
        System.out.println("  1. Atualizar dados");
        System.out.println("  2. Remover fornecedor");
        System.out.print("Escolha: ");
        int acao = scanner.nextInt();
        scanner.nextLine();

        if (acao == 1) {
            System.out.print("Novo telefone (atual: " + f.getTelefone() + "): ");
            String telefone = scanner.nextLine();

            System.out.print("Novo e-mail (atual: " + f.getEmail() + "): ");
            String email = scanner.nextLine();

            System.out.println("Nova categoria principal (atual: " + f.getCategoriaPrincipal() + "):");
            System.out.println("  1. Ração  2. Brinquedo  3. Remédio");
            System.out.print("Escolha: ");
            int catOpcao = scanner.nextInt();
            scanner.nextLine();

            String categoria;
            switch (catOpcao) {
                case 1: categoria = "Ração"; break;
                case 2: categoria = "Brinquedo"; break;
                case 3: categoria = "Remédio"; break;
                default:
                    System.out.println("Categoria inválida. Atualização cancelada.");
                    return;
            }

            Fornecedor atualizado = new Fornecedor(f.getNome(), cpf, telefone, email, categoria);
            fornecedorController.atualizar(cpf, atualizado);
            System.out.println("Fornecedor atualizado com sucesso!");

        } else if (acao == 2) {
            System.out.print("Confirma a remoção de \"" + f.getNome() + "\"? (s/n): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("s")) {
                fornecedorController.deletar(cpf);
                System.out.println("Fornecedor removido com sucesso.");
            } else {
                System.out.println("Remoção cancelada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
}