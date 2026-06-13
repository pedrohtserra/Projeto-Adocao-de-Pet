package controller;

import interfaces.ICrud;
import model.Veterinario;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class VeterinarioController implements ICrud<Veterinario, String> {
    private ArrayList<Veterinario> veterinarios;
    private final String ARQUIVO = "veterinarios.dat";

    public VeterinarioController() {
        this.veterinarios = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(Veterinario entidade) {
        veterinarios.add(entidade);
        salvarDados();
        Logger.registrar("Veterinário cadastrado CPF: " + entidade.getCpf());
    }

    @Override
    public ArrayList<Veterinario> listar() { return veterinarios; }

    @Override
    public boolean atualizar(String cpf, Veterinario entidadeAtualizada) {
        for (int i = 0; i < veterinarios.size(); i++) {
            if (veterinarios.get(i).getCpf().equals(cpf)) {
                veterinarios.set(i, entidadeAtualizada);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(String cpf) {
        boolean removido = veterinarios.removeIf(v -> v.getCpf().equals(cpf));
        if (removido) salvarDados();
        return removido;
    }

    public Veterinario buscarPorCpf(String cpf) {
        for (Veterinario v : veterinarios) {
            if (v.getCpf().equals(cpf)) return v;
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(veterinarios);
        } catch (IOException e) { Logger.registrar("Erro ao salvar veterinários."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                veterinarios = (ArrayList<Veterinario>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar veterinários."); }
        }
    }
}