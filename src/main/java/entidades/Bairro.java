package entidades;

import entidades.Roteiro.Area;
import entidades.Roteiro.Ponto;

public class Bairro {

    private String nome;
    private Area limites;
    private int custoBasico;

    public Bairro(String nome, Area limites, int custoBasico){
        this.nome = nome;
        this.limites = limites;
        this.custoBasico = custoBasico;
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

    public Ponto getCentro(){
        int x = ((int)limites.getPInfEsq().getX() + (int)limites.getPSupDir().getX())/2;
        int y = ((int)limites.getPInfEsq().getY() + (int)limites.getPSupDir().getY())/2;
        Ponto centro = new Ponto(x,y);
        return centro;
    }
}
