package entidades;

import entidades.Roteiro.*;
import entidades.Roteiro.IllegalArgumentException;


public class PrecoViagem {
//    public static void main(String[] args) throws IllegalArgumentException {
//        Cidade poa = new Cidade("Porto Alegre");
//        //criacao de bairros para preencher a cidade
//        Ponto p1 = new Ponto(5,5);
//        Ponto p2 = new Ponto(3,1);
//        Area Ab1 = new Area(p1,p2);
//        Bairro b1 = new Bairro("bairro 1",Ab1,12);
//        Ponto p3 = new Ponto(8,5);
//        Ponto p4 = new Ponto(6,1);
//        Area Ab2 = new Area(p3,p4);
//        Bairro b2 = new Bairro("bairro 2",Ab1,9);
//        Ponto p5 = new Ponto(10,5);
//        Ponto p6 = new Ponto(8,1);
//        Area Ab3 = new Area(p5,p6);
//        Bairro b3 = new Bairro("bairro 3",Ab1,4);
//        Veiculo veiculo = new Veiculo("12345","ferrarri", "vermelho", TipoVeiculo.LUXO);
//        Motorista motorista = new Motorista("25","Cleiton", veiculo);
//        poa.addBairro(b1);
//        poa.addBairro(b2);
//        poa.addBairro(b3);
//
//
//        Roteiro roteiro = new Roteiro(poa,b1,b3);
//        double preco = calculaPreco(roteiro,motorista);
//        System.out.println(preco);
//
//
//    }

    public static double calculaPreco(Roteiro r, Motorista m){
        double preco = 0;
        Ponto centroOrig = r.getBairroOrigem().getCentro();
        Ponto centroDest = r.getBairroDestino().getCentro();
        Reta trajeto = new Reta(centroOrig, centroDest);
        // checar com quais bairros a reta intersecta
        for(int i = 0; i < r.getCidade().getBairros().size(); i++){
            Cohen c = new Cohen(r.getCidade().getBairroByIndex(i).getLimites(),trajeto);
            if(c.cohenSutherlandClip() == SituacaoReta.INTERSECTA){
                preco += r.getCidade().getBairroByIndex(i).getCustoBasico();
            }
        }
        preco = calculaPrecoAdicional(r,m,preco);
        return preco;
    }

    public static double calculaPrecoAdicional(Roteiro r, Motorista m, double preco){
        if(m.getVeiculo().getTipo() == TipoVeiculo.SIMPLES){
            return preco;
        }else if(m.getVeiculo().getTipo() == TipoVeiculo.NORMAL){
            preco += (preco * 0.1);
        }else{
            preco += (preco * 0.1);
            preco +=  preco * ((double)(r.getBairrosInterseccionados().size() * 2)/100);
        }
        return preco;
    }
    

}
