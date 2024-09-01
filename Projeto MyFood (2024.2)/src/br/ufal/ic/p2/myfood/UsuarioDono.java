package br.ufal.ic.p2.myfood;

public class UsuarioDono extends Usuario{

    private String cpf;

    public UsuarioDono(String nome, String email, String senha, String endereco, String cpf){
        super(nome, email, senha, endereco);
        this.cpf = cpf;
        this.temCpf = true;
    }

    public String getCpf(){
        return cpf;
    }
}
