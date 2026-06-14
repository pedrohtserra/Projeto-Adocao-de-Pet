package view;

import controller.ColaboradorController;
import controller.PetController;
import model.Cachorro;
import model.Colaborador;
import model.Gato;
import java.util.Scanner;

public class ViewColaborador {
    public static void menu(Scanner scanner, PetController petController, ColaboradorController colaboradorController) {
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n--- Área do Colaborador ---");
            System.out.println("1. Cadastrar Novo Colaborador");
            System.out.println("2. Cadastrar Cachorro");
            System.out.println("3. Cadastrar Gato");
            System.out.println("4. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeColaborador = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfColaborador = scanner.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    colaboradorController.cadastrar(new Colaborador(nomeColaborador, cpfColaborador, cargo));
                    System.out.println("Colaborador cadastrado!");
                    break;
                case 2:
                case 3:
                    System.out.print("ID do pet (Número inteiro): ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do Pet: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    if (opcao == 2) {
                        System.out.print("Porte (P/M/G): ");
                        String porte = scanner.nextLine();
                        petController.cadastrar(new Cachorro(id, nome, idade, porte));
                        System.out.println("Cachorro cadastrado!");
                    } else {
                        System.out.print("Pelagem: ");
                        String pelagem = scanner.nextLine();
                        petController.cadastrar(new Gato(id, nome, idade, pelagem));
                        System.out.println("Gato cadastrado!");
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}