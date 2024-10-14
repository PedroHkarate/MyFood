package br.ufal.ic.p2.myfood;

import java.io.IOException;

public class Facade {
    private Sistema sistema;

    public Facade() {
        try {
            this.sistema = Sistema.carregarDados("users.json");
        } catch (IOException e) {
            this.sistema = new Sistema();
        }
    }

    public void zerarSistema() {
        sistema.zerarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        Usuario usuario = sistema.criarUsuario(nome, email, senha, endereco);
        sistema.adicionarUsuario(usuario);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        Usuario usuario = sistema.criarUsuario(nome, email, senha, endereco, cpf);
        sistema.adicionarUsuario(usuario);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return sistema.getAtributoUsuario(id, atributo);
    }

    public int login(String email, String senha) throws Exception {
        return sistema.login(email, senha);
    }

    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) throws Exception {
        return sistema.criarEmpresa(tipoEmpresa, donoId, nome, endereco, tipoCozinha);
    }

    public String getEmpresasDoUsuario(int idDono) throws Exception {
        return sistema.getEmpresasDoUsuario(idDono);
    }

    public String getAtributoEmpresa(int empresaId, String atributo) throws Exception {
        return sistema.getAtributoEmpresa(empresaId, atributo);
    }

    public int getIdEmpresa(int idDono, String nome, int indice) throws Exception {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }

    public void encerrarSistema() throws IOException {
        sistema.salvarUsuarios();
    }

    public Sistema getSistema() {
        return sistema;
    }
}
