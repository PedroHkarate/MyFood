package br.ufal.ic.p2.myfood.Exceptions;

public class NaoEPossivelLiberarUmProdutoQueNaoEstaSendoPreparadoException extends Exception {
  public NaoEPossivelLiberarUmProdutoQueNaoEstaSendoPreparadoException() {
    super("Nao e possivel liberar um produto que nao esta sendo preparado");
  }
}
