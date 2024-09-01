package br.ufal.ic.p2.myfood;

import java.util.ArrayList;
import java.util.Objects;

import br.ufal.ic.p2.myfood.Exceptions.*;

public class Sistema {

    /*public Sistema(String nome, String email, String senha, String endereco) {
        super(nome, email, senha, endereco);
    }*/

    //conferir se as linhas 14-23 estão certas

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Produto> produtos;
    private ArrayList<String> secoesAtivas;

    public Sistema(){
        this.usuario = new Usuario("", "", "", "");
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

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception{
        Usuario usuario1 = new UsuarioCliente(nome, email, senha, endereco);
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null) {
            throw new EmailInvalidoException();
        }
        if (!email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }

        for (Usuario usuario2 : usuarios) {
            if (usuario2.getEmail().equals(email)) throw new EmailJaExisteException();
        }

        Usuario usuarioC = new UsuarioCliente(nome, email, senha, endereco);
        usuarios.add(usuarioC);

    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception{
        Usuario usuario1 = new UsuarioCliente(nome, email, senha, endereco);
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null) {
            throw new EmailInvalidoException();
        }
        if (!email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        if (cpf == null || cpf.length() != 14){
            throw new CpfInvalidoException();
        }

        for (Usuario usuario2 : usuarios) {
            if (usuario2.getEmail().equals(email)) throw new EmailJaExisteException();
        }

        Usuario usuarioD = new UsuarioDono(nome, email, senha, endereco, cpf);
        usuarios.add(usuarioD);

    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {

        Usuario usuario1 = null;

        for (Usuario usuario2 : usuarios) {
            if (usuario2.getId() == id) {
                usuario1 = usuario2;
                break; // Podemos sair do loop assim que encontrarmos o usuário
            }
        }

        if (usuario1 != null) {
            switch (atributo) {
                case "nome":
                    return usuario1.getNome();
                case "email":
                    return usuario1.getEmail();
                case "senha":
                    return usuario1.getSenha();
                case "endereco":
                    return usuario1.getEndereco();
                case "cpf":
                    if (usuario1.getTemCpf()){
                        UsuarioDono usuarioD = (UsuarioDono) usuario1;
                        return usuarioD.getCpf();
                    } else {
                        throw new AtributoInvalidoException();
                    }
                default:
                    throw new AtributoInvalidoException();
            }
        } else {
            throw new UsuarioNaoCadastradoException();
        }
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
