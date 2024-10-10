package br.ufal.ic.p2.myfood;

public class Usuario {
    private static int counterId = 0;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;

    public Usuario(String nome, String email, String senha, String endereco){
        this.id = counterId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }
}
