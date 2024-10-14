package br.ufal.ic.p2.myfood.Exceptions;

public class ProdutoNaoPertenceAEmpresaException extends Exception {
    public ProdutoNaoPertenceAEmpresaException() {
        super("O produto nao pertence a essa empresa");
    }
}
