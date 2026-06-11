package view;

import controller.ProdutoController;
import model.Produto;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewProduto {
    public static void menu(Scanner scanner, ProdutoController produtoController) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n--- Gerenciamento de Estoque ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Todos os Produtos");
            System.out.println("3. Listar por Categoria");
            System.out.println("4. Atualizar Estoque");
            System.out.println("5. Remover Produto");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner, produtoController);
                    break;
                case 2:
                    listarTodos(produtoController);
                    break;
                case 3:
                    listarPorCategoria(scanner, produtoController);
                    break;
                case 4:
                    atualizarEstoque(scanner, produtoController);
                    break;
                case 5:
                    removerProduto(scanner, produtoController);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarProduto(Scanner scanner, ProdutoController produtoController) {
        System.out.print("ID do produto (número inteiro): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Categoria:");
        System.out.println("  1. Ração");
        System.out.println("  2. Brinquedo");
        System.out.println("  3. Remédio");
        System.out.print("Escolha: ");
        int catOpcao = scanner.nextInt();
        scanner.nextLine();

        Produto.Categoria categoria;
        switch (catOpcao) {
            case 1: categoria = Produto.Categoria.RACAO; break;
            case 2: categoria = Produto.Categoria.BRINQUEDO; break;
            case 3: categoria = Produto.Categoria.REMEDIO; break;
            default:
                System.out.println("Categoria inválida. Produto não cadastrado.");
                return;
        }

        System.out.print("Preço (ex: 29.90): ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        produtoController.cadastrar(new Produto(id, nome, categoria, preco, quantidade));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarTodos(ProdutoController produtoController) {
        ArrayList<Produto> lista = produtoController.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- Todos os Produtos ---");
        for (Produto p : lista) {
            System.out.println(p);
        }

        ArrayList<Produto> estoqueBaixo = produtoController.listarComEstoqueBaixo(5);
        if (!estoqueBaixo.isEmpty()) {
            System.out.println("\n⚠ Produtos com estoque baixo (≤ 5 unidades):");
            for (Produto p : estoqueBaixo) {
                System.out.println("  ! " + p.getNome() + " | Estoque: " + p.getQuantidadeEstoque());
            }
        }
    }

    private static void listarPorCategoria(Scanner scanner, ProdutoController produtoController) {
        System.out.println("Escolha a categoria:");
        System.out.println("  1. Ração");
        System.out.println("  2. Brinquedo");
        System.out.println("  3. Remédio");
        System.out.print("Escolha: ");
        int catOpcao = scanner.nextInt();
        scanner.nextLine();

        Produto.Categoria categoria;
        switch (catOpcao) {
            case 1: categoria = Produto.Categoria.RACAO; break;
            case 2: categoria = Produto.Categoria.BRINQUEDO; break;
            case 3: categoria = Produto.Categoria.REMEDIO; break;
            default:
                System.out.println("Categoria inválida.");
                return;
        }

        ArrayList<Produto> lista = produtoController.listarPorCategoria(categoria);
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado nessa categoria.");
            return;
        }
        System.out.println("\n--- Produtos: " + categoria + " ---");
        for (Produto p : lista) {
            System.out.println(p);
        }
    }

    private static void atualizarEstoque(Scanner scanner, ProdutoController produtoController) {
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = produtoController.buscarPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Produto encontrado: " + produto.getNome() + " | Estoque atual: " + produto.getQuantidadeEstoque());
        System.out.println("O que deseja fazer?");
        System.out.println("  1. Adicionar ao estoque");
        System.out.println("  2. Remover do estoque");
        System.out.print("Escolha: ");
        int acao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (acao == 1) {
            if (produtoController.adicionarEstoque(id, quantidade)) {
                System.out.println("Estoque atualizado! Novo total: " + produtoController.buscarPorId(id).getQuantidadeEstoque());
            } else {
                System.out.println("Não foi possível atualizar o estoque.");
            }
        } else if (acao == 2) {
            if (produtoController.removerEstoque(id, quantidade)) {
                System.out.println("Estoque atualizado! Novo total: " + produtoController.buscarPorId(id).getQuantidadeEstoque());
            } else {
                System.out.println("Quantidade inválida ou estoque insuficiente.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void removerProduto(Scanner scanner, ProdutoController produtoController) {
        System.out.print("ID do produto a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (produtoController.deletar(id)) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}