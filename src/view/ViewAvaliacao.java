package view;

import controller.AvaliacaoController;
import model.Avaliacao;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewAvaliacao {

    public static void menu(Scanner entrada, AvaliacaoController controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=========================================");
            System.out.println("         * AVALIAÇÕES E FEEDBACKS *         ");
            System.out.println("=========================================");
            System.out.println("1 - Deixar uma Avaliação");
            System.out.println("2 - Ver Todas as Avaliações");
            System.out.println("3 - Editar Avaliação");
            System.out.println("4 - Excluir Avaliação");
            System.out.println("0 - Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {
                    case 1: menuCadastrar(entrada, controller); break;
                    case 2: menuListar(controller); break;
                    case 3: menuAtualizar(entrada, controller); break;
                    case 4: menuDeletar(entrada, controller); break;
                    case 0: System.out.println("\nVoltando ao menu anterior..."); break;
                    default: System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nErro: Entrada inválida! Digite apenas números.");
                entrada.nextLine();
            }
        }
    }

    private static void menuCadastrar(Scanner entrada, AvaliacaoController controller) {
        System.out.println("\n--- NOVA AVALIAÇÃO ---");
        System.out.print("Seu Nome: ");
        String nome = entrada.nextLine();

        int estrelas = 0;
        while (estrelas < 1 || estrelas > 5) {
            System.out.print("Sua Nota de 1 a 5 (Estrelas): ");
            try {
                estrelas = entrada.nextInt();
                entrada.nextLine();
                if (estrelas < 1 || estrelas > 5) {
                    System.out.println("Erro: A nota deve ser estritamente entre 1 e 5.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Digite um número inteiro válido.");
                entrada.nextLine();
            }
        }

        System.out.print("Escreva seu Comentário/Feedback: ");
        String comentario = entrada.nextLine();

        Avaliacao novaAvaliacao = new Avaliacao(nome, comentario, estrelas);
        controller.cadastrar(novaAvaliacao);

        System.out.println("Avaliação registrada com sucesso! Obrigado pelo feedback.");
    }

    private static void menuListar(AvaliacaoController controller) {
        System.out.println("\n--- AVALIAÇÕES RECEBIDAS ---");
        ArrayList<Avaliacao> lista = controller.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada até o momento.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Avaliacao a = lista.get(i);
            String estrelasVisuais = "*".repeat(a.getEstrelas());
            System.out.println("[" + i + "] Cliente: " + a.getNome());
            System.out.println("    Nota: " + estrelasVisuais + " (" + a.getEstrelas() + "/5)");
            System.out.println("    Feedback: \"" + a.getComentario() + "\"");
            System.out.println("----------------------------------------");
        }
        System.out.printf("Média Geral de Satisfação: %.1f / 5.0\n", controller.calcularMediaEstrelas());
    }

    private static void menuAtualizar(Scanner entrada, AvaliacaoController controller) {
        System.out.println("\n--- EDITAR AVALIAÇÃO ---");
        menuListar(controller);

        ArrayList<Avaliacao> lista = controller.listar();
        if (lista.isEmpty()) return;

        System.out.print("\nDigite o índice da avaliação que deseja editar: ");
        int indice = entrada.nextInt();
        entrada.nextLine();

        if (indice >= 0 && indice < lista.size()) {
            System.out.print("Novo Nome do Cliente: ");
            String nome = entrada.nextLine();

            int estrelas = 0;
            while (estrelas < 1 || estrelas > 5) {
                System.out.print("Nova Nota de 1 a 5 (Estrelas): ");
                try {
                    estrelas = entrada.nextInt();
                    entrada.nextLine();
                    if (estrelas < 1 || estrelas > 5) {
                        System.out.println("Erro: A nota deve ser estritamente entre 1 e 5.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Digite um número inteiro válido.");
                    entrada.nextLine();
                }
            }

            System.out.print("Novo Comentário/Feedback: ");
            String comentario = entrada.nextLine();

            Avaliacao avaliacaoAtualizada = new Avaliacao(nome, comentario, estrelas);

            if (controller.atualizar(indice, avaliacaoAtualizada)) {
                System.out.println("Avaliação atualizada com sucesso!");
            } else {
                System.out.println("Erro ao atualizar avaliação.");
            }
        } else {
            System.out.println("Erro: Índice inválido!");
        }
    }

    private static void menuDeletar(Scanner entrada, AvaliacaoController controller) {
        System.out.println("\n--- EXCLUIR AVALIAÇÃO ---");
        menuListar(controller);

        ArrayList<Avaliacao> lista = controller.listar();
        if (lista.isEmpty()) return;

        System.out.print("\nDigite o índice da avaliação que deseja excluir: ");
        int indice = entrada.nextInt();
        entrada.nextLine();

        if (controller.deletar(indice)) {
            System.out.println("Avaliação excluída com sucesso!");
        } else {
            System.out.println("Erro: Índice inválido!");
        }
    }
}