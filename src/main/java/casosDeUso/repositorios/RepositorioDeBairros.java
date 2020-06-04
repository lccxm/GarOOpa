package casosDeUso.repositorios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidades.Bairro;
import entidades.Cidade;
import entidades.Roteiro.Ponto;
import entidades.Roteiro.Area;

public class RepositorioDeBairros {
    private static List<Bairro> bairros;

    public RepositorioDeBairros() throws FileNotFoundException, IllegalArgumentException {
        bairros = new ArrayList<>();
        carregaBairros();
    }

    public static List<Bairro> getBairros() {
        return bairros;
    }

    public void carregaBairros() throws FileNotFoundException, IllegalArgumentException {
        List<String[]> lst = GetRawData.fromCSV("bairros.dat");
        for (String[] data : lst) {
            String nome = data[0];
            String cidade = data[1];
            int custoBasico = Integer.parseInt(data[2]);
            Ponto pInfEsq = new Ponto(Double.parseDouble(data[3]), Double.parseDouble(data[4]));
            Ponto pSupDir = new Ponto(Double.parseDouble(data[5]), Double.parseDouble(data[6]));
            Area limites = new Area(pInfEsq, pSupDir);
            Bairro b = new Bairro(nome, limites, custoBasico);
            b.setCidade(cidade);
            bairros.add(b);
        }
        pushBairros2Cidades();

    }

    public static void persiste() throws IOException {
        List<String[]> l = new ArrayList<>();
        for (Bairro b: bairros){
            String[] s = {b.getNome(), b.getCidade(), String.valueOf(b.getCustoBasico()), 
                String.valueOf(b.getLimites().getPSupDir().getX()), String.valueOf(b.getLimites().getPSupDir().getY()), 
                String.valueOf(b.getLimites().getPInfEsq().getX()), String.valueOf(b.getLimites().getPInfEsq().getY())};
            l.add(s);
        }
        Writer.toCSV("bairros.dat", l);
    }

    private void pushBairros2Cidades(){
        List<Cidade> cidades = RepositorioDeCidades.getCidades();
        for (Bairro b: bairros){
            for (Cidade c : cidades){
                if (b.getCidade().equals(c.getNome()))
                    c.addBairro(b);
            }
        }
    }

    public static Bairro getBairroByName(String nome) throws IllegalArgumentException {
        for (Bairro bairro : bairros)
            if (bairro.getNome().equals(nome))
                return bairro;
        throw new IllegalArgumentException("Bairro nao cadastrado");
    }

    public static void add(Bairro bairro){
        bairros.add(bairro);
    }


}