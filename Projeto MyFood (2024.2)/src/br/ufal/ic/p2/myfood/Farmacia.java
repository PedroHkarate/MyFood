package br.ufal.ic.p2.myfood;

public class Farmacia extends Empresa {

    public Farmacia(int id, String nome, String endereco, Usuario dono) {
        super(id, nome, endereco, "farmacia", dono);  // Use "farmacia" como tipo específico
    }
}
