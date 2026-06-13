package view;

import controller.*;
import util.Logger;
import java.util.Scanner;

public class ViewGeral {
    public static void executar() {
        Scanner scanner = new Scanner(System.in);

        PetController petController = new PetController();
        ClienteController clienteController = new ClienteController();
        FuncionarioController funcController = new FuncionarioController();
        ProdutoController produtoController = new ProdutoController();
        FornecedorController fornController = new FornecedorController();
        BanhoTosaController banhoTosaController = new BanhoTosaController();
        VeterinarioController veterinarioController = new VeterinarioController();

        Logger.registrar("Sistema iniciado.");
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n=== SISTEMA DE ADOÇÃO DE PETS ===");
            System.out.println("1. Menu Funcionário");
            System.out.println("2. Menu Cliente");
            System.out.println("3. Menu Estoque");
            System.out.println("4. Menu Fornecedores");
            System.out.println("5. Menu Veterinario");
            System.out.println("6. Menu Banho e Tosa");
            System.out.println("7. Sair");

            System.out.print("Escolha o seu perfil: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                ViewFuncionario.menu(scanner, petController, funcController);
            } else if (opcao == 2) {
                ViewCliente.menu(scanner, petController, clienteController);
            } else if (opcao == 3) {
                ViewProduto.menu(scanner, produtoController);
            } else if (opcao == 4) {
                ViewFornecedor.menu(scanner, fornController);
            } else if (opcao == 5) {
                ViewVeterinario.menu(scanner, veterinarioController);
            }else if(opcao == 6){
                ViewBanhoTosa.menu(scanner , banhoTosaController , petController);

            }
        }

        Logger.registrar("Sistema encerrado normalmente.");
        System.out.println("Sistema encerrado.");
        scanner.close();
    }
}