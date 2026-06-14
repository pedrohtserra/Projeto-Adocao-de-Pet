package controller;

import interfaces.ICrud;
import model.Colaborador;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class ColaboradorController implements ICrud<Colaborador, String> {
    private ArrayList<Colaborador> colaboradores;
    private final String ARQUIVO = "colaboradores.dat";

    public ColaboradorController() {
        this.colaboradores = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Colaborador entidade) {
        colaboradores.add(entidade);
        salvarDados();
        Logger.registrar("Colaborador cadastrado: " + entidade.getNome());
    }

    @Override
    public ArrayList<Colaborador> listar() { return colaboradores; }

    @Override
    public boolean atualizar(String cpf, Colaborador entidadeAtualizada) {
        for (int i = 0; i < colaboradores.size(); i++) {
            if (colaboradores.get(i).getCpf().equals(cpf)) {
                colaboradores.set(i, entidadeAtualizada);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = colaboradores.removeIf(c -> c.getCpf().equals(cpf));
        if (removido) salvarDados();
        return removido;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(colaboradores);
        } catch (IOException e) { Logger.registrar("Erro ao salvar colaboradores."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                colaboradores = (ArrayList<Colaborador>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar colaboradores."); }
        }
    }
}