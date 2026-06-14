package controller;

import interfaces.ICrud;
import model.Avaliacao;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class AvaliacaoController implements ICrud<Avaliacao, Integer> {
    private ArrayList<Avaliacao> avaliacoes;
    private final String ARQUIVO = "avaliacoes.dat";

    public AvaliacaoController() {
        this.avaliacoes = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Avaliacao entidade) {
        avaliacoes.add(entidade);
        salvarDados();
        Logger.registrar("Nova avaliação registrada - Cliente: " + entidade.getNome() + " | Nota: " + entidade.getEstrelas());
    }

    @Override
    public ArrayList<Avaliacao> listar() {
        return avaliacoes;
    }

    @Override
    public boolean atualizar(Integer indice, Avaliacao entidadeAtualizada) {
        // Nota: Se a sua interface ICrud usar "atualizar" em vez de "update", mude o nome do método aqui
        if (indice >= 0 && indice < avaliacoes.size()) {
            avaliacoes.set(indice, entidadeAtualizada);
            salvarDados();
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(Integer indice) {
        if (indice >= 0 && indice < avaliacoes.size()) {
            avaliacoes.remove(indice.intValue());
            salvarDados();
            return true;
        }
        return false;
    }

    public double calcularMediaEstrelas() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (int i = 0; i < avaliacoes.size(); i++) {
            Avaliacao a = avaliacoes.get(i);
            if (a.getEstrelas() >= 1 && a.getEstrelas() <= 5) {
                soma += a.getEstrelas();
            }
        }
        return soma / avaliacoes.size();
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(avaliacoes);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar avaliações.");
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                avaliacoes = (ArrayList<Avaliacao>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar avaliações.");
            }
        }
    }
}