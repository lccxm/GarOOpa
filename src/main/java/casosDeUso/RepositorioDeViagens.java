package casosDeUso;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entidades.Cidade;
import entidades.Viagem;
import entidades.Roteiro.Ponto;

public class RepositorioDeViagens {
    private static List<Viagem> viagens;

    
    public RepositorioDeViagens() throws FileNotFoundException {
        viagens = new ArrayList<>();
        carregaViagens();
    }

    public static List<Viagem> getViagens() {
        return viagens;
    }

    // To DO: tirar list do contrutor de cidades (inicia vazia)
    // e inserir metodo addBairro 
    private void carregaViagens() throws FileNotFoundException{
        List<String[]> lst = GetRawData.fromCSV("viagens.dat");

        for (String[] data: lst){
            int identificador = Integer.parseInt(data[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(data[1], formatter);
            String bairroOrigem = data[2];
            String bairroDestino = data[3];
            Cidade cidade = new Cidade(data[4]);
            String cpfMotorista = data[5];
            String cpfPassageiro = data[6];
            double custo = Double.parseDouble(data[7]);




            viagens.add(new Viagem(#######));
        }
    }

    public static Viagem getViagemById(int id){
        for (Viagem viagem : viagens)
            if (viagem.getIdentificador() == id)
                return viagem;
        throw new IllegalArgumentException("id inexistente");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new RepositorioDeViagens();
    }
}