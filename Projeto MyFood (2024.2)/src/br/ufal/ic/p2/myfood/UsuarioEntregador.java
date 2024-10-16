package br.ufal.ic.p2.myfood;

import java.util.Objects;

public class UsuarioEntregador extends Usuario {

    private String veiculo;
    private String placa;

    public UsuarioEntregador(int id, String nome, String email, String senha, String endereco, String veiculo, String placa) {
        super(id, nome, email, senha, endereco, "Entregador");
        this.veiculo = veiculo;
        this.placa = placa;
    }

    public String getVeiculo(){
        return veiculo;
    }

    public String getPlaca(){
        return placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntregador that = (UsuarioEntregador) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
