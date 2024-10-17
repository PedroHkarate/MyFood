package br.ufal.ic.p2.myfood;

public class Restaurante extends Empresa {
    private String tipoCozinha;

    public Restaurante(int id, String nome, String endereco, String tipoCozinha, Usuario dono) {
        super(id, nome, endereco, "restaurante", dono);
        this.tipoCozinha = tipoCozinha;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }
}
