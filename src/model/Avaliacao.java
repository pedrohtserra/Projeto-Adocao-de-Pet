package model;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String comentario;
    private int estrelas;

    public Avaliacao(String nome, String comentario, int estrelas) {
        this.nome = nome;
        this.comentario = comentario;
        this.estrelas = estrelas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeCliente) {
        this.nome = nomeCliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }


}