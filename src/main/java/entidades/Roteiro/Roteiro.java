package entidades.Roteiro;

import entidades.Bairro;
import entidades.Cidade;

import java.util.ArrayList;


public class Roteiro {
    private Cidade cidade;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;

    public Roteiro(Cidade cidade, Bairro bairroOrigem, Bairro bairroDestino) {
        this.cidade = cidade;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public Bairro getBairroOrigem() {
        return this.bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return this.bairroDestino;
    }

    public ArrayList<Bairro> getBairrosInterseccionados() {
        ArrayList<Bairro> bairrosIntersec = new ArrayList<>();
        Ponto c1 = bairroOrigem.getCentro();
        Ponto c2 = bairroDestino.getCentro();
        Reta reta = new Reta(c1,c2);
        for (int i = 0; i < cidade.getBairros().size(); i++) {
            Cohen c = new Cohen(cidade.getBairroByIndex(i).getLimites(),reta);
            if(c.cohenSutherlandClip() == SituacaoReta.INTERSECTA){
                bairrosIntersec.add(cidade.getBairroByIndex(i));
            }
        }
        return bairrosIntersec;
    }


    @Override
    public String toString() {
        return "{" +
            " cidade='" + cidade + "'" +
            ", bairroOrigem='" + bairroOrigem + "'" +
            ", bairroDestino='" + bairroDestino + "'" +
            "}";
    }

}