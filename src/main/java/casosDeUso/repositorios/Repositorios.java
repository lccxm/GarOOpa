package casosDeUso.repositorios;

import java.io.FileNotFoundException;



public class Repositorios {

    public Repositorios(){
    }

    public void carregaTodos() throws FileNotFoundException, IllegalArgumentException {
        System.out.println("carr");
        new RepositorioDePassageiros();
        new RepositorioDeMotoristas();
        new RepositorioDeCidades();
        new RepositorioDeBairros();
        new RepositorioDeViagens();
    }

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
        System.out.println("aa");
        Repositorios r = new Repositorios();
        r.carregaTodos();
    }
    
}