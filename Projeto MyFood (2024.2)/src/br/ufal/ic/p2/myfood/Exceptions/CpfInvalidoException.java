package br.ufal.ic.p2.myfood.Exceptions;

public class CpfInvalidoException extends Exception {
    public CpfInvalidoException() {
        new Exception("CPF invalido");
    }
}
