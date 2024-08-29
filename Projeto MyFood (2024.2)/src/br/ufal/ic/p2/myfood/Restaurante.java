package br.ufal.ic.p2.myfood;

public class Restaurante {
    private int id;
    private int dono; //id do usuario criador do restaurante
    private String nome;
    private String endereco;
    private String tipoCozinha;

    public Restaurante(int id, int dono, String nome, String endereco, String tipoCozinha) {
        this.id = id;
        this.dono = dono;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }

    public int getId() {
        return id;
    }

    public int getDono() {
        return dono;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }
}