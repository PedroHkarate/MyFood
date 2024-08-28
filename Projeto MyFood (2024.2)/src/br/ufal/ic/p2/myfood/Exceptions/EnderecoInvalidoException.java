package br.ufal.ic.p2.myfood.Exceptions;

public class EnderecoInvalidoException extends Exception{
    public EnderecoInvalidoException(){
        new Exception("Endereco invalido");
    }
}
