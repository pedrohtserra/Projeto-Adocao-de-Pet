package model;

public class Veterinario extends Pessoa {
    private static final long serialVersionUID = 1L;
    private String crmv;
    private String especialidade;

    public Veterinario(String nome, String cpf, String crmv, String especialidade) {
        super(nome, cpf);
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

    public String getCrmv() { return crmv; }
    public void setCrmv(String crmv) { this.crmv = crmv; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public String toString() {
        return "Veterinário: " + getNome() + " | CPF: " + getCpf() + " | CRMV: " + crmv + " | Especialidade: " + especialidade;
    }
}