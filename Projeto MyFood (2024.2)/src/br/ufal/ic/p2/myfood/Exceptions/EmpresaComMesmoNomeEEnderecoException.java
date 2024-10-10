package br.ufal.ic.p2.myfood.Exceptions;

public class EmpresaComMesmoNomeEEnderecoException extends Exception {
    public EmpresaComMesmoNomeEEnderecoException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
