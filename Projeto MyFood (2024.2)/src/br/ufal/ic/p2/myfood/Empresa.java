package br.ufal.ic.p2.myfood;

public class Empresa {
    private static int counterId = 0;
    private int eid;
    private String nome;
    private String endereco;
    private Usuario dono;

    public Empresa(String nome, String endereco, Usuario dono) {
        this.eid = counterId++;
        this.nome = nome;
        this.endereco = endereco;
        this.dono = dono;
    }

    public int getEid() {
        return eid;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Usuario getDono() {
        return dono;
    }
}