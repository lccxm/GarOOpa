package entidades;

import java.util.*;

public class Cidade {
    private String nome;
    private ArrayList<Bairro> bairros = new ArrayList<>();

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Bairro> getBairros() {
        return this.bairros;
    }

    public void addBairro(Bairro b){
        bairros.add(b);
    }

    public void removeBairro(Bairro b){
        bairros.remove(b);
    }

    public Bairro getBairroByIndex(int index){
        return bairros.get(index);
    }


    @Override
    public String toString() {
        return "{" +
            " nome='" + nome + "'" +
            ", bairros='" + bairros + "'" +
            "}";
    }

}
