package br.ufal.ic.p2.myfood.Exceptions;

public class DoisPedidosEmAberto extends Exception {
    public DoisPedidosEmAberto() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
