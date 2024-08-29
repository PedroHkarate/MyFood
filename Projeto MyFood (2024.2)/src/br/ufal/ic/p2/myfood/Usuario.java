package br.ufal.ic.p2.myfood;

// id, nome, email, senha e endereço NÃO deveriam ser estaticos(?) ja que são atributos compartilhados

public class Usuario {
    private static int id;
    private static String nome;
    private static String email;
    private static String senha;
    private static String endereco;

    public Usuario(int id, String nome, String email, String senha, String endereco){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public static int getId() {
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static String getEmail(){
        return email;
    }

    public static String getSenha() {
        return senha;
    }

    public static String getEndereco() {
        return endereco;
    }
}
