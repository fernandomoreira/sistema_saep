package com.mycompany.estoque.model;

public class Usuario {

    private int id_usua;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id_usua, String nome, String email, String senha) {
        this.id_usua = id_usua;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId_usua() {
        return id_usua;
    }

    public void setId_usua(int id_usua) {
        this.id_usua = id_usua;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}