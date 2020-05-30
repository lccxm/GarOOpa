import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RepositorioDeBairros {
    private List<Bairro> bairros;

    public RepositorioDeBairros() throws FileNotFoundException {
        this.bairros = new ArrayList<>();
        carregaBairros();
    }

    
    public void carregaBairros() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("bairros.dat");
        for (String[] data: lst){
            String nome = data[0];
            String cidade = data[1];
            int custoBasico = Integer.parseInt(data[2]);
            //limites (p1.x, p1.y, p2.x, p2.y)
            Ponto pInfEsq = new Ponto(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            Ponto pSupDir = new Ponto(Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            Area limites = new Area(pInfEsq, pSupDir);
            bairros.add(new Bairro(nome, limites, custoBasico));
            List<RepositorioDeCidades> cidades = RepositorioDeCidades.getCidades();
            Stream<RepositorioDeCidades> stream = cidades.stream();
            stream.filter(cidade -> cidade.)
        }
    }
}