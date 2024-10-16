package br.ufal.ic.p2.myfood.Exceptions;

public class NaoEMercadoValidoException extends Exception {
    public NaoEMercadoValidoException() {
        super("Nao e um mercado valido");
    }
}
