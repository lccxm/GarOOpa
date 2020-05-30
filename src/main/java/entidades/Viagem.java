package entidades;

import entidades.Roteiro.Roteiro;

import java.time.LocalTime;

public class Viagem {
    private int identificador;
    private LocalTime dataHora;
    private Roteiro roteiro;
    private Motorista motorista;
    private Passageiro passageiro;
    private double custo; 

    public Viagem(int identificador, LocalTime dataHora, Roteiro roteiro, Motorista motorista, Passageiro passageiro, double custo) {
        this.identificador = identificador;
        this.dataHora = dataHora;
        this.roteiro = roteiro;
        this.motorista = motorista;
        this.passageiro = passageiro;
        this.custo = custo;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public LocalTime getDataHora() {
        return this.dataHora;
    }

    public Roteiro getRoteiro() {
        return this.roteiro;
    }

    public Motorista getMotorista() {
        return this.motorista;
    }

    public Passageiro getPassageiro() {
        return this.passageiro;
    }

    public double getCusto() {
        return this.custo;
    }


    @Override
    public String toString() {
        return "{" +
            " identificador='" + identificador + "'" +
            ", dataHora='" + dataHora + "'" +
            ", roteiro='" + roteiro + "'" +
            ", motorista='" + motorista + "'" +
            ", passageiro='" + passageiro + "'" +
            ", custo='" + custo + "'" +
            "}";
    }

}