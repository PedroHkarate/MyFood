package br.ufal.ic.p2.myfood.Exceptions;

public class NaoEUmEntregadorValidoException extends Exception {
    public NaoEUmEntregadorValidoException() {
        super("Nao e um entregador valido");
    }
}
