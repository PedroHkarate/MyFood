package br.ufal.ic.p2.myfood;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private Usuario cliente;
    private Empresa empresa;
    private List<Produto> produtos;
    private String estado;

    public Pedido(int numero, Usuario cliente, Empresa empresa) {
        this.numero = numero;
        this.cliente = cliente;
        this.empresa = empresa;
        this.produtos = new ArrayList<>();
        this.estado = "aberto";
    }

    public int getNumero() {
        return numero;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getEstado() {
        return estado;
    }

    public void adicionarProduto(Produto produto) {
        if (!estado.equals("fechado")) {
            produtos.add(produto);
        }
    }

    public void fechar() {
        estado = "fechado";
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
