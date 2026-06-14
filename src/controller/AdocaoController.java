package controller;

import interfaces.ICrud;
import model.Adocao;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class AdocaoController implements ICrud<Adocao, Integer> {
    private ArrayList<Adocao> adocoes;
    private final String ARQUIVO = "adocoes.dat";

    public AdocaoController() {
        this.adocoes = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Adocao entidade) {
        adocoes.add(entidade);
        salvarDados();
        Logger.registrar("Adocao registrada. Pet ID: " + entidade.getIdPet() + " | CPF: " + entidade.getCpfCliente());
    }

    @Override
    public ArrayList<Adocao> listar() {
        return adocoes;
    }

    @Override
    public boolean atualizar(Integer id, Adocao entidadeAtualizada) {
        for (int i = 0; i < adocoes.size(); i++) {
            if (adocoes.get(i).getId() == id) {
                adocoes.set(i, entidadeAtualizada);
                salvarDados();
                Logger.registrar("Adocao atualizada. ID: " + id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Integer id) {
        boolean removido = adocoes.removeIf(a -> a.getId() == id);
        if (removido) {
            salvarDados();
            Logger.registrar("Adocao removida. ID: " + id);
        }
        return removido;
    }

    public boolean cancelarAdocao(int id) {
        Adocao adocao = buscarPorId(id);
        if (adocao != null && adocao.isAtiva()) {
            adocao.setAtiva(false);
            salvarDados();
            Logger.registrar("Adocao cancelada. ID: " + id);
            return true;
        }
        return false;
    }

    public Adocao buscarPorId(int id) {
        for (Adocao a : adocoes) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public Adocao buscarAtivaPorPet(int idPet) {
        for (Adocao a : adocoes) {
            if (a.getIdPet() == idPet && a.isAtiva()) return a;
        }
        return null;
    }

    public ArrayList<Adocao> buscarPorCpf(String cpf) {
        ArrayList<Adocao> resultado = new ArrayList<>();
        for (Adocao a : adocoes) {
            if (a.getCpfCliente().equals(cpf)) resultado.add(a);
        }
        return resultado;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(adocoes);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar adocoes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                adocoes = (ArrayList<Adocao>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar adocoes: " + e.getMessage());
            }
        }
    }
}
