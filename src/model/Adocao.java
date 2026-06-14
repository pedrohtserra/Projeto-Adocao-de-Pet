package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Adocao implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int idPet;
    private String nomePet;
    private String cpfCliente;
    private String nomeCliente;
    private LocalDate data;
    private boolean ativa;

    public Adocao(int id, int idPet, String nomePet, String cpfCliente, String nomeCliente, LocalDate data) {
        this.id = id;
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.data = data;
        this.ativa = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPet() { return idPet; }
    public void setIdPet(int idPet) { this.idPet = idPet; }
    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Pet: " + nomePet + " (" + idPet + ")" +
                " | Cliente: " + nomeCliente + " | CPF: " + cpfCliente +
                " | Data: " + data +
                " | Status: " + (ativa ? "Ativa" : "Cancelada");
    }
}
