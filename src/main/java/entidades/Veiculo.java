package entidades;

public class Veiculo {

    private String placa;
    private String marca;
    private String cor;
    private TipoVeiculo tipo;
    private boolean bagageiroG;

    public Veiculo(String placa, String marca, String cor, TipoVeiculo tipo){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.tipo = tipo;
        this.bagageiroG = false;
    }

    public String getPlaca(){
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public String getMarca() {
        return marca;
    }

    public TipoVeiculo getTipo(){
        return tipo;
    }

    public boolean getBagageiro(){
        return bagageiroG;
    }

    public void setBagageiroG(boolean b){
        if (tipo.equals(TipoVeiculo.LUXO) && b)
            bagageiroG = true;
    }


    @Override
    public String toString() {
        return "{" +
            " placa='" + placa + "'" +
            ", marca='" + marca + "'" +
            ", cor='" + cor + "'" +
            ", tipo='" + tipo + "'" +
            ", bagageiroG='" + bagageiroG + "'" +
            "}";
    }

}
