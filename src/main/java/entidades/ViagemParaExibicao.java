package entidades;

public class ViagemParaExibicao {
    private Viagem viagem;
    private Motorista motorista;
    private String placa;
    private String marca;
    private double custo;

    public ViagemParaExibicao(Viagem viagem){
        this.viagem = viagem;
        motorista = viagem.getMotorista();
        placa = motorista.getVeiculo().getPlaca();
        marca = motorista.getVeiculo().getMarca();
        custo = PrecoViagem.calculaPreco(viagem.getRoteiro(), motorista);
    }


    public Viagem getViagem() {
        return this.viagem;
    }

    public String getNomeMotorista() {
        return this.motorista.getNome();
    }

    public String getPlaca() {
        return this.placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public double getCusto() {
        return this.custo;
    }

    @Override
    public String toString() {
        return "{" +
            " viagem='" + viagem + "'" +
            ", motorista='" + motorista + "'" +
            ", placa='" + placa + "'" +
            ", marca='" + marca + "'" +
            ", custo='" + custo + "'" +
            "}";
    }



}