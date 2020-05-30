package casosDeUso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import entidades.Bairro;
import entidades.Cidade;
import entidades.Roteiro.Ponto;
import entidades.Roteiro.Area;
import entidades.Roteiro.IllegalArgumentException;

public class RepositorioDeBairros {
    private List<Bairro> bairros;

    public RepositorioDeBairros() throws FileNotFoundException, IllegalArgumentException {
        this.bairros = new ArrayList<>();
        carregaBairros();
    }

    public void carregaBairros() throws FileNotFoundException, IllegalArgumentException {
        List<String[]> lst = GetRawData.fromCSV("fileName");
        for (String[] data: lst){
            String nome = data[0];
            String cidade = data[1];
            int custoBasico = Integer.parseInt(data[2]);
            Ponto pInfEsq = new Ponto(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            Ponto pSupDir = new Ponto(Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            Area limites = new Area(pInfEsq, pSupDir);
            bairros.add(new Bairro(nome, limites, custoBasico));
        }
        pushBairros2Cidades();

    }

    private void pushBairros2Cidades(){
        List<Cidade> cidades = RepositorioDeCidades.getCidades();
        for (Bairro b: bairros){
            for (Cidade cidade : cidades){
                if (b.geCidade().equals(cidade.getNome()))
                    cidade.addBairro(b);
            }
        }
    }
}