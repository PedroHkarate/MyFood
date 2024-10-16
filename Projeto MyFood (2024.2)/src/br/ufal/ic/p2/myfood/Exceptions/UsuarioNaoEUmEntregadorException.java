package br.ufal.ic.p2.myfood.Exceptions;

public class UsuarioNaoEUmEntregadorException extends Exception {
    public UsuarioNaoEUmEntregadorException() {
        super("Usuario nao e um entregador");
    }
}
