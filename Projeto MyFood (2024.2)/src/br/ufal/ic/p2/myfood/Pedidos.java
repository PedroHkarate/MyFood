package br.ufal.ic.p2.myfood;

public class Pedidos {
    private static int numero;
    private static String cliente;
    private static String empresa;
    private static String estado;
    //private static  produtos;
    private static float valor;

    public Pedidos(int numero, String cliente, String empresa, String estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = estado;
        this.valor = valor;
    }

    public static int getNumero() {
        return numero;
    }

    public static void setNumero(int numero) {
        Pedidos.numero = numero;
    }

    public static String getCliente() {
        return cliente;
    }

    public static void setCliente(String cliente) {
        Pedidos.cliente = cliente;
    }

    public static String getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(String empresa) {
        Pedidos.empresa = empresa;
    }

    public static String getEstado() {
        return estado;
    }

    public static void setEstado(String estado) {
        Pedidos.estado = estado;
    }

    public static float getValor() {
        return valor;
    }

    public static void setValor(float valor) {
        Pedidos.valor = valor;
    }
}