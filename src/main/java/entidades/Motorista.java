package entidades;

import java.util.LinkedList;
import java.util.List;

public class Motorista {
//    public static void main(String[] args){
//        Veiculo veiculo = new Veiculo("a","b","azul", TipoVeiculo.LUXO);
//        Motorista motorista = new Motorista("1","carlos",veiculo);
//        motorista.atualizaNota(7);
//        System.out.println(motorista.getNota());
//        motorista.atualizaNota(7);
//        System.out.println(motorista.getNota());
//
//    }

    private String cpf;
    private String nome;
    private int somaAvaliacoes;
    private int quantAvaliacoes;
    private Veiculo veiculo;
    private List<FormaPgto> formasPgto;
    private int nota;

    public Motorista(String cpf, String nome, Veiculo veiculo, int somaAvaliacoes, int quantAvaliacoes){
        this.cpf = cpf;
        this.nome = nome;
        this.veiculo = veiculo;
        this.formasPgto = new LinkedList<>();
        this.somaAvaliacoes = somaAvaliacoes;
        this.quantAvaliacoes = quantAvaliacoes;
        atualizaNota();
    }


    public String getCpf(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }

    public Veiculo getVeiculo(){
        return veiculo;
    }


    public List<FormaPgto> getFormasPgto() {
        return this.formasPgto;
    }

    public void setFormasPgto(List<FormaPgto> formasPgto) {
        this.formasPgto = formasPgto;
    }

    public void atualizaNota(int avaliacao){
        if(avaliacao >= 0 && avaliacao <= 10) {
            somaAvaliacoes += avaliacao;
            quantAvaliacoes += 1;
            nota = (int) (somaAvaliacoes / quantAvaliacoes);
        }
    }

    private void atualizaNota(){
        nota = (int) (somaAvaliacoes / quantAvaliacoes);
    }

    public int getNota(){
        return nota;
    }


    @Override
    public String toString() {
        return "{" +
            " cpf='" + cpf + "'" +
            ", nome='" + nome + "'" +
            ", somaAvaliacoes='" + somaAvaliacoes + "'" +
            ", quantAvaliacoes='" + quantAvaliacoes + "'" +
            ", veiculo='" + veiculo + "'" +
            ", formasPgto='" + formasPgto + "'" +
            ", nota='" + nota + "'" +
            "}";
    }



}
