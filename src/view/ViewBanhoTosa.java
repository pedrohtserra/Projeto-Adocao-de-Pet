package view;

import controller.BanhoTosaController;
import controller.PetController;
import model.Animal;
import model.BanhoTosa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class ViewBanhoTosa {
    public static void menu(Scanner scanner, BanhoTosaController banhoTosaController, PetController petController) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU BANHO E TOSA ---");
            System.out.println("1. Agendar serviço");
            System.out.println("2. Listar todos os agendamentos");
            System.out.println("3. Listar agendamentos por pet");
            System.out.println("4. Marcar como concluído");
            System.out.println("5. Cancelar agendamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do pet: ");
                    String nomePet = scanner.nextLine();
                    Animal pet = petController.buscarPet(nomePet);
                    if (pet == null) {
                        System.out.println("Pet não encontrado.");
                        break;
                    }
                    System.out.print("Tipo de serviço (Banho/Tosa/Banho e Tosa): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Valor do serviço: R$ ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    banhoTosaController.cadastrar(new BanhoTosa(nomePet, tipo, LocalDate.now(), valor));
                    System.out.println("Serviço agendado com sucesso!");
                    break;

                case 2:
                    listarComIndice(banhoTosaController.listar());
                    break;

                case 3:
                    System.out.print("Nome do pet: ");
                    String busca = scanner.nextLine();
                    ArrayList<BanhoTosa> resultado = banhoTosaController.buscarPorPet(busca);
                    if (resultado.isEmpty()) System.out.println("Nenhum agendamento encontrado para este pet.");
                    else for (BanhoTosa b : resultado) System.out.println(b);
                    break;

                case 4:
                    listarComIndice(banhoTosaController.listar());
                    if (banhoTosaController.listar().isEmpty()) break;
                    System.out.print("Número do agendamento a concluir: ");
                    int idxConc = scanner.nextInt();
                    scanner.nextLine();
                    boolean okConc = banhoTosaController.marcarComoConcluido(idxConc);
                    System.out.println(okConc ? "Marcado como concluído!" : "Índice inválido.");
                    break;

                case 5:
                    listarComIndice(banhoTosaController.listar());
                    if (banhoTosaController.listar().isEmpty()) break;
                    System.out.print("Número do agendamento a cancelar: ");
                    int idxDel = scanner.nextInt();
                    scanner.nextLine();
                    boolean okDel = banhoTosaController.deletar(idxDel);
                    System.out.println(okDel ? "Agendamento cancelado." : "Índice inválido.");
                    break;

                case 0:
                    System.out.println("Voltando ao menu geral...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarComIndice(ArrayList<BanhoTosa> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento registrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i));
        }
    }
}
