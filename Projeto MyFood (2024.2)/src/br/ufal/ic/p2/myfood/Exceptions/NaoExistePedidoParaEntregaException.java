package br.ufal.ic.p2.myfood.Exceptions;

public class NaoExistePedidoParaEntregaException extends RuntimeException {
    public NaoExistePedidoParaEntregaException(String message) {
        super(message);
    }
}
