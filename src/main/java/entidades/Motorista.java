package entidades;

import java.util.LinkedList;
import java.util.List;

public class Motorista {

    private String cpf;
    private String nome;
    private int somaAvaliacoes;
    private int quantAvaliacoes;
    private Veiculo veiculo;
    private List<FormaPgto> formasPgto;

    public Motorista(String cpf, String nome, Veiculo veiculo){
        this.cpf = cpf;
        this.nome = nome;
        this.veiculo = veiculo;
        this.formasPgto = new LinkedList<>();
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






}
