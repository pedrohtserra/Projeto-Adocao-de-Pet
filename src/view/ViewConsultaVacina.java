package view;

import controller.ConsultaVacinaController;
import controller.PetController;
import model.Animal;
import model.ConsultaVacina;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewConsultaVacina {
    public static void menu(Scanner scanner, ConsultaVacinaController consultaVacinaController, PetController petController) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n--- Historico de Consultas e Vacinas ---");
            System.out.println("1. Registrar consulta");
            System.out.println("2. Registrar vacina");
            System.out.println("3. Listar todos os registros");
            System.out.println("4. Listar historico por pet");
            System.out.println("5. Atualizar ou remover registro");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarRegistro(scanner, consultaVacinaController, petController, "Consulta");
                    break;
                case 2:
                    cadastrarRegistro(scanner, consultaVacinaController, petController, "Vacina");
                    break;
                case 3:
                    listarTodos(consultaVacinaController);
                    break;
                case 4:
                    listarPorPet(scanner, consultaVacinaController);
                    break;
                case 5:
                    atualizarOuRemover(scanner, consultaVacinaController, petController);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static void cadastrarRegistro(Scanner scanner, ConsultaVacinaController controller,
                                          PetController petController, String tipo) {
        System.out.print("ID do registro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (controller.buscarPorId(id) != null) {
            System.out.println("Ja existe um registro com esse ID.");
            return;
        }

        System.out.print("ID do pet: ");
        int idPet = scanner.nextInt();
        scanner.nextLine();

        Animal pet = petController.buscarPet(idPet);
        if (pet == null) {
            System.out.println("Pet nao encontrado.");
            return;
        }

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Profissional responsavel: ");
        String profissional = scanner.nextLine();
        System.out.print(tipo.equals("Vacina") ? "Nome da vacina: " : "Motivo da consulta: ");
        String descricao = scanner.nextLine();
        System.out.print("Observacoes: ");
        String observacoes = scanner.nextLine();

        ConsultaVacina registro = new ConsultaVacina(id, idPet, pet.getNome(), tipo, data,
                profissional, descricao, observacoes);
        controller.cadastrar(registro);
        System.out.println(tipo + " registrada com sucesso!");
    }

    private static void listarTodos(ConsultaVacinaController controller) {
        ArrayList<ConsultaVacina> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro cadastrado.");
            return;
        }

        System.out.println("\n--- Todos os Registros ---");
        for (ConsultaVacina r : lista) {
            System.out.println(r);
        }
    }

    private static void listarPorPet(Scanner scanner, ConsultaVacinaController controller) {
        System.out.print("ID do pet: ");
        int idPet = scanner.nextInt();
        scanner.nextLine();

        ArrayList<ConsultaVacina> lista = controller.buscarPorPet(idPet);
        if (lista.isEmpty()) {
            System.out.println("Nenhum historico encontrado para esse pet.");
            return;
        }

        System.out.println("\n--- Historico do Pet " + idPet + " ---");
        for (ConsultaVacina r : lista) {
            System.out.println(r);
        }
    }

    private static void atualizarOuRemover(Scanner scanner, ConsultaVacinaController controller,
                                           PetController petController) {
        System.out.print("ID do registro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ConsultaVacina existente = controller.buscarPorId(id);
        if (existente == null) {
            System.out.println("Registro nao encontrado.");
            return;
        }

        System.out.println("Registro encontrado: " + existente);
        System.out.println("1. Atualizar");
        System.out.println("2. Remover");
        System.out.print("Escolha: ");
        int acao = scanner.nextInt();
        scanner.nextLine();

        if (acao == 1) {
            System.out.print("Novo ID do pet (atual: " + existente.getIdPet() + "): ");
            int idPet = scanner.nextInt();
            scanner.nextLine();

            Animal pet = petController.buscarPet(idPet);
            if (pet == null) {
                System.out.println("Pet nao encontrado. Atualizacao cancelada.");
                return;
            }

            System.out.print("Tipo (Consulta/Vacina): ");
            String tipo = scanner.nextLine();
            System.out.print("Data (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());
            System.out.print("Profissional responsavel: ");
            String profissional = scanner.nextLine();
            System.out.print("Descricao: ");
            String descricao = scanner.nextLine();
            System.out.print("Observacoes: ");
            String observacoes = scanner.nextLine();

            ConsultaVacina atualizado = new ConsultaVacina(id, idPet, pet.getNome(), tipo, data,
                    profissional, descricao, observacoes);
            controller.atualizar(id, atualizado);
            System.out.println("Registro atualizado com sucesso!");
        } else if (acao == 2) {
            System.out.print("Confirma remocao? (s/n): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("s")) {
                controller.deletar(id);
                System.out.println("Registro removido com sucesso.");
            } else {
                System.out.println("Remocao cancelada.");
            }
        } else {
            System.out.println("Opcao invalida.");
        }
    }
}
