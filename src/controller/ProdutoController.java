package controller;

import interfaces.ICrud;
import model.Produto;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class ProdutoController implements ICrud<Produto, Integer> {
    private ArrayList<Produto> produtos;
    private final String ARQUIVO = "produtos.dat";

    public ProdutoController() {
        this.produtos = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Produto entidade) {
        produtos.add(entidade);
        salvarDados();
        Logger.registrar("Produto cadastrado: " + entidade.getNome() + " | Categoria: " + entidade.getCategoria());
    }

    @Override
    public ArrayList<Produto> listar() {
        return produtos;
    }

    public ArrayList<Produto> listarPorCategoria(Produto.Categoria categoria) {
        ArrayList<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria() == categoria) resultado.add(p);
        }
        return resultado;
    }

    public ArrayList<Produto> listarComEstoqueBaixo(int limiteMinimo) {
        ArrayList<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getQuantidadeEstoque() <= limiteMinimo) resultado.add(p);
        }
        return resultado;
    }

    @Override
    public boolean atualizar(Integer id, Produto entidadeAtualizada) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.set(i, entidadeAtualizada);
                salvarDados();
                Logger.registrar("Produto atualizado. ID: " + id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Integer id) {
        boolean removido = produtos.removeIf(p -> p.getId() == id);
        if (removido) {
            salvarDados();
            Logger.registrar("Produto removido. ID: " + id);
        }
        return removido;
    }

    public boolean adicionarEstoque(int id, int quantidade) {
        Produto produto = buscarPorId(id);
        if (produto != null && quantidade > 0) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
            salvarDados();
            Logger.registrar("Estoque adicionado ao produto ID: " + id + " | Qtd: +" + quantidade);
            return true;
        }
        return false;
    }

    public boolean removerEstoque(int id, int quantidade) {
        Produto produto = buscarPorId(id);
        if (produto != null && quantidade > 0 && produto.getQuantidadeEstoque() >= quantidade) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            salvarDados();
            Logger.registrar("Estoque removido do produto ID: " + id + " | Qtd: -" + quantidade);
            return true;
        }
        return false;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                produtos = (ArrayList<Produto>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar produtos: " + e.getMessage());
            }
        }
    }
}