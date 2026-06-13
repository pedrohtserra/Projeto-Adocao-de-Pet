package controller;

import interfaces.ICrud;
import model.BanhoTosa;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class BanhoTosaController implements ICrud<BanhoTosa, Integer> {
    private ArrayList<BanhoTosa> agendamentos;
    private final String ARQUIVO = "banhotosa.dat";

    public BanhoTosaController() {
        this.agendamentos = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(BanhoTosa entidade) {
        agendamentos.add(entidade);
        salvarDados();
        Logger.registrar("Banho/Tosa agendado - Pet: " + entidade.getNomePet() + " | Serviço: " + entidade.getTipoServico());
    }

    @Override
    public ArrayList<BanhoTosa> listar() { return agendamentos; }

    // ID = índice na lista
    @Override
    public boolean atualizar(Integer indice, BanhoTosa entidadeAtualizada) {
        if (indice >= 0 && indice < agendamentos.size()) {
            agendamentos.set(indice, entidadeAtualizada);
            salvarDados();
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(Integer indice) {
        if (indice >= 0 && indice < agendamentos.size()) {
            agendamentos.remove(indice.intValue());
            salvarDados();
            return true;
        }
        return false;
    }

    public boolean marcarComoConcluido(Integer indice) {
        if (indice >= 0 && indice < agendamentos.size()) {
            agendamentos.get(indice).setConcluido(true);
            salvarDados();
            return true;
        }
        return false;
    }

    public ArrayList<BanhoTosa> buscarPorPet(String nomePet) {
        ArrayList<BanhoTosa> resultado = new ArrayList<>();
        for (BanhoTosa b : agendamentos) {
            if (b.getNomePet().equalsIgnoreCase(nomePet)) resultado.add(b);
        }
        return resultado;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(agendamentos);
        } catch (IOException e) { Logger.registrar("Erro ao salvar agendamentos de banho/tosa."); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                agendamentos = (ArrayList<BanhoTosa>) ois.readObject();
            } catch (Exception e) { Logger.registrar("Erro ao carregar agendamentos de banho/tosa."); }
        }
    }
}