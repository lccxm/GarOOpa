package entidades;

public class Veiculo {

    private String placa;
    private String marca;
    private String cor;
    private TipoVeiculo tipo;
    private boolean bagageiroG;

    public Veiculo(String placa, String marca, String cor, TipoVeiculo tipo ){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
        this.tipo = tipo;
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
}
