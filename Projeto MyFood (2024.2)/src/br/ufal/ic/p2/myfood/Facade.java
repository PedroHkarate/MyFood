package br.ufal.ic.p2.myfood;

import java.io.IOException;
import java.util.List;

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
    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws Exception {
        Usuario usuario = sistema.criarUsuario(nome, email, senha, endereco, veiculo, placa);
        sistema.adicionarUsuario(usuario);
    }

    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) throws Exception {
        return sistema.criarEmpresa(tipoEmpresa, donoId, nome, endereco, tipoCozinha);
    }
    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String abre, String fecha, String tipoMercado) throws Exception {
        return sistema.criarEmpresa(tipoEmpresa, donoId, nome, endereco, abre, fecha, tipoMercado);
    }
    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, Boolean aberto24Horas, int numeroFuncionarios) throws Exception{
        return sistema.criarEmpresa(tipoEmpresa, donoId, nome, endereco, aberto24Horas, numeroFuncionarios);
    }


    public int criarProduto(int empresa, String nome, float valor, String categoria) throws Exception {
        return sistema.criarProduto(empresa, nome, valor, categoria);
    }
    public void adicionarProduto(int numeroPedido, int produtoId) throws Exception {
        sistema.adicionarProduto(numeroPedido, produtoId);
    }
    public int criarPedido(int cliente, int empresa) throws Exception {
        return sistema.criarPedido(cliente, empresa);
    }
    public int criarEntrega(int pedido, int entregador, String destino) throws Exception {
        return sistema.criarEntrega(pedido, entregador, destino);
    }


    public int login(String email, String senha) throws Exception {
        return sistema.login(email, senha);
    }
    public void editarProduto(int produto, String nome, float valor, String categoria) throws Exception {
        sistema.editarProduto(produto, nome, valor, categoria);
    }
    public String listarProdutos(int empresa) throws Exception {
        return sistema.listarProdutos(empresa);
    }
    public void removerProduto(int numeroPedido, String nomeProduto) throws Exception {
        sistema.removerProduto(numeroPedido, nomeProduto);
    }
    public void alterarFuncionamento(int mercadoId, String abre, String fecha) throws Exception {
        sistema.alterarFuncionamento(mercadoId, abre, fecha);
    }
    public void cadastrarEntregador(int empresaId, int entregadorId) throws Exception {
        sistema.cadastrarEntregador(empresaId, entregadorId);
    }
    public int obterPedido(int entregadorId) throws Exception {
        return sistema.obterPedido(entregadorId);
    }
    public void liberarPedido(int numero) throws Exception {
        sistema.liberarPedido(numero);
    }
    public void fecharPedido(int numeroPedido) throws Exception {
        sistema.fecharPedido(numeroPedido);
    }
    public void entregar(int entrega) throws Exception {
        sistema.entregar(entrega);
    }


    public String getEmpresas(int entregadorId) throws Exception {
        List<String> empresas = sistema.getEmpresas(entregadorId);
        return "{" + empresas.toString() + "}";
    }
    public String getEmpresasDoUsuario(int idDono) throws Exception {
        return sistema.getEmpresasDoUsuario(idDono);
    }
    public String getProduto(String nome, int empresa, String atributo) throws Exception {
        return sistema.getProduto(nome, empresa, atributo);
    }
    public String getEntregadores(int empresaId) throws Exception {
        List<String> entregadores = sistema.getEntregadores(empresaId);
        return "{" + entregadores.toString() + "}";
    }
    public String getPedidos(int pedido, String atributo) throws Exception {
        return sistema.getPedidos(pedido, atributo);
    }
    public int getNumeroPedido(int cliente, int empresa, int indice) throws Exception {
        return sistema.getNumeroPedido(cliente, empresa, indice);
    }
    public String getEntrega(int id, String atributo) throws Exception {
        return sistema.getEntrega(id, atributo);
    }


    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return sistema.getAtributoUsuario(id, atributo);
    }
    public String getAtributoEmpresa(int empresaId, String atributo) throws Exception{
        return sistema.getAtributoEmpresa(empresaId, atributo);
    }


    public int getIdEmpresa(int idDono, String nome, int indice) throws Exception {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }
    public int getIdEntrega(int pedido) throws Exception {
        return sistema.getIdEntrega(pedido);
    }


    public void encerrarSistema() throws IOException {
        sistema.salvarUsuarios();
    }
    public Sistema getSistema() {
        return sistema;
    }
}
