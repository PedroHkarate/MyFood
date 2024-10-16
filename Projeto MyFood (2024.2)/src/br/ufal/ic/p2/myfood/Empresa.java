package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.ProdutoNaoCadastradoException;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private int id;
    private String nome;
    private String endereco;
    private String tipo;
    private Usuario dono;
    private List<Produto> produtos;
    private List<UsuarioEntregador> entregadores;

    public Empresa(int id, String nome, String endereco, String tipo, Usuario dono) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.dono = dono;
        this.produtos = new ArrayList<>();
        this.entregadores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public Usuario getDono() {
        return dono;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto getProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }

    public void editarProduto(int produtoId, String nome, float valor, String categoria) throws Exception {
        Produto produto = findProdutoById(produtoId);
        if (produto == null) {
            throw new ProdutoNaoCadastradoException();
        }
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setCategoria(categoria);
    }

    public Produto findProdutoById(int produtoId) {
        for (Produto produto : produtos) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null;
    }

    public List<String> listarProdutos() {
        List<String> nomesProdutos = new ArrayList<>();
        for (Produto produto : produtos) {
            nomesProdutos.add(produto.getNome());
        }
        return nomesProdutos;
    }

    public void adicionarEntregador(UsuarioEntregador entregador) {
        entregadores.add(entregador);
    }

    public List<UsuarioEntregador> getEntregadores() {
        return entregadores;
    }
}
