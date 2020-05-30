package entidades;

import entidades.Roteiro.Area;
import entidades.Roteiro.Ponto;

public class Bairro {
    
    private String nome;
    private Area limites;
    private int custoBasico;
    private String cidade;

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

    public void setCidade(String c){
        cidade = c;
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
