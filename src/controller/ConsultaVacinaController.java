package controller;

import interfaces.ICrud;
import model.ConsultaVacina;
import util.Logger;
import java.io.*;
import java.util.ArrayList;

public class ConsultaVacinaController implements ICrud<ConsultaVacina, Integer> {
    private ArrayList<ConsultaVacina> registros;
    private final String ARQUIVO = "consultas_vacinas.dat";

    public ConsultaVacinaController() {
        this.registros = new ArrayList<>();
        carregarDados();
    }

    @Override
    public void cadastrar(ConsultaVacina entidade) {
        registros.add(entidade);
        salvarDados();
        Logger.registrar("Consulta/Vacina registrada para pet ID: " + entidade.getIdPet());
    }

    @Override
    public ArrayList<ConsultaVacina> listar() {
        return registros;
    }

    @Override
    public boolean atualizar(Integer id, ConsultaVacina entidadeAtualizada) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == id) {
                registros.set(i, entidadeAtualizada);
                salvarDados();
                Logger.registrar("Consulta/Vacina atualizada. ID: " + id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Integer id) {
        boolean removido = registros.removeIf(r -> r.getId() == id);
        if (removido) {
            salvarDados();
            Logger.registrar("Consulta/Vacina removida. ID: " + id);
        }
        return removido;
    }

    public ConsultaVacina buscarPorId(int id) {
        for (ConsultaVacina r : registros) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public ArrayList<ConsultaVacina> buscarPorPet(int idPet) {
        ArrayList<ConsultaVacina> resultado = new ArrayList<>();
        for (ConsultaVacina r : registros) {
            if (r.getIdPet() == idPet) resultado.add(r);
        }
        return resultado;
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(registros);
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar consultas/vacinas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                registros = (ArrayList<ConsultaVacina>) ois.readObject();
            } catch (Exception e) {
                Logger.registrar("Erro ao carregar consultas/vacinas: " + e.getMessage());
            }
        }
    }
}
