package br.ufal.ic.p2.myfood;

public class UsuarioEntregador extends Usuario {

    private String veiculo;
    private String placa;

    public UsuarioEntregador(int id, String nome, String email, String senha, String endereco, String veiculo, String placa) {
        super(id, nome, email, senha, endereco, "Entregador");

        this.veiculo = veiculo;
        this.placa = placa;
    }

    public String getVeiculo(){ return veiculo; }
    public String getPlaca(){ return placa; }
}
