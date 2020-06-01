package casosDeUso.repositorios;

import entidades.Passageiro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class RepositorioDePassageiros {
    private static List<Passageiro> passageiros;

    public RepositorioDePassageiros() throws FileNotFoundException {
        passageiros = new ArrayList<>();
        carregaPassageiros();
    }

    public void carregaPassageiros() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("passageiros.dat");
        for (String[] data: lst){
            String cpf = data[0];
            String nome = data[1];
            int somatorioAval = Integer.parseInt(data[2]);
            int qtdAval = Integer.parseInt(data[3]);
            passageiros.add(new Passageiro(cpf, nome, somatorioAval, qtdAval));
        }
    }

    public static Passageiro getPassageiroByCPF(String cpf){
        for (Passageiro passageiro : passageiros)
            if (passageiro.getCpf().equals(cpf))
                return passageiro;
        throw new IllegalArgumentException("passageiro nao existe");
    }

    public static void add(Passageiro passageiro){
        passageiros.add(passageiro);
    }
    public static void main(String[] args) throws FileNotFoundException {
        new RepositorioDePassageiros();
    }
}