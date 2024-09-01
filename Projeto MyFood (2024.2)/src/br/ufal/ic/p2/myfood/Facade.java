package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

public class Facade {

    private final Sistema sistema;

    public Facade(){
        this.sistema = new Sistema();
    }


    public void zerarSistema() {

    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return sistema.getAtributoUsuario(id, atributo);

        /*if(atributo1 != null) return atributo1;
        else throw new UsuarioNaoCadastradoException();*/
    }

    public int login(String email, String senha) {
        return 0;
    }

    public void encerrarSistema(){

    }
}
