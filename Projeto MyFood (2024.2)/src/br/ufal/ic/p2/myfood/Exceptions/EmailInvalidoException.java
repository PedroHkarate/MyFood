package br.ufal.ic.p2.myfood.Exceptions;

public class EmailInvalidoException extends Exception{
    public EmailInvalidoException(){
        new Exception("email invalido");
    }
}
