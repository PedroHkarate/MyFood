package br.ufal.ic.p2.myfood;

import java.util.ArrayList;
import br.ufal.ic.p2.myfood.Exceptions.*;

public class Sistema {

    /*public Sistema(String nome, String email, String senha, String endereco) {
        super(nome, email, senha, endereco);
    }*/

    private final Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Produto> produtos;
    private ArrayList<String> secoesAtivas;

    public Sistema(){
        this.usuario = new Usuario(0, "", "", "", "");
        usuarios = new ArrayList<>();
        restaurantes = new ArrayList<>();
        produtos = new ArrayList<>();
        secoesAtivas = new ArrayList<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        this.restaurantes.clear();
        this.produtos.clear();
        secoesAtivas.clear();
    }

    // possivel solução pra não usar parametros estaticos (36-74), as funções originais estão comentadas em baixo

    public String criarUsuario(String nome, String email, String senha, String endereco)
            throws NomeInvalidoException, EmailInvalidoException,
            SenhaInvalidaException, EnderecoInvalidoException {

        if (nome == null) throw new NomeInvalidoException();
        if (email == null) throw new EmailInvalidoException();
        if (senha == null) throw new SenhaInvalidaException();
        if (endereco == null) throw new EnderecoInvalidoException();

        Usuario usuario = new Usuario(usuarios.size() + 1, nome, email, senha, endereco);

        boolean adicionado = usuarios.add(usuario);
        return nome;
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                usuario = u;
                break;
            }
        }
        if (usuario == null) {
            throw new UsuarioNaoCadastradoException();
        }
        if (atributo.equalsIgnoreCase("nome")) {
            return usuario.getNome();
        } else if (atributo.equalsIgnoreCase("email")) {
            return usuario.getEmail();
        } else if (atributo.equalsIgnoreCase("senha")) {
            return usuario.getSenha();
        } else if (atributo.equalsIgnoreCase("endereco")) {
            return usuario.getEndereco();
        } else {
            throw new UsuarioNaoCadastradoException();
        }
    }

/*
    public String criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailInvalidoException,
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
        String usuario1 = String.valueOf(usuarios.add(new Usuario(id, nome, email, senha, endereco)));

        if(usuario1 != null) return usuario1;
        else return null;

        //System.out.println(usuarios);
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException{
        if(usuarios.contains(id)){
            if(atributo == "nome"){
                if(Usuario.getNome() == atributo) atributo = Usuario.getNome();
                else throw new UsuarioNaoCadastradoException();
            }
            if(atributo == "email"){
                if(usuarios.contains(atributo)) atributo = Usuario.getEmail();
                else throw new UsuarioNaoCadastradoException();
            }
            if(atributo == "senha"){
                if(usuarios.contains(atributo)) atributo = Usuario.getSenha();
                else throw new UsuarioNaoCadastradoException();
            }
            if(atributo == "endereco"){
                if(usuarios.contains(atributo)) atributo = Usuario.getEndereco();
                else throw new UsuarioNaoCadastradoException();
            }
            return atributo;
        }
        else throw new UsuarioNaoCadastradoException();
    }
*/

    /*

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException,
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
