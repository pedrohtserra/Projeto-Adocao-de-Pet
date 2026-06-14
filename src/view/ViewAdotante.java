package view;

import controller.AdotanteController;
import controller.PetController;
import model.Adotante;
import java.util.Scanner;

public class ViewAdotante {
    public static void menu(Scanner scanner, PetController petController, AdotanteController adotanteController) {
        int opcao = 0;
        while (opcao != 2) {
            System.out.println("\n--- Área do Adotante ---");
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

                adotanteController.cadastrar(new Adotante(nome, cpf, tel));
                System.out.println("Adotante cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}
