package model;

import java.io.Serializable;
import java.time.LocalDate;

public class ConsultaVacina implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int idPet;
    private String nomePet;
    private String tipoRegistro;
    private LocalDate data;
    private String profissional;
    private String descricao;
    private String observacoes;

    public ConsultaVacina(int id, int idPet, String nomePet, String tipoRegistro, LocalDate data,
                          String profissional, String descricao, String observacoes) {
        this.id = id;
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.tipoRegistro = tipoRegistro;
        this.data = data;
        this.profissional = profissional;
        this.descricao = descricao;
        this.observacoes = observacoes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPet() { return idPet; }
    public void setIdPet(int idPet) { this.idPet = idPet; }
    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getProfissional() { return profissional; }
    public void setProfissional(String profissional) { this.profissional = profissional; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Pet: " + nomePet + " (" + idPet + ")" +
                " | Tipo: " + tipoRegistro +
                " | Data: " + data +
                " | Profissional: " + profissional +
                " | Descricao: " + descricao +
                " | Observacoes: " + observacoes;
    }
}
