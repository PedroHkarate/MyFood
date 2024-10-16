package br.ufal.ic.p2.myfood;

import java.util.List;

public class Entrega {
    private int id;
    private String cliente;
    private String empresa;
    private int pedido;
    private int entregador;
    private String destino;
    private List<String> produtos;

    public Entrega(int id, String cliente, String empresa, int pedido, int entregador, String destino, List<String> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.empresa = empresa;
        this.pedido = pedido;
        this.entregador = entregador;
        this.destino = destino != null ? destino : cliente;  // Caso destino seja null, usa o endereço do cliente
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getPedido() {
        return pedido;
    }

    public int getEntregador() {
        return entregador;
    }

    public String getDestino() {
        return destino;
    }

    public List<String> getProdutos() {
        return produtos;
    }
}
