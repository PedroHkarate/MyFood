package br.ufal.ic.p2.myfood;

public class Produto {
    private static int id;
    private static String nome;
    private static float valor;
    private static String categoria;

    public Produto(int id, String nome, float valor, String categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Produto.id = id;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Produto.nome = nome;
    }

    public static float getValor() {
        return valor;
    }

    public static void setValor(float valor) {
        Produto.valor = valor;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static void setCategoria(String categoria) {
        Produto.categoria = categoria;
    }
}