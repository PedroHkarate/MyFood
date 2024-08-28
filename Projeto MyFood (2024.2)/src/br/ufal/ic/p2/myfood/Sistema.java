package br.ufal.ic.p2.myfood;

import java.util.ArrayList;
import br.ufal.ic.p2.myfood.Exceptions.*;

public class Sistema {

    /*public Sistema(String nome, String email, String senha, String endereco) {
        super(nome, email, senha, endereco);
    }*/

    private ArrayList<Usuario> usuarios;
    private ArrayList<String> secoesAtivas;

    public Sistema(){
        usuarios = new ArrayList<>();
        secoesAtivas = new ArrayList<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        secoesAtivas.clear();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailInvalidoException,
            SenhaInvalidaException, EnderecoInvalidoException{
        int id = Usuario.getId();
        if(!usuarios.contains(nome)){
            if(nome == null) throw new NomeInvalidoException();
            else nome = Usuario.getNome();
        }
        if(!usuarios.contains(email)){
            if(email == null) throw new EmailInvalidoException();
            else email = Usuario.getEmail();
        }
        if(!usuarios.contains(senha)){
            if(senha == null) throw new SenhaInvalidaException();
            else senha = Usuario.getSenha();
        }
        if(!usuarios.contains(endereco)){
            if(endereco == null) throw new EnderecoInvalidoException();
            else endereco = Usuario.getEndereco();
        }
        usuarios.add(new Usuario(id, nome, email, senha, endereco));

        System.out.println(usuarios);
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException{
        if(usuarios.contains(id)){
            if(atributo == "nome"){
                if(usuarios.contains(atributo)) atributo = Usuario.getNome();
            }
            if(atributo == "email"){
                if(usuarios.contains(atributo)) atributo = Usuario.getEmail();
            }
            if(atributo == "senha"){
                if(usuarios.contains(atributo)) atributo = Usuario.getSenha();
            }
            if(atributo == "endereco"){
                if(usuarios.contains(atributo)) atributo = Usuario.getEndereco();
            }
            return atributo;
        }
        else return null;
    }

    /*public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException,
            SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException{
        if(!usuarios.contains(nome)){
            if(nome == null) throw new NomeInvalidoException();
        }
        if(!usuarios.contains(email)){
            if(email == null) throw new EmailInvalidoException();
        }
        if(!usuarios.contains(senha)){
            if(senha == null) throw new SenhaInvalidaException();
        }
        if(!usuarios.contains(endereco)){
            if(endereco == null) throw new EnderecoInvalidoException();
        }
        if(!usuarios.contains(cpf)){
            if(cpf == null) throw new CpfInvalidoException();
        }

    }*/

}
