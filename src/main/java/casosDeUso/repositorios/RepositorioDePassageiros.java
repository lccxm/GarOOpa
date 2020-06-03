package casosDeUso.repositorios;

import entidades.Passageiro;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static void persistePassageiros() throws IOException {
        List<String[]> l = new ArrayList<>();
        for (Passageiro p: passageiros){
            String[] s = {p.getNome(),p.getNome(), String.valueOf(p.getSomatorioAval()), String.valueOf(p.getQtdAval())};
            for (int i=0; i<s.length; i++){
                System.out.println(s[i]);
            }
            l.add(s);
        }
        System.out.println(l.toString());
        Writer.toCSV("passageiros.dat", l);
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