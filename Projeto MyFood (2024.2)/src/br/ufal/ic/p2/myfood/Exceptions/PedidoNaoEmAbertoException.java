package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoNaoEmAbertoException extends Exception {
    public PedidoNaoEmAbertoException() {
        super("Nao existe pedido em aberto");
    }
}
