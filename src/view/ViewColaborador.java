package view;

import controller.ColaboradorController;
import controller.PetController;
import model.Animal;
import model.Cachorro;
import model.Colaborador;
import model.Gato;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewColaborador {
    public static void menu(Scanner scanner, PetController petController, ColaboradorController colaboradorController) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Área do Colaborador ---");
            System.out.println("1. Gerenciar Colaboradores");
            System.out.println("2. Gerenciar Pets");
            System.out.println("0. Voltar");
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
                    menuColaboradores(scanner, colaboradorController);
                    break;
                case 2:
                    menuPets(scanner, petController);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // ==================== SUBMENU COLABORADORES ====================

    private static void menuColaboradores(Scanner scanner, ColaboradorController colaboradorController) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Colaboradores ---");
            System.out.println("1. Cadastrar Colaborador");
            System.out.println("2. Listar Colaboradores");
            System.out.println("3. Buscar por CPF");
            System.out.println("4. Atualizar Colaborador");
            System.out.println("5. Remover Colaborador");
            System.out.println("0. Voltar");
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
                    cadastrarColaborador(scanner, colaboradorController);
                    break;
                case 2:
                    listarColaboradores(colaboradorController);
                    break;
                case 3:
                    buscarColaborador(scanner, colaboradorController);
                    break;
                case 4:
                    atualizarColaborador(scanner, colaboradorController);
                    break;
                case 5:
                    removerColaborador(scanner, colaboradorController);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarColaborador(Scanner scanner, ColaboradorController colaboradorController) {
        System.out.println("\n--- CADASTRAR COLABORADOR ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        // Verifica CPF duplicado buscando na lista
        for (Colaborador c : colaboradorController.listar()) {
            if (c.getCpf().equals(cpf)) {
                System.out.println("Já existe um colaborador cadastrado com esse CPF.");
                return;
            }
        }

        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        colaboradorController.cadastrar(new Colaborador(nome, cpf, cargo));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void listarColaboradores(ColaboradorController colaboradorController) {
        System.out.println("\n--- LISTA DE COLABORADORES ---");
        ArrayList<Colaborador> lista = colaboradorController.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        for (Colaborador c : lista) {
            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Cargo: " + c.getCargo());
        }
    }

    private static void buscarColaborador(Scanner scanner, ColaboradorController colaboradorController) {
        System.out.println("\n--- BUSCAR COLABORADOR ---");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Colaborador encontrado = null;
        for (Colaborador c : colaboradorController.listar()) {
            if (c.getCpf().equals(cpf)) {
                encontrado = c;
                break;
            }
        }

        if (encontrado != null) {
            System.out.println("Nome: " + encontrado.getNome() + " | CPF: " + encontrado.getCpf() + " | Cargo: " + encontrado.getCargo());
        } else {
            System.out.println("Colaborador não encontrado.");
        }
    }

    private static void atualizarColaborador(Scanner scanner, ColaboradorController colaboradorController) {
        System.out.println("\n--- ATUALIZAR COLABORADOR ---");
        listarColaboradores(colaboradorController);

        if (colaboradorController.listar().isEmpty()) return;

        System.out.print("\nCPF do colaborador a atualizar: ");
        String cpf = scanner.nextLine();

        Colaborador existente = null;
        for (Colaborador c : colaboradorController.listar()) {
            if (c.getCpf().equals(cpf)) {
                existente = c;
                break;
            }
        }

        if (existente == null) {
            System.out.println("Colaborador não encontrado.");
            return;
        }

        System.out.print("Novo nome (atual: " + existente.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo cargo (atual: " + existente.getCargo() + "): ");
        String novoCargo = scanner.nextLine();

        if (colaboradorController.atualizar(cpf, new Colaborador(novoNome, cpf, novoCargo))) {
            System.out.println("Colaborador atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar colaborador.");
        }
    }

    private static void removerColaborador(Scanner scanner, ColaboradorController colaboradorController) {
        System.out.println("\n--- REMOVER COLABORADOR ---");
        listarColaboradores(colaboradorController);

        if (colaboradorController.listar().isEmpty()) return;

        System.out.print("\nCPF do colaborador a remover: ");
        String cpf = scanner.nextLine();

        if (colaboradorController.deletar(cpf)) {
            System.out.println("Colaborador removido com sucesso.");
        } else {
            System.out.println("Colaborador não encontrado.");
        }
    }

    // ==================== SUBMENU PETS ====================

    private static void menuPets(Scanner scanner, PetController petController) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Pets ---");
            System.out.println("1. Cadastrar Cachorro");
            System.out.println("2. Cadastrar Gato");
            System.out.println("3. Listar Todos os Pets");
            System.out.println("4. Listar Pets Disponíveis");
            System.out.println("5. Buscar Pet por ID");
            System.out.println("6. Atualizar Pet");
            System.out.println("7. Remover Pet");
            System.out.println("0. Voltar");
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
                    cadastrarCachorro(scanner, petController);
                    break;
                case 2:
                    cadastrarGato(scanner, petController);
                    break;
                case 3:
                    listarPets(petController, false);
                    break;
                case 4:
                    listarPets(petController, true);
                    break;
                case 5:
                    buscarPet(scanner, petController);
                    break;
                case 6:
                    atualizarPet(scanner, petController);
                    break;
                case 7:
                    removerPet(scanner, petController);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarCachorro(Scanner scanner, PetController petController) {
        System.out.println("\n--- CADASTRAR CACHORRO ---");
        System.out.print("ID do pet (número inteiro): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (petController.buscarPet(id) != null) {
            System.out.println("Já existe um pet cadastrado com esse ID.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Porte (P/M/G): ");
        String porte = scanner.nextLine();

        petController.cadastrar(new Cachorro(id, nome, idade, porte));
        System.out.println("Cachorro cadastrado com sucesso!");
    }

    private static void cadastrarGato(Scanner scanner, PetController petController) {
        System.out.println("\n--- CADASTRAR GATO ---");
        System.out.print("ID do pet (número inteiro): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (petController.buscarPet(id) != null) {
            System.out.println("Já existe um pet cadastrado com esse ID.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Pelagem: ");
        String pelagem = scanner.nextLine();

        petController.cadastrar(new Gato(id, nome, idade, pelagem));
        System.out.println("Gato cadastrado com sucesso!");
    }

    private static void listarPets(PetController petController, boolean apenasDisponiveis) {
        ArrayList<Animal> lista = apenasDisponiveis ? petController.listarDisponiveis() : petController.listar();
        String titulo = apenasDisponiveis ? "PETS DISPONÍVEIS PARA ADOÇÃO" : "TODOS OS PETS";

        System.out.println("\n--- " + titulo + " ---");

        if (lista.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        for (Animal a : lista) {
            String tipo = a instanceof Cachorro ? "Cachorro" : "Gato";
            String extra = a instanceof Cachorro
                    ? " | Porte: " + ((Cachorro) a).getPorte()
                    : " | Pelagem: " + ((Gato) a).getPelagem();
            String status = a.isAdotado() ? "Adotado" : "Disponível";
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Idade: " + a.getIdade()
                    + " anos | Tipo: " + tipo + extra + " | Status: " + status);
        }
    }

    private static void buscarPet(Scanner scanner, PetController petController) {
        System.out.println("\n--- BUSCAR PET ---");
        System.out.print("ID do pet: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Animal a = petController.buscarPet(id);
        if (a == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        String tipo = a instanceof Cachorro ? "Cachorro" : "Gato";
        String extra = a instanceof Cachorro
                ? " | Porte: " + ((Cachorro) a).getPorte()
                : " | Pelagem: " + ((Gato) a).getPelagem();
        String status = a.isAdotado() ? "Adotado" : "Disponível";
        System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Idade: " + a.getIdade()
                + " anos | Tipo: " + tipo + extra + " | Status: " + status);
    }

    private static void atualizarPet(Scanner scanner, PetController petController) {
        System.out.println("\n--- ATUALIZAR PET ---");
        listarPets(petController, false);

        if (petController.listar().isEmpty()) return;

        System.out.print("\nID do pet a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Animal existente = petController.buscarPet(id);
        if (existente == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        System.out.print("Novo nome (atual: " + existente.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Nova idade (atual: " + existente.getIdade() + "): ");
        int novaIdade = scanner.nextInt();
        scanner.nextLine();

        Animal atualizado;
        if (existente instanceof Cachorro) {
            System.out.print("Novo porte (atual: " + ((Cachorro) existente).getPorte() + "): ");
            String novoPorte = scanner.nextLine();
            atualizado = new Cachorro(id, novoNome, novaIdade, novoPorte);
        } else {
            System.out.print("Nova pelagem (atual: " + ((Gato) existente).getPelagem() + "): ");
            String novaPelagem = scanner.nextLine();
            atualizado = new Gato(id, novoNome, novaIdade, novaPelagem);
        }

        atualizado.setCpfDono(existente.getCpfDono());

        if (petController.atualizar(id, atualizado)) {
            System.out.println("Pet atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar pet.");
        }
    }

    private static void removerPet(Scanner scanner, PetController petController) {
        System.out.println("\n--- REMOVER PET ---");
        listarPets(petController, false);

        if (petController.listar().isEmpty()) return;

        System.out.print("\nID do pet a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Animal pet = petController.buscarPet(id);
        if (pet != null && pet.isAdotado()) {
            System.out.println("Atenção: esse pet está adotado. Cancele a adoção antes de removê-lo.");
            return;
        }

        if (petController.deletar(id)) {
            System.out.println("Pet removido com sucesso.");
        } else {
            System.out.println("Pet não encontrado.");
        }
    }
}