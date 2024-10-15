package br.ufal.ic.p2.myfood;

public class Mercado extends Empresa {
    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado(int id, String nome, String endereco, String abre, String fecha, String tipoMercado,Usuario dono) {
        super(id, nome, endereco, "mercado",dono);

        this.tipoMercado = tipoMercado;
        this.abre = abre;
        this.fecha = fecha;
    }

    public String getAbre(){ return abre; }
    public String getFecha(){ return fecha; }
    public String getTipoMercado(){ return tipoMercado; }

}
