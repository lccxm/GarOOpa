package casosDeUso.repositorios;

import java.io.FileNotFoundException;



public class Repositorios {

    public Repositorios(){
    }

    public void carregaTodos() throws FileNotFoundException, IllegalArgumentException {
        new RepositorioDePassageiros();
        new RepositorioDeMotoristas();
        new RepositorioDeCidades();
        new RepositorioDeBairros();
        new RepositorioDeViagens();
    }

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
        Repositorios r = new Repositorios();
        r.carregaTodos();
    }
    
}