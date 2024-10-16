package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorNaoEstaEmNenhumaEmpresaException extends Exception {
    public EntregadorNaoEstaEmNenhumaEmpresaException() {
        super("Entregador nao estar em nenhuma empresa.");
    }
}
