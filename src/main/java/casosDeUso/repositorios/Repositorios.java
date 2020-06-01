package casosDeUso.repositorios;

import java.io.FileNotFoundException;

import entidades.Roteiro.IllegalArgumentException;

public class Repositorios {

    public static void carregaTodos() throws FileNotFoundException, IllegalArgumentException {
        new RepositorioDePassageiros();
        new RepositorioDeMotoristas();
        new RepositorioDeCidades();
        new RepositorioDeBairros();
        new RepositorioDeViagens();
    }
    
}