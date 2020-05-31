package casosDeUso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import entidades.Viagem;

public class RepositorioDeViagens {
    private static List<Viagem> viagens;

    
    public RepositorioDeViagens() throws FileNotFoundException {
        viagens = new ArrayList<>();
        carregaCidades();
    }

    public static List<Viagem> getViagens() {
        return viagens;
    }

    // To DO: tirar list do contrutor de cidades (inicia vazia)
    // e inserir metodo addBairro 
    private void carregaCidades() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("cidades.dat");
        for (String[] data: lst){
            String nome = data[0];
            viagens.add(new Viagem(#######));
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        new RepositorioDeViagens();
    }
}