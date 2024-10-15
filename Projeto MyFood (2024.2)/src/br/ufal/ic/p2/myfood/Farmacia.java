package br.ufal.ic.p2.myfood;

public class Farmacia extends Empresa {
    private Boolean aberto24Horas;
    private int numeroFuncionarios;

    public Farmacia(int id, String nome, String endereco, Boolean aberto24Horas, int numeroFuncionarios, Usuario dono) {
        super(id, nome, endereco, "farmacia", dono);  // Use "farmacia" como tipo específico

        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public Boolean getAberto24Horas(){ return aberto24Horas; }

    public int getNumeroFuncionarios() { return numeroFuncionarios; }
}
