package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

public class Facade {
    private final Sistema sistema;

    public Facade(){
        this.sistema = new Sistema();
    }

    public void zerarSistema() {
        sistema.zerarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return sistema.getAtributoUsuario(id, atributo);
    }

    public int login(String email, String senha) throws Exception {
        return sistema.login(email, senha);
    }

    public void encerrarSistema(){

    }
}
