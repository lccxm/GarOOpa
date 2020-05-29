package entidades;

public class Veiculo {

    private String placa;
    private String marca;
    private String cor;
    private int tipo;
    private boolean bagageiroG;

    public Veiculo(String placa, String marca, String cor){
        this.placa = placa;
        this.marca = marca;
        this.cor = cor;
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


}
