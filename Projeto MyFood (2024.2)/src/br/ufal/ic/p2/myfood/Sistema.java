package br.ufal.ic.p2.myfood;

import java.util.ArrayList;
import java.util.Objects;

import br.ufal.ic.p2.myfood.Exceptions.*;

public class Sistema {
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Produto> produtos;
    private ArrayList<String> secoesAtivas;
    private ArrayList<Empresa> empresas;


    public Sistema(){
        this.usuario = new Usuario("", "", "", "");
        usuarios = new ArrayList<>();
        restaurantes = new ArrayList<>();
        produtos = new ArrayList<>();
        secoesAtivas = new ArrayList<>();
        empresas = new ArrayList<>();
    }


    public void zerarSistema(){
        this.usuarios.clear();
        this.restaurantes.clear();
        this.produtos.clear();
        secoesAtivas.clear();
    }


    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || !email.contains("@")) {
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


    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        if (cpf == null || cpf.length() != 14) {
            throw new CpfInvalidoException();
        }

        for (Usuario usuario2 : usuarios) {
            if (usuario2.getEmail().equals(email)) throw new EmailJaExisteException();
        }

        Usuario usuarioDono = new UsuarioDono(nome, email, senha, endereco, cpf);
        usuarios.add(usuarioDono);
    }


    public int login(String email, String senha) throws Exception {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            throw new LoginOuSenhaInvalidosException();
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario.getId();
            }
        }
        throw new LoginOuSenhaInvalidosException();
    }


    public String getAtributoUsuario(int id, String atributo) throws Exception {

        Usuario usuario1 = null;

        for (Usuario usuario2 : usuarios) {
            if (usuario2.getId() == id) {
                usuario1 = usuario2;
                break;
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
                    if (usuario1 instanceof UsuarioDono){
                        UsuarioDono UsuarioDono = (UsuarioDono) usuario1;
                        return UsuarioDono.getCpf();
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


    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) throws Exception {
        Usuario dono = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == donoId) {
                if (usuario instanceof UsuarioDono) {
                    dono = usuario;
                    break;
                } else {
                    throw new Exception("Usuario nao pode criar uma empresa");
                }
            }
        }

        if (dono == null) {
            throw new UsuarioNaoCadastradoException();
        }

        for (Empresa empresa : empresas) {
            if (empresa.getNome().equals(nome)) {
                Usuario donoEmpresa = getDonoEmpresa(empresa);
                if (donoEmpresa != null && donoEmpresa.getId() != donoId) {
                    throw new EmpresaComMesmoNomeDonoDiferenteException();
                }
                if (donoEmpresa != null && donoEmpresa.getId() == donoId && empresa.getEndereco().equals(endereco)) {
                    throw new EmpresaComMesmoNomeEEnderecoException();
                }
            }
        }
        Empresa novaEmpresa;
        if (tipoEmpresa.equalsIgnoreCase("restaurante")) {
            novaEmpresa = new Restaurante(nome, endereco, tipoCozinha);
        } else {
            throw new TipoEmpresaInvalidoException();
        }
        empresas.add(novaEmpresa);
        return novaEmpresa.getEid();
    }

    private Usuario getDonoEmpresa(Empresa empresa) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof UsuarioDono) {
                return usuario;
            }
        }
        return null;
    }


    public String getEmpresasDoUsuario(int idDono) throws Exception {
        Usuario dono = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idDono && usuario instanceof UsuarioDono) {
                dono = usuario;
                break;
            }
        }
        if (dono == null) {
            throw new UsuarioNaoCadastradoException();
        }
        StringBuilder resultado = new StringBuilder("{[");
        for (Empresa empresa : empresas) {
            if (empresa instanceof Restaurante) {
                if (((Restaurante) empresa).getEid() == idDono) {
                    resultado.append("[").append(empresa.getNome()).append(", ").append(empresa.getEndereco()).append("], ");
                }
            }
        }
        if (resultado.length() > 2) {
            resultado.setLength(resultado.length() - 2);
        }
        resultado.append("]}");
        return resultado.toString();
    }


    public String getAtributoEmpresa(int empresaId, String atributo) throws Exception {
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getEid() == empresaId) {
                empresa = e;
                break;
            }
        }
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        switch (atributo) {
            case "nome":
                return empresa.getNome();
            case "endereco":
                return empresa.getEndereco();
            case "tipoCozinha":
                if (empresa instanceof Restaurante) {
                    return ((Restaurante) empresa).getTipoCozinha();
                } else {
                    throw new AtributoInvalidoException();
                }
            default:
                throw new AtributoInvalidoException();
        }
    }


    public int getIdEmpresa(int idDono, String nome, int indice) throws Exception {
        Usuario dono = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idDono && usuario instanceof UsuarioDono) {
                dono = usuario;
                break;
            }
        }
        if (dono == null) {
            throw new UsuarioNaoCadastradoException();
        }
        ArrayList<Empresa> empresasDoDono = new ArrayList<>();
        for (Empresa empresa : empresas) {
            if (empresa instanceof Restaurante) {
                if (((Restaurante) empresa).getEid() == idDono && empresa.getNome().equals(nome)) {
                    empresasDoDono.add(empresa);
                }
            }
        }
        if (indice < 0 || indice >= empresasDoDono.size()) {
            throw new Exception("Indice invalido");
        }
        return empresasDoDono.get(indice).getEid();
    }
}
