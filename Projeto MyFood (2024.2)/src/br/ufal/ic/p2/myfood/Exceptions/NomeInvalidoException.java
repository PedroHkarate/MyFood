package br.ufal.ic.p2.myfood.Exceptions;

public class NomeInvalidoException extends Exception{
    public NomeInvalidoException(){
        new Exception("Nome invalido");
    }

}
