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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Restaurante.id = id;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Restaurante.nome = nome;
    }

    public static String getEndereco() {
        return endereco;
    }

    public static void setEndereco(String endereco) {
        Restaurante.endereco = endereco;
    }

    public static String getTipoCozinha() {
        return tipoCozinha;
    }

    public static void setTipoCozinha(String tipoCozinha) {
        Restaurante.tipoCozinha = tipoCozinha;
    }
}