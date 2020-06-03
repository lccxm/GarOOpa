package casosDeUso.repositorios;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Repositorios {

    public Repositorios() {
    }

    public static void carregaTodos() throws FileNotFoundException, IllegalArgumentException {
        new RepositorioDePassageiros();
        new RepositorioDeMotoristas();
        new RepositorioDeCidades();
        new RepositorioDeBairros();
        new RepositorioDeViagens();
    }

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {

    }

    public static void persiste() throws IOException {
        RepositorioDeBairros.persiste();
        RepositorioDeCidades.persiste();
        RepositorioDeMotoristas.persiste();
        RepositorioDePassageiros.persiste();
        RepositorioDeViagens.persiste();
    }
    
}