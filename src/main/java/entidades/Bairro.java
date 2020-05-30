package entidades;

import entidades.Roteiro.Area;
import entidades.Roteiro.Ponto;

public class Bairro {

    private String nome;
    private Area limites;
    private int custoBasico;
    private String cidade;

    public Bairro(String nome, Area limites, int custoBasico, String cidade){
        this.nome = nome;
        this.limites = limites;
        this.custoBasico = custoBasico;
        this.cidade = cidade;
    }

    public String getNome(){
        return nome;
    }

    public Area getLimites(){
        return limites;
    }

    public int getCustoBasico(){
        return custoBasico;
    }

    public String geCidade(){
        return cidade;
    }

    public Ponto getCentro(){
        int x = ((int)limites.getPInfEsq().getX() + (int)limites.getPSupDir().getX())/2;
        int y = ((int)limites.getPInfEsq().getY() + (int)limites.getPSupDir().getY())/2;
        return new Ponto(x,y);
    }
}
