package model;

import java.io.Serializable;
import java.time.LocalDate;

public class BanhoTosa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomePet;
    private String tipoServico; // Banho, Tosa, Banho e Tosa
    private LocalDate data;
    private double valor;
    private boolean concluido;

    public BanhoTosa(String nomePet, String tipoServico, LocalDate data, double valor) {
        this.nomePet = nomePet;
        this.tipoServico = tipoServico;
        this.data = data;
        this.valor = valor;
        this.concluido = false;
    }

    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }

    @Override
    public String toString() {
        return "Pet: " + nomePet + " | Serviço: " + tipoServico + " | Data: " + data
                + " | Valor: R$ " + String.format("%.2f", valor)
                + " | Status: " + (concluido ? "Concluído" : "Agendado");
    }
}