package apresentacao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import casosDeUso.repositorios.RepositorioDeBairros;
import casosDeUso.repositorios.RepositorioDeMotoristas;
import casosDeUso.repositorios.RepositorioDeViagens;
import casosDeUso.repositorios.Repositorios;
import casosDeUso.servicos.ServicosDoPassageiro;
import casosDeUso.servicos.ViagemParaExibicao;
import entidades.FormaPgto;
import entidades.Motorista;
import entidades.TipoVeiculo;
import entidades.Viagem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class App extends Application implements EventHandler<ActionEvent> {
    // private CadastroProduto cadProd;
    // private Cart cart;

    // private ComboBox<Produto> cbProdutos;
    private TextField cpf, bairroOrigem, bairroDestino, tfTotal, MdataViagem, MbairroOrigem, MbairroDestino, MCusto, MAvaliacao;
    private TextField tfDesconto, tfTaxaEntrega, tfICMS, tfISSQN, tfTotalPagar, nomeMotorista, placaVeiculo,
            marcaVeiculo, custoCorrida, pontMotorista, nomeMot, cpfMot;
    private TextArea taCart;
    private ComboBox<FormaPgto> cbxFormaPgto;
    private ComboBox<TipoVeiculo> cbxTipoVeiculo;
    private ViagemParaExibicao viagem;
    private ServicosDoPassageiro sp;
    private List<Viagem> viagens;
    private ComboBox<String> cbViagens;

    public static HBox criaCampoTexto(String label, TextField tf) {
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_LEFT);
        hb.getChildren().add(new Label(label));
        hb.getChildren().add(tf);
        return hb;
    }

    public void atualizaCampos() {
        nomeMotorista.setText(viagem.getNomeMotorista());
        placaVeiculo.setText(viagem.getPlaca());
        marcaVeiculo.setText(viagem.getMarca());
        custoCorrida.setText(String.valueOf(viagem.getCusto()));
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        Repositorios.carregaTodos();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Define o título do form
        Text tfTitulo = new Text("Garoopa");
        tfTitulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tfTitulo, 0, 0, 2, 1);

        // Define o painel de seleção de produtos
        GridPane topGrid = new GridPane();
        topGrid.setAlignment(Pos.BASELINE_LEFT);
        topGrid.setHgap(4);
        topGrid.setVgap(6);

        // Define o título da secçao
        Label titSolicita = new Label("Dados da solicitacao: ");
        titSolicita.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        topGrid.add(titSolicita, 1, 1);

        // add campo cpf
        topGrid.add(new Label("CPF "), 1, 2);
        cpf = new TextField();
        topGrid.add(cpf, 2, 2);

        // add campo bairroOrigem
        topGrid.add(new Label("Bairro de origem: "), 1, 3);
        bairroOrigem = new TextField();
        topGrid.add(bairroOrigem, 2, 3);

        // add campo bairroDestino
        topGrid.add(new Label("Bairro destino: "), 1, 4);
        bairroDestino = new TextField();
        topGrid.add(bairroDestino, 2, 4);

        // Monta lista de formas de pagamento
        cbxFormaPgto = new ComboBox<>();
        cbxFormaPgto.getItems().setAll(FormaPgto.values());
        topGrid.add(cbxFormaPgto, 2, 5);
        topGrid.add(new Label("Forma de pagamento: "), 1, 5);

        // Monta lista de tips de veiculo
        cbxTipoVeiculo = new ComboBox<>();
        cbxTipoVeiculo.getItems().setAll(TipoVeiculo.values());
        topGrid.add(cbxTipoVeiculo, 2, 6);
        topGrid.add(new Label("Tipo de veiculo: "), 1, 6);

        // botao buscar motorista
        Button btBuscarMotorista = new Button("Buscar motorista");
        btBuscarMotorista.setOnAction(e -> {
            try {
                this.trataBotaoBuscaMot(e);
            } catch (FileNotFoundException | IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        // btBuscarMotorista.setOnAction(this);
        topGrid.add(btBuscarMotorista, 3, 7);

        // Define o título da secçao Dados da Viagem
        Label titViagem = new Label("Dados da viagem: ");
        titViagem.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        topGrid.add(titViagem, 1, 8);

        // campo nome do mootrista
        topGrid.add(new Label("Nome do motorista: "), 1, 9);
        nomeMotorista = new TextField();
        topGrid.add(nomeMotorista, 2, 9);

        // campo placa
        topGrid.add(new Label("Placa do veiculo: "), 1, 10);
        placaVeiculo = new TextField();
        topGrid.add(placaVeiculo, 2, 10);

        // campo marca
        topGrid.add(new Label("Marca do veiculo: "), 1, 11);
        marcaVeiculo = new TextField();
        topGrid.add(marcaVeiculo, 2, 11);

        // campo custo da corrida
        topGrid.add(new Label("Custo da corrida: "), 1, 12);
        custoCorrida = new TextField();
        topGrid.add(custoCorrida, 2, 12);

        // botao Finalizar Corrida
        Button btFinalizarCorrida = new Button("Finalizar corrida");
        btFinalizarCorrida.setOnAction(e -> {
            try {
                this.trataBotaoFinalizaCorrida(e);
            } catch (IOException | IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        topGrid.add(btFinalizarCorrida, 3, 13);

        // Define o título da secçao Avaliar motorista
        Label titAvalMotorista = new Label("Avaliar motorista: ");
        titAvalMotorista.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        topGrid.add(titAvalMotorista, 1, 14);

        // campo aval motorista
        topGrid.add(new Label("Avaliar motorista: "), 1, 15);
        pontMotorista = new TextField();
        topGrid.add(pontMotorista, 2, 15);

        // botao Ok (aval motorista)
        Button btOkAvalMotorista = new Button("Ok");
        btOkAvalMotorista.setOnAction(e -> {
            try {
                this.trataBotaoAvaliaMotorista(e);
            } catch (IOException | IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        topGrid.add(btOkAvalMotorista, 3, 16);

        

        /*
         * List<Produto> produtos = new ArrayList<>(); for(ProdutoDTO p:cadProd){
         * produtos.add(EntidadeDTOConverter.Dto2Prod(p)); } // Monta o combo de
         * produtos topGrid.add(new Label("Selecione um produto:"), 0, 1); cbProdutos =
         * new ComboBox<Produto>(FXCollections.observableArrayList(produtos));
         * cbProdutos.getSelectionModel().selectFirst(); topGrid.add(cbProdutos, 0, 2);
         * topGrid.add(new Label("Quantidade:"), 1, 2);
         */

        /*
         * // Botoes Button btAdd = new Button("Add");
         * btAdd.setOnAction(e->this.trataBotaoAdd(e)); Button btRemove = new
         * Button("Remove"); btRemove.setOnAction(e->this.trataBotaoRemove(e)); Button
         * btCheckout = new Button("Checkout");
         * btCheckout.setOnAction(e->this.trataBotaoCheckout(e)); HBox hbBtn = new
         * HBox(); hbBtn.setAlignment(Pos.BOTTOM_RIGHT); hbBtn.getChildren().add(btAdd);
         * hbBtn.getChildren().add(btRemove); hbBtn.getChildren().add(btCheckout);
         * topGrid.add(hbBtn, 0, 3);
         */

        // Define a área de montagem do carrinho
        GridPane cartGrid = new GridPane();
        cartGrid.setAlignment(Pos.BASELINE_LEFT);
        cartGrid.setHgap(4);
        cartGrid.setVgap(6);

        /*
         * taCart = new TextArea(); taCart.setEditable(false); cartGrid.add(taCart,0,0);
         */

        // Adiciona campos texto
        /*
         * tfTotal = new TextField(); cartGrid.add(criaCampoTexto("Total:",
         * tfTotal),0,2); tfDesconto = new TextField();
         * cartGrid.add(criaCampoTexto("Desconto:", tfDesconto),0,3); tfTaxaEntrega =
         * new TextField(); cartGrid.add(criaCampoTexto("Taxa de entrega:",
         * tfTaxaEntrega),0,4); tfICMS = new TextField();
         * cartGrid.add(criaCampoTexto("ICMS:", tfICMS),0,5); tfISSQN = new TextField();
         * cartGrid.add(criaCampoTexto("ISSQN:", tfISSQN),0,6); tfTotalPagar = new
         * TextField(); cartGrid.add(criaCampoTexto("Total a pagar:",
         * tfTotalPagar),0,7);;
         */

        // Adiciona os paineis da direita e da esquerda no grid básico
        grid.add(topGrid, 0, 2);
        grid.add(cartGrid, 0, 3);

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 800, 550);
        primaryStage.setTitle("Garoopa");
        primaryStage.setScene(scene);
        primaryStage.show();

        
        GridPane outroGrid = new GridPane();
        outroGrid.setAlignment(Pos.BASELINE_LEFT);
        outroGrid.setHgap(4);
        outroGrid.setVgap(6);

        Label titOutro = new Label("Tela do Motorista: ");
        titOutro.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        outroGrid.add(titOutro, 1, 1);

        
        outroGrid.add(new Label("CPF do motorista: "), 1, 2);
        cpfMot = new TextField();
        outroGrid.add(cpfMot, 2, 2);

        Button buscarMot = new Button("Buscar");
        outroGrid.add(buscarMot, 2, 3);
        buscarMot.setOnAction(e -> {
            try {
                this.trataBtBuscarMot(e);
            } catch (IOException | IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }); 

        outroGrid.add(new Label("Nome: "), 1, 4);
        nomeMot = new TextField();
        outroGrid.add(nomeMot, 2, 4);

        cbViagens = new ComboBox<String>();
        
        //cbViagens.getItems().addAll(viagens);
        outroGrid.add(cbViagens, 2, 5);
        outroGrid.add(new Label("Selecione a viagem: "), 1, 6);

        Button btgetViagem = new Button("Escolher viagem");
        outroGrid.add(btgetViagem, 2, 6);
        btgetViagem.setOnAction(e -> {
            try {
                this.trataBtGetViagem(e);
            } catch (IOException | IllegalArgumentException e1) {
                e1.printStackTrace();
            }
        }); 

        outroGrid.add(new Label("Data: "), 1, 7);
        MdataViagem = new TextField();
        outroGrid.add(MdataViagem, 2, 7);

        outroGrid.add(new Label("Bairro de origem: "), 1, 8);
        MbairroOrigem = new TextField();
        outroGrid.add(MbairroOrigem, 2, 8);

        outroGrid.add(new Label("Bairro destino: "), 1, 9);
        MbairroDestino = new TextField();
        outroGrid.add(MbairroDestino, 2, 9);

        outroGrid.add(new Label("Custo: "), 1,10);
        MCusto = new TextField();
        outroGrid.add(MCusto, 2,10);

        outroGrid.add(new Label("Avaliacao: "), 1, 11);
        MAvaliacao = new TextField();
        outroGrid.add(MAvaliacao, 2, 11);

        Button btAvaliarPassageiro = new Button("Avaliar passageiro");
        outroGrid.add(btAvaliarPassageiro, 2, 12);
        btAvaliarPassageiro.setOnAction(e -> {
            try {
                this.trataBtAvaliarPassageiro(e);
            } catch (IOException | IllegalArgumentException e1) {
                e1.printStackTrace();
            }
        }); 

        
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(outroGrid);

        Stage segundoStage = new Stage();
        Scene scene2 = new Scene(layout2, 800, 550);
        segundoStage.setTitle("Garoopa2222");
        segundoStage.setScene(scene2);
        segundoStage.show();
        




    }


    public void trataBtAvaliarPassageiro(ActionEvent event) throws IllegalArgumentException, IOException {
        viagem.getPassageiro().atualizaNota(Integer.parseInt(MAvaliacao.getText()));
        Repositorios.persiste();
    }

    public void trataBtGetViagem(ActionEvent event) throws IllegalArgumentException, IOException {
        Viagem v = RepositorioDeViagens.getViagemById(Integer.parseInt(cbViagens.getSelectionModel().getSelectedItem()));
        MdataViagem.setText(v.getDataHora().toString());
        MbairroOrigem.setText(v.getRoteiro().getBairroOrigem().getNome());
        MbairroDestino.setText(v.getRoteiro().getBairroDestino().getNome());
        MCusto.setText(String.valueOf(v.getCusto()));
        viagem = new ViagemParaExibicao(v);
    }

    public void trataBtBuscarMot(ActionEvent event) throws IllegalArgumentException, IOException {
        nomeMot.setText(RepositorioDeMotoristas.getMotoristaByCPF(cpfMot.getText()).getNome());
        Motorista mot = RepositorioDeMotoristas.getMotoristaByCPF(cpfMot.getText());
        List<Viagem> viagens = RepositorioDeViagens.getViagensByMotorista(mot);
        List<String> lst = new LinkedList<>();
        for (Viagem v: viagens)
            lst.add(String.valueOf(v.getIdentificador()));
        cbViagens.getItems().setAll(lst);
    }
    
    public void trataBotaoBuscaMot(ActionEvent event) throws IllegalArgumentException, IOException {
        sp = null;
        sp = new ServicosDoPassageiro(cpf.getText(), bairroOrigem.getText(), bairroDestino.getText(), 
                                cbxFormaPgto.getSelectionModel().getSelectedItem().toString(), cbxTipoVeiculo.getSelectionModel().getSelectedItem().toString());
        viagem = new ViagemParaExibicao(sp.getViagem());
       /*  Alert numEx = new Alert(Alert.AlertType.WARNING,sp.toString());
            numEx.showAndWait(); */
        // Recupera produto selecionado
//        Produto prod = cbProdutos.getSelectionModel().getSelectedItem();
        int qtdade = 0;
 /*        try{
            qtdade = Integer.parseInt(tfQtdade.getText());
        }catch(NumberFormatException e){
            Alert numEx = new Alert(Alert.AlertType.WARNING,"Quantidade inválida");
            numEx.showAndWait();
        } */
        // Cadastra o novo item e exibe
//        ItemCart tc = cart.addItem(prod.getCodigo(), qtdade);
//        taCart.appendText("Produto: "+tc.getProduto()+
//                          ", quantidade:"+tc.getQuantidade()+
//                          ", valor item:"+tc.getValorDoItem()+
//                          "\n");
        atualizaCampos();
    }

    public void trataBotaoFinalizaCorrida(ActionEvent event) throws IllegalArgumentException, IOException {
        RepositorioDeViagens.add(sp.getViagem());   
        Repositorios.persiste();            
        atualizaCampos();
    }

    public void trataBotaoAvaliaMotorista(ActionEvent event) throws IllegalArgumentException, IOException {
        sp.avaliarMotorista(pontMotorista.getText());
        Repositorios.persiste();            
        atualizaCampos();
    }

    public void trataBotaoGravar(ActionEvent event) throws IllegalArgumentException, IOException {
        Repositorios.persiste();
    }



    public void trataBotaoRemove(ActionEvent event){
//        cart.removeLast();
        taCart.clear();
/*         for(ItemCart tc:cart){
            taCart.appendText("Produto: "+tc.getProduto()+
            ", quantidade:"+tc.getQuantidade()+
            ", valor item:"+tc.getValorDoItem()+
            "\n");
        }
        atualizaCampos(); */
    }

    public void trataBotaoCheckout(ActionEvent event){
        // Deve salvar o conteúdo do carrinho em algum mecanismo de persistencia antes
//        cart = new Cart();
        // cart.persiste();
        taCart.clear();
//        atualizaCampos();
        Alert numEx = new Alert(Alert.AlertType.CONFIRMATION,"Compra efetuada!");
        numEx.showAndWait();
    }

    @Override
    public void handle(ActionEvent e){
    }

    public static void main(String args[]) throws FileNotFoundException, IllegalArgumentException {
        launch(args);
    }
}

