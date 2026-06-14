package model;

public class Adotante extends Pessoa {
    private static final long serialVersionUID = 1L;
    private String telefone;

    public Adotante(String nome, String cpf, String telefone) {
        super(nome, cpf);
        this.telefone = telefone;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}