package br.ufal.ic.p2.myfood.Exceptions;

public class EmpresaNaoCadastradaException extends Exception {
    public EmpresaNaoCadastradaException() {
        super("Empresa não cadastrada.");
    }
}
