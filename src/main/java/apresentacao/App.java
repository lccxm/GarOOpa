package apresentacao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import casosDeUso.repositorios.RepositorioDeBairros;
import casosDeUso.repositorios.RepositorioDeViagens;
import casosDeUso.repositorios.Repositorios;
import casosDeUso.servicos.ServicosDoPassageiro;
import casosDeUso.servicos.ViagemParaExibicao;
import entidades.FormaPgto;
import entidades.TipoVeiculo;
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
    private TextField cpf, bairroOrigem, bairroDestino, tfTotal;
    private TextField tfDesconto, tfTaxaEntrega, tfICMS, tfISSQN, tfTotalPagar, nomeMotorista, placaVeiculo,
            marcaVeiculo, custoCorrida, pontMotorista;
    private TextArea taCart;
    private ComboBox<FormaPgto> cbxFormaPgto;
    private ComboBox<TipoVeiculo> cbxTipoVeiculo;
    private ViagemParaExibicao viagem;
    private ServicosDoPassageiro sp;

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
        Repositorios.persiste();
        /*
         * try { Repositorios.carregaTodos(); } catch (IllegalArgumentException e2) { //
         * TODO Auto-generated catch block e2.printStackTrace(); }
         */
        /*
         * Repositorios r = new Repositorios(); r.carregaTodos();
         */
        // Instancia estruturas de dados
        // cadProd = CadastroProduto.getInstance();
        // cart = new Cart();
        // Define o grid basico
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
        // btFinalizarCorrida.setOnAction(e->this.trataBotaoFinalizaCorrida(e));
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
        // btBuscarMotorista.setOnAction(e->this.trataBotaoAdd(e));
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

    public void trataBotaoFinalizaCorrida(ActionEvent event) throws FileNotFoundException, IllegalArgumentException {
        RepositorioDeViagens.add(sp.getViagem());               
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

