package br.ufal.ic.p2.myfood.Exceptions;

public class NaoEUmMercadoValidoException extends Exception {
    public NaoEUmMercadoValidoException() {
        super("Nao e um mercado valido");
    }
}
