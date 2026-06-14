package model;

public class Colaborador extends Pessoa {
    private static final long serialVersionUID = 1L;
    private String cargo;

    public Colaborador(String nome, String cpf, String cargo) {
        super(nome, cpf);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}