package br.ufal.ic.p2.myfood.Exceptions;

public class AdicionarPedidoFechado extends Exception {
    public AdicionarPedidoFechado() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
