package model;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Categoria {
        RACAO, BRINQUEDO, REMEDIO
    }

    private int id;
    private String nome;
    private Categoria categoria;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, Categoria categoria, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isDisponivel() {
        return quantidadeEstoque > 0;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Categoria: " + categoria +
                " | Preço: R$ " + String.format("%.2f", preco) +
                " | Estoque: " + quantidadeEstoque;
    }
}