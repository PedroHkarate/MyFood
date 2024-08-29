package br.ufal.ic.p2.myfood;

public class Pedidos {
    private static int numero;
    private static String cliente;
    private static String empresa;
    private static String estado;
    //private static  produtos;
    //private static float valor;

    public Pedidos(int numero, String cliente, String empresa, String estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = estado;
    }

    public static int getNumero(){
        return numero;
    }

    public static String getCliente() {
        return cliente;
    }

    public static float getEmpresa() {
        return empresa;
    }

    public static String getEstado() {
        return estado;
    }
}