package br.ufal.ic.p2.myfood;

public class Restaurante extends Empresa {
    private String tipoCozinha;

    public Restaurante(String nome, String endereco, String tipoCozinha) {
        super(nome, endereco);
        this.tipoCozinha = tipoCozinha;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }
}
