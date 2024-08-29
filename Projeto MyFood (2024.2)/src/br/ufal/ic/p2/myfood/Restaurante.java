package br.ufal.ic.p2.myfood;

public class Restaurante {
    private static int id;
    private static String nome;
    private static String endereco;
    private static String tipoCozinha;

    public Restaurante(int id, String nome, String endereco, String tipoCozinha) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }

    public static int getId(){
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static String getEndereco() {
        return endereco;
    }

    public static String getTipoCozinha() {
        return tipoCozinha;
    }
}