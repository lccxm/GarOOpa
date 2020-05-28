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
}
