package br.ufal.ic.p2.myfood;

public class Produtos {
    private static int id;
    private static String nome;
    private static float valor;
    private static String categoria;

    public Produtos(int id, String nome, float valor, String categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public static int getId(){
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static float getValor() {
        return valor;
    }

    public static String getCategoria() {
        return categoria;
    }
}