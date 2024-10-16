package br.ufal.ic.p2.myfood.Exceptions;

public class NaoExisteNadaParaSerEntregueComEsseIdException extends Exception {
    public NaoExisteNadaParaSerEntregueComEsseIdException() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
