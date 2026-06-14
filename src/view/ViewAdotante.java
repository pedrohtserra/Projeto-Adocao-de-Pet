package view;

import controller.AdotanteController;
import controller.PetController;
import model.Adotante;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewAdotante {
    public static void menu(Scanner scanner, PetController petController, AdotanteController adotanteController) {
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n--- Área do Adotante ---");
            System.out.println("1. Cadastrar Adotante");
            System.out.println("2. Listar Adotantes");
            System.out.println("3. Buscar por CPF");
            System.out.println("4. Remover Adotante");
            System.out.println("5. Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Opção inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrar(scanner, adotanteController);
                    break;
                case 2:
                    listar(adotanteController);
                    break;
                case 3:
                    buscarPorCpf(scanner, adotanteController);
                    break;
                case 4:
                    remover(scanner, adotanteController);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrar(Scanner scanner, AdotanteController adotanteController) {
        System.out.println("\n--- CADASTRAR ADOTANTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (adotanteController.buscarPorCpf(cpf) != null) {
            System.out.println("Já existe um adotante cadastrado com esse CPF.");
            return;
        }

        System.out.print("Telefone: ");
        String tel = scanner.nextLine();

        adotanteController.cadastrar(new Adotante(nome, cpf, tel));
        System.out.println("Adotante cadastrado com sucesso!");
    }

    private static void listar(AdotanteController adotanteController) {
        System.out.println("\n--- LISTA DE ADOTANTES ---");
        ArrayList<Adotante> lista = adotanteController.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum adotante cadastrado.");
            return;
        }

        for (Adotante a : lista) {
            System.out.println("Nome: " + a.getNome() + " | CPF: " + a.getCpf() + " | Telefone: " + a.getTelefone());
        }
    }

    private static void buscarPorCpf(Scanner scanner, AdotanteController adotanteController) {
        System.out.println("\n--- BUSCAR ADOTANTE ---");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Adotante a = adotanteController.buscarPorCpf(cpf);
        if (a != null) {
            System.out.println("Nome: " + a.getNome() + " | CPF: " + a.getCpf() + " | Telefone: " + a.getTelefone());
        } else {
            System.out.println("Adotante não encontrado.");
        }
    }

    private static void remover(Scanner scanner, AdotanteController adotanteController) {
        System.out.println("\n--- REMOVER ADOTANTE ---");
        listar(adotanteController);

        if (adotanteController.listar().isEmpty()) return;

        System.out.print("\nCPF do adotante a remover: ");
        String cpf = scanner.nextLine();

        if (adotanteController.deletar(cpf)) {
            System.out.println("Adotante removido com sucesso.");
        } else {
            System.out.println("Adotante não encontrado.");
        }
    }
}