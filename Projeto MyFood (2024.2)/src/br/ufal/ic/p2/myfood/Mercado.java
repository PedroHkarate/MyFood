package br.ufal.ic.p2.myfood;

public class Mercado extends Empresa {
    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado(int id, String nome, String endereco, String abre, String fecha, String tipoMercado, Usuario dono) {
        super(id, nome, endereco, "mercado", dono);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    public String getAbre() {
        return abre;
    }

    public void setAbre(String abre) {
        this.abre = abre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoMercado() {
        return tipoMercado;
    }

    public void setHorarioFuncionamento(String abre, String fecha) {
        // Atualiza os mesmos atributos "abre" e "fecha"
        this.abre = abre;
        this.fecha = fecha;
    }
}
