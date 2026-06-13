package view;

import controller.VeterinarioController;
import model.Veterinario;
import java.util.Scanner;

public class ViewVeterinario {
    public static void menu(Scanner scanner, VeterinarioController vetController) {
        int opcao = -1;
        while (opcao != 5) {
            System.out.println("\n--- MENU VETERINÁRIO ---");
            System.out.println("1. Cadastrar veterinário");
            System.out.println("2. Listar veterinários");
            System.out.println("3. Atualizar veterinário");
            System.out.println("4. Remover veterinário");
            System.out.println("5. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("CRMV: ");
                    String crmv = scanner.nextLine();
                    System.out.print("Especialidade: ");
                    String esp = scanner.nextLine();
                    vetController.cadastrar(new Veterinario(nome, cpf, crmv, esp));
                    System.out.println("Veterinário cadastrado!");
                    break;

                case 2:
                    if (vetController.listar().isEmpty()) System.out.println("Nenhum veterinário cadastrado.");
                    else for (Veterinario v : vetController.listar()) System.out.println(v);
                    break;

                case 3:
                    System.out.print("CPF do veterinário a atualizar: ");
                    String cpfAtt = scanner.nextLine();
                    Veterinario existente = vetController.buscarPorCpf(cpfAtt);
                    if (existente == null) { System.out.println("Não encontrado."); break; }
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo CRMV: ");
                    String novoCrmv = scanner.nextLine();
                    System.out.print("Nova especialidade: ");
                    String novaEsp = scanner.nextLine();
                    vetController.atualizar(cpfAtt, new Veterinario(novoNome, cpfAtt, novoCrmv, novaEsp));
                    System.out.println("Atualizado!");
                    break;

                case 4:
                    System.out.print("CPF do veterinário a remover: ");
                    String cpfDel = scanner.nextLine();
                    boolean ok = vetController.deletar(cpfDel);
                    System.out.println(ok ? "Removido." : "Não encontrado.");
                    break;

                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}