package view;

import controller.ClienteController;
import controller.PetController;
import model.Cliente;
import java.util.Scanner;

public class ViewCliente {
    public static void menu(Scanner scanner, PetController petController, ClienteController clienteController) {
        int opcao = 0;
        while (opcao != 2) {
            System.out.println("\n--- Área do Cliente ---");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Telefone: ");
                String tel = scanner.nextLine();

                clienteController.cadastrar(new Cliente(nome, cpf, tel));
                System.out.println("Cliente cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}
