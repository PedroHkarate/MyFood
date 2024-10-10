package br.ufal.ic.p2.myfood.Exceptions;

public class EmpresaComMesmoNomeDonoDiferenteException extends Exception {
  public EmpresaComMesmoNomeDonoDiferenteException() {
    super("Empresa com esse nome ja existe");
  }
}
