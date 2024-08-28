package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.UsuarioNaoCadastradoException;

public class Facade {

    private final Sistema sistema;

    public Facade(){
        this.sistema = new Sistema();
    }


    public void zerarSistema() {

    }
    public void criarUsuario(String nome, String email, String senha, String endereco){

    }

    public void getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        String usuario1 = new Sistema().getAtributoUsuario(id, atributo);

        if(usuario1 != null) System.out.println(usuario1);
        else new UsuarioNaoCadastradoException();
    }

    public int login(String email, String senha){
        return 0;
    }

    public void encerrarSistema(){

    }
}
