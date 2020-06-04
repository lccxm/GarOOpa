package casosDeUso.repositorios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entidades.*;

public class RepositorioDeMotoristas {
    private static List<Motorista> motoristas;

    public RepositorioDeMotoristas() throws FileNotFoundException {
        motoristas = new ArrayList<>();
        carregaMotoristas();
    }

    public List<Motorista> getMotoristas(){
        return motoristas;
    }

    public void carregaMotoristas() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("motoristas.dat");
        for (String[] data: lst){
            String cpf = data[0];
            String nome = data[1];
            int somatorioAval = Integer.parseInt(data[2]);
            int qtdAval = Integer.parseInt(data[3]);
            String[] aFormaPgto = data[4].split("_");
            List<FormaPgto> formasPgto = new LinkedList<>();
            for (String text: aFormaPgto){
                formasPgto.add(FormaPgto.valueOf(text));
            }
            String veiculoPlaca = data[5];
            String veiculoMarca= data[6];
            String veiculoCor = data[7];
            TipoVeiculo tipo = TipoVeiculo.valueOf(data[8]);
            boolean bagageiroG = Integer.parseInt(data[9]) == 1; // add no construtor
            Veiculo v = new Veiculo(veiculoPlaca, veiculoMarca, veiculoCor, tipo); 
            v.setBagageiroG(bagageiroG);
            Motorista motorista = new Motorista(cpf, nome, v, somatorioAval, qtdAval);
            motorista.setFormasPgto(formasPgto);
            motoristas.add(motorista);

        }
    }


    public static Motorista getMotoristaByCPF(String cpf){
        for (Motorista motorista : motoristas)
            if (motorista.getCpf().equals(cpf))
                return motorista;
        throw new IllegalArgumentException("motorista nao existe");
    }

    public static void persiste() throws IOException {
        List<String[]> l = new ArrayList<>();
        for (Motorista m: motoristas){
            String a = "";
            for (FormaPgto f: m.getFormasPgto())
                a += f+"_";
            String[] s = {m.getCpf(), m.getNome(), String.valueOf(m.getNota()), String.valueOf(m.getQuantAvaliacoes()), a, String.valueOf(m.getVeiculo().getPlaca()), String.valueOf(m.getVeiculo().getMarca()), String.valueOf(m.getVeiculo().getCor()), String.valueOf(m.getVeiculo().getTipo()), String.valueOf(m.getVeiculo().getBagageiro())};
            l.add(s);
        }
        Writer.toCSV("motoristas.dat", l);
    }

    public static void add(Motorista motorista){
        motoristas.add(motorista);
    }


}