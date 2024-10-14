package br.ufal.ic.p2.myfood;

public class UsuarioDono extends Usuario {
    private String cpf;

    public UsuarioDono(int id, String nome, String email, String senha, String endereco, String cpf) {
        super(id, nome, email, senha, endereco, "Dono");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
