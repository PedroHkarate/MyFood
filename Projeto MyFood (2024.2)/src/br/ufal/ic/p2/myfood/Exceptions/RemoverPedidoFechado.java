package br.ufal.ic.p2.myfood.Exceptions;

public class RemoverPedidoFechado extends Exception {
  public RemoverPedidoFechado() {
    super("Nao e possivel remover produtos de um pedido fechado");
  }
}
