package casosDeUso.repositorios;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cidade;

public class RepositorioDeCidades {
    private static List<Cidade> cidades;

    public RepositorioDeCidades() throws FileNotFoundException {
        cidades = new ArrayList<>();
        carregaCidades();
    }

    public static List<Cidade> getCidades() {
        return cidades;
    }

    // To DO: tirar list do contrutor de cidades (inicia vazia)
    // e inserir metodo addBairro 
    private void carregaCidades() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("cidades.dat");
        for (String[] data: lst){
            String nome = data[0];
            cidades.add(new Cidade(nome));
        }
    }

    public static Cidade getCidadeByName(String nome){
        for (Cidade cidade : cidades)
            if (cidade.getNome().equals(nome))
                return cidade;
        throw new IllegalArgumentException("Cidade nao existe");
    }

    public static void add(Cidade cidade){
        cidades.add(cidade);
    }
    public static void main(String[] args) throws FileNotFoundException {
        new RepositorioDeCidades();
    }
}