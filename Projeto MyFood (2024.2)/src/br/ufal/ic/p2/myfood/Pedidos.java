package br.ufal.ic.p2.myfood;

public class Pedidos {
    private int numero;
    private String cliente;
    private String empresa;
    private String estado;
    //private produtos;
    private float valor;

    public Pedidos(int numero, String cliente, String empresa, String estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = estado;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}