package controller;

import interfaces.ICrud;
import model.Fornecedor;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class FornecedorController implements ICrud<Fornecedor, String> {
    private ArrayList<Fornecedor> fornecedores;
    private final String ARQUIVO = "fornecedores.dat";

    public FornecedorController() {
        this.fornecedores = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Fornecedor entidade) {
        fornecedores.add(entidade);
        salvarDados();
        Logger.registrar("Fornecedor cadastrado: " + entidade.getNome() + " | CNPJ/CPF: " + entidade.getCpf());
    }

    @Override
    public ArrayList<Fornecedor> listar() {
        return fornecedores;
    }

    public ArrayList<Fornecedor> listarPorCategoria(String categoria) {
        ArrayList<Fornecedor> resultado = new ArrayList<>();
        for (Fornecedor f : fornecedores) {
            if (f.getCategoriaPrincipal().equalsIgnoreCase(categoria)) resultado.add(f);
        }
        return resultado;
    }

    @Override
    public boolean atualizar(String cpf, Fornecedor entidadeAtualizada) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getCpf().equals(cpf)) {
                fornecedores.set(i, entidadeAtualizada);
                salvarDados();
                Logger.registrar("Fornecedor atualizado. CNPJ/CPF: " + cpf);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = fornecedores.removeIf(f -> f.getCpf().equals(cpf));
        if (removido) {
            salvarDados();
            Logger.registrar("Fornecedor removido. CNPJ/CPF: " + cpf);
        }
        return removido;
    }

    public Fornecedor buscarPorCpf(String cpf) {
        for (Fornecedor f : fornecedores) {
            if (f.getCpf().equals(cpf)) return f;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(fornecedores);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                fornecedores = (ArrayList<Fornecedor>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar fornecedores: " + e.getMessage());
            }
        }
    }
}