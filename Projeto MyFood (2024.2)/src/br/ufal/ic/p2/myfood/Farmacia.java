package br.ufal.ic.p2.myfood;

public class Farmacia extends Empresa {
    private boolean aberto24h;
    private int numFuncionarios;

    public Farmacia(String nome, String endereco, boolean aberto24h, int numFuncionarios) {
        super(nome, endereco);
        this.aberto24h = aberto24h;
        this.numFuncionarios = numFuncionarios;
    }

    public boolean isAberto24h() {
        return aberto24h;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }
}
