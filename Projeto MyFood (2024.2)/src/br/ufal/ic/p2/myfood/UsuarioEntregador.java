package br.ufal.ic.p2.myfood;

public class UsuarioEntregador extends Usuario {
    public UsuarioEntregador(int id, String nome, String email, String senha, String endereco) {
        super(id, nome, email, senha, endereco, "Entregador");
    }
}
