package casosDeUso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import entidades.Motorista;
import entidades.TipoVeiculo;
import entidades.Veiculo;

public class RepositorioDeMotoristas {
    private List<Motorista> motoristas;

    public RepositorioDeMotoristas() throws FileNotFoundException {
        this.motoristas = new ArrayList<>();
        carregaMotoristas();
    }
    // Motorista(String cpf, String nome, Veiculo veiculo){
    // colocar em Motorista outros campos (forma de pagamento???)
    // colocar oustro campos no construtor de vaiculo
    public void carregaMotoristas() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("motoristas.dat");
        for (String[] data: lst){
            String cpf = data[0];
            String nome = data[1];
            int somatorioAval = Integer.parseInt(data[2]);
            int qtdAval = Integer.parseInt(data[3]);
            // FOMADEPAGAMENTO = data[4];??
            String veiculoPlaca = data[5];
            String veiculoMarca= data[6];
            String veiculoCor = data[7];
            TipoVeiculo tipo = TipoVeiculo.valueOf(data[8]);
            boolean bagageiroG = Integer.parseInt(data[9]) == 1; // add no construtor
            Veiculo v = new Veiculo(veiculoPlaca, veiculoMarca, veiculoCor, tipo); 
            motoristas.add(new Motorista(cpf, nome, v));
        }
    }
}