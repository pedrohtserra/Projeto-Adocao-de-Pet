package model;

public class Fornecedor extends Pessoa {
    private static final long serialVersionUID = 1L;
    private String telefone;
    private String email;
    private String categoriaPrincipal;

    public Fornecedor(String nome, String cpf, String telefone, String email, String categoriaPrincipal) {
        super(nome, cpf);
        this.telefone = telefone;
        this.email = email;
        this.categoriaPrincipal = categoriaPrincipal;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCategoriaPrincipal() { return categoriaPrincipal; }
    public void setCategoriaPrincipal(String categoriaPrincipal) { this.categoriaPrincipal = categoriaPrincipal; }

    @Override
    public String toString() {
        return "CNPJ/CPF: " + getCpf() +
                " | Nome: " + getNome() +
                " | Telefone: " + telefone +
                " | Email: " + email +
                " | Categoria: " + categoriaPrincipal;
    }
}
