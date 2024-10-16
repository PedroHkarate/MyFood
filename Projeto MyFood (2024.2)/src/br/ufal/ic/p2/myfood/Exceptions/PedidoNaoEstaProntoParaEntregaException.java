package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoNaoEstaProntoParaEntregaException extends Exception {
    public PedidoNaoEstaProntoParaEntregaException() {
        super("Pedido nao esta pronto para entrega");
    }
}
