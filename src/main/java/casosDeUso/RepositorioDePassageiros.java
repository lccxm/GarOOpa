package casosDeUso;

import casosDeUso.GetRawData;
import entidades.Passageiro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePassageiros {
    private List<Passageiro> passageiros;

    public RepositorioDePassageiros() throws FileNotFoundException {
        this.passageiros = new ArrayList<>();
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
}