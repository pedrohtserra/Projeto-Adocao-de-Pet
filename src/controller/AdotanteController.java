package controller;

import interfaces.ICrud;
import model.Adotante;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class AdotanteController implements ICrud<Adotante, String> {
    private ArrayList<Adotante> adotantes;
    private final String ARQUIVO = "adotantes.dat";

    public AdotanteController() {
        this.adotantes = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Adotante entidade) {
        adotantes.add(entidade);
        salvarDados();
        Logger.registrar("Adotante cadastrado CPF: " + entidade.getCpf());
    }

    @Override
    public ArrayList<Adotante> listar() { return adotantes; }

    @Override
    public boolean atualizar(String cpf, Adotante entidadeAtualizada) {
        for (int i = 0; i < adotantes.size(); i++) {
            if (adotantes.get(i).getCpf().equals(cpf)) {
                adotantes.set(i, entidadeAtualizada);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = adotantes.removeIf(a -> a.getCpf().equals(cpf));
        if (removido) salvarDados();
        return removido;
    }

    public Adotante buscarPorCpf(String cpf) {
        for (Adotante a : adotantes) {
            if (a.getCpf().equals(cpf)) return a;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(adotantes);
        } catch (IOException e) { Logger.registrar("Erro ao salvar adotantes."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                adotantes = (ArrayList<Adotante>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar adotantes."); }
        }
    }
}