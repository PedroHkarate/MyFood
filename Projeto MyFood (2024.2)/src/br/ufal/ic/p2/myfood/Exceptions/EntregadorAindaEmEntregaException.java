package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorAindaEmEntregaException extends Exception {
    public EntregadorAindaEmEntregaException() {
        super("Entregador ainda em entrega");
    }
}
