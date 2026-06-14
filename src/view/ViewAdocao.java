package view;

import controller.AdocaoController;
import controller.AdotanteController;
import controller.PetController;
import model.Adocao;
import model.Adotante;
import model.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewAdocao {
    public static void menu(Scanner scanner, AdocaoController adocaoController,
                            PetController petController, AdotanteController adotanteController) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n--- Gerenciamento de Adocoes ---");
            System.out.println("1. Registrar adocao");
            System.out.println("2. Listar todas as adocoes");
            System.out.println("3. Buscar adocoes por CPF");
            System.out.println("4. Cancelar adocao");
            System.out.println("5. Remover registro de adocao");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarAdocao(scanner, adocaoController, petController, adotanteController);
                    break;
                case 2:
                    listarTodos(adocaoController);
                    break;
                case 3:
                    buscarPorCpf(scanner, adocaoController);
                    break;
                case 4:
                    cancelarAdocao(scanner, adocaoController, petController);
                    break;
                case 5:
                    removerRegistro(scanner, adocaoController);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static void registrarAdocao(Scanner scanner, AdocaoController adocaoController,
                                        PetController petController, AdotanteController adotanteController) {
        System.out.print("ID da adocao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (adocaoController.buscarPorId(id) != null) {
            System.out.println("Ja existe uma adocao com esse ID.");
            return;
        }

        listarPetsDisponiveis(petController);
        System.out.print("ID do pet: ");
        int idPet = scanner.nextInt();
        scanner.nextLine();

        Animal pet = petController.buscarPet(idPet);
        if (pet == null || pet.isAdotado() || adocaoController.buscarAtivaPorPet(idPet) != null) {
            System.out.println("Pet nao encontrado ou ja adotado.");
            return;
        }

        System.out.print("CPF do adotante: ");
        String cpf = scanner.nextLine();

        Adotante adotante = adotanteController.buscarPorCpf(cpf);
        if (adotante == null) {
            System.out.println("Adotante nao encontrado. Cadastre o adotante primeiro.");
            return;
        }

        Adocao adocao = new Adocao(id, idPet, pet.getNome(), cpf, adotante.getNome(), LocalDate.now());
        adocaoController.cadastrar(adocao);
        pet.setCpfDono(cpf);
        petController.atualizar(idPet, pet);
        System.out.println("Adocao registrada com sucesso!");
    }

    private static void listarTodos(AdocaoController adocaoController) {
        ArrayList<Adocao> lista = adocaoController.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma adocao cadastrada.");
            return;
        }

        System.out.println("\n--- Todas as Adocoes ---");
        for (Adocao a : lista) {
            System.out.println(a);
        }
    }

    private static void buscarPorCpf(Scanner scanner, AdocaoController adocaoController) {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        ArrayList<Adocao> lista = adocaoController.buscarPorCpf(cpf);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma adocao encontrada para esse CPF.");
            return;
        }

        System.out.println("\n--- Adocoes do CPF " + cpf + " ---");
        for (Adocao a : lista) {
            System.out.println(a);
        }
    }

    private static void cancelarAdocao(Scanner scanner, AdocaoController adocaoController,
                                       PetController petController) {
        System.out.print("ID da adocao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Adocao adocao = adocaoController.buscarPorId(id);
        if (adocao == null || !adocao.isAtiva()) {
            System.out.println("Adocao nao encontrada ou ja cancelada.");
            return;
        }

        if (adocaoController.cancelarAdocao(id)) {
            Animal pet = petController.buscarPet(adocao.getIdPet());
            if (pet != null) {
                pet.setCpfDono(null);
                petController.atualizar(pet.getId(), pet);
            }
            System.out.println("Adocao cancelada com sucesso.");
        }
    }

    private static void removerRegistro(Scanner scanner, AdocaoController adocaoController) {
        System.out.print("ID da adocao a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (adocaoController.deletar(id)) {
            System.out.println("Registro removido com sucesso.");
        } else {
            System.out.println("Adocao nao encontrada.");
        }
    }

    private static void listarPetsDisponiveis(PetController petController) {
        ArrayList<Animal> pets = petController.listarDisponiveis();
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet disponivel para adocao.");
            return;
        }

        System.out.println("\nPets disponiveis:");
        for (Animal p : pets) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Som: " + p.emitirSom());
        }
    }
}
