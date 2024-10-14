package br.ufal.ic.p2.myfood;

public class UsuarioCliente extends Usuario {
    public UsuarioCliente(int id, String nome, String email, String senha, String endereco) {
        super(id, nome, email, senha, endereco, "Cliente");
    }
}
