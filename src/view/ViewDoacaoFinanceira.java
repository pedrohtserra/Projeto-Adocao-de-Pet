package view;

import controller.DoacaoFinanceiraController;
import model.DoacaoFinanceira;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewDoacaoFinanceira{

    private DoacaoFinanceiraController controller;
    private Scanner entrada;

    public ViewDoacaoFinanceira(DoacaoFinanceiraController controller){
        this.controller = controller;
        this.entrada = new Scanner(System.in);
    }

    public void exibirMenu(){
        int opcao = -1;

        while(opcao != 0){
            System.out.println("\n--- GERENCIAMENTO DE DOAÇÕES FINANCEIRAS ---");
            System.out.println("1 - Cadastrar Doação");
            System.out.println("2 - Listar Doações");
            System.out.println("3 - Atualizar Doação");
            System.out.println("4 - Deletar Doação");
            System.out.println("5 - Confirmar Recebimento");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    menuCadastrar();
                    break;
                case 2:
                    menuListar();
                    break;
                case 3:
                    menuAtualizar();
                    break;
                case 4:
                    menuDeletar();
                    break;
                case 5:
                    menuConfirmarRecebimento();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuCadastrar(){
        System.out.println("\n--- CADASTRAR NOVA DOAÇÃO ---");

        System.out.print("Nome do Doador: ");
        String nome = entrada.nextLine();

        String cpf = "";
        while (cpf.length() != 11){
            System.out.print("Novo CPF do Doador (apenas 11 números): ");
            cpf = entrada.nextLine();

            if (cpf.length() != 11){
                System.out.println("Erro: O CPF deve conter exatamente 11 dígitos. Tente novamente.");
            }
        }

        System.out.print("Valor da Doação (R$): ");
        float valor = entrada.nextFloat();
        entrada.nextLine();

        DoacaoFinanceira novaDoacao = new DoacaoFinanceira(false, valor, nome, cpf);

        controller.cadastrar(novaDoacao);

        System.out.println("Doação registrada com sucesso!");
    }

    private void menuListar(){
        System.out.println("\n--- LISTA DE DOAÇÕES REGISTRADAS ---");

        ArrayList<DoacaoFinanceira> lista = controller.listar();

        if (lista.isEmpty()){
            System.out.println("Nenhuma doação cadastrada até o momento.");
            return;
        }

        for (int i = 0; i < lista.size(); i++){
            DoacaoFinanceira d = lista.get(i);
            if (d.isValorRecebido()) {
                System.out.println("[" + i + "] Doador: " + d.getNome() + " | CPF: " + d.getCPF() + " | Valor: R$ " + d.getDoacao() + " | Status: Confirmado");
            }else{
                System.out.println("[" + i + "] Doador: " + d.getNome() + " | CPF: " + d.getCPF() + " | Valor: R$ " + d.getDoacao() + " | Status: Pendente");
            }
        }
    }

    private void menuAtualizar(){
        System.out.println("\n--- ATUALIZAR DOAÇÃO ---");
        menuListar();

        ArrayList<DoacaoFinanceira> lista = controller.listar();
        if (lista.isEmpty()) return;

        System.out.print("\nDigite o número (índice) da doação que deseja alterar: ");
        int indice = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Novo Nome do Doador: ");
        String nome = entrada.nextLine();

        String cpf = "";
        while (cpf.length() != 11){
            System.out.print("Novo CPF do Doador (apenas 11 números): ");
            cpf = entrada.nextLine();

            if (cpf.length() != 11){
                System.out.println("Erro: O CPF deve conter exatamente 11 dígitos. Tente novamente.");
            }
        }

        System.out.print("Novo Valor da Doação (R$): ");
        float valor = entrada.nextFloat();

        System.out.print("O valor já foi recebido? (true/false): ");
        boolean recebido = entrada.nextBoolean();
        entrada.nextLine();

        DoacaoFinanceira doacaoAtualizada = new DoacaoFinanceira(recebido, valor, nome, cpf);

        if(controller.atualizar(indice, doacaoAtualizada)){
            System.out.println("Doação atualizada com sucesso!");
        }else{
            System.out.println("Erro: Índice inválido!");
        }
    }

    private void menuDeletar(){
        System.out.println("\n--- DELETAR DOAÇÃO ---");
        menuListar();

        ArrayList<DoacaoFinanceira> lista = controller.listar();
        if (lista.isEmpty()) return;

        System.out.print("\nDigite o número (índice) da doação que deseja deletar: ");
        int indice = entrada.nextInt();
        entrada.nextLine();

        if (controller.deletar(indice)){
            System.out.println("Doação removida com sucesso!");
        }else{
            System.out.println("Erro: Índice inválido!");
        }
    }

    private void menuConfirmarRecebimento(){
        System.out.println("\n--- CONFIRMAR RECEBIMENTO ---");
        menuListar();

        ArrayList<DoacaoFinanceira> lista = controller.listar();
        if (lista.isEmpty()) return;

        System.out.print("\nDigite o índice da doação que deseja dar baixa (Confirmar): ");
        int indice = entrada.nextInt();
        entrada.nextLine(); // Limpa buffer

        if (controller.confirmarRecebimento(indice)){
            System.out.println("Status atualizado para >> Confirmado << com sucesso!");
        }else{
            System.out.println("Erro: Índice inválido!");
        }
    }

}