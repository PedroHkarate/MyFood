package br.ufal.ic.p2.myfood;

public class Mercado extends Empresa {
    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado(String nome, String endereco, String abre, String fecha, String tipoMercado) {
        super(nome, endereco);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    public String getAbre() {
        return abre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoMercado() {
        return tipoMercado;
    }
}
