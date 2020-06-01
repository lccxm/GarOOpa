
import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class App extends Application {
    //private CadastroProduto cadProd;
    //private Cart cart;

    //private ComboBox<Produto> cbProdutos;
    private TextField tfQtdade,tfTotal;
    private TextField tfDesconto,tfTaxaEntrega,
                      tfICMS,tfISSQN,tfTotalPagar;
    private TextArea taCart;
    
    public static HBox criaCampoTexto(String label,TextField tf){
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_LEFT);
        hb.getChildren().add(new Label(label));
        hb.getChildren().add(tf);
        return hb;
    }
/*
    public void atualizaCampos(){
        tfTotal.setText("R$ "+cart.getSubTotal());
        tfDesconto.setText("R$ "+cart.getDesconto());
        tfTaxaEntrega.setText("R$ "+cart.getTaxaEntrega());
        tfICMS.setText("R$ "+cart.getICMS());
        tfISSQN.setText("R$ "+cart.getISSQN());
        tfTotalPagar.setText("R$ "+cart.getTotalPagar());
    }
*/
    @Override
    public void start(Stage primaryStage) {
        // Instancia estruturas de dados
//        cadProd = CadastroProduto.getInstance();
//        cart = new Cart();

        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Define o título do form
        Text tfTitulo = new Text("ACME's Shopping Cart");
        tfTitulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tfTitulo, 0, 0, 2, 1);    
        
        // Define o painel de seleção de produtos
        GridPane topGrid = new GridPane();
        topGrid.setAlignment(Pos.BASELINE_LEFT);
        topGrid.setHgap(4);
        topGrid.setVgap(6);

        // Monta lista de produtos para exibição
//        List<Produto> produtos = new ArrayList<>();
//        for(ProdutoDTO p:cadProd){
//            produtos.add(EntidadeDTOConverter.Dto2Prod(p));
//        }
        // Monta o combo de produtos
        topGrid.add(new Label("Selecione um produto:"), 0, 1);
//        cbProdutos = new ComboBox<Produto>(FXCollections.observableArrayList(produtos));
//        cbProdutos.getSelectionModel().selectFirst();
//        topGrid.add(cbProdutos, 0, 2);        
//        topGrid.add(new Label("Quantidade:"), 1, 2);
        tfQtdade = new TextField();
        topGrid.add(tfQtdade,2,2);

        // Botoes
        Button btAdd = new Button("Add");
        btAdd.setOnAction(e->this.trataBotaoAdd(e));
        Button btRemove = new Button("Remove");
        btRemove.setOnAction(e->this.trataBotaoRemove(e));
        Button btCheckout = new Button("Checkout");
        btCheckout.setOnAction(e->this.trataBotaoCheckout(e));
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btAdd);
        hbBtn.getChildren().add(btRemove);
        hbBtn.getChildren().add(btCheckout);
        topGrid.add(hbBtn, 0, 3);

        // Define a área de montagem do carrinho
        GridPane cartGrid = new GridPane();
        cartGrid.setAlignment(Pos.BASELINE_LEFT);
        cartGrid.setHgap(4);
        cartGrid.setVgap(6);
        
        taCart = new TextArea();
        taCart.setEditable(false);
        cartGrid.add(taCart,0,0);

        // Adiciona campos texto
        tfTotal = new TextField();
        cartGrid.add(criaCampoTexto("Total:", tfTotal),0,2);
        tfDesconto = new TextField();
        cartGrid.add(criaCampoTexto("Desconto:", tfDesconto),0,3);
        tfTaxaEntrega = new TextField();
        cartGrid.add(criaCampoTexto("Taxa de entrega:", tfTaxaEntrega),0,4);
        tfICMS = new TextField();
        cartGrid.add(criaCampoTexto("ICMS:", tfICMS),0,5);
        tfISSQN = new TextField();
        cartGrid.add(criaCampoTexto("ISSQN:", tfISSQN),0,6);
        tfTotalPagar = new TextField();
        cartGrid.add(criaCampoTexto("Total a pagar:", tfTotalPagar),0,7);;

        // Adiciona os paineis da direita e da esquerda no grid básico
        grid.add(topGrid,0,2);
        grid.add(cartGrid,0,3);

        StackPane root = new StackPane();
        root.getChildren().add(grid);        
        
        Scene scene = new Scene(root, 800, 550);
        primaryStage.setTitle("ACME Shopping Cart");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public void trataBotaoAdd(ActionEvent event){
        // Recupera produto selecionado
//        Produto prod = cbProdutos.getSelectionModel().getSelectedItem();
        int qtdade = 0;
        try{
            qtdade = Integer.parseInt(tfQtdade.getText());
        }catch(NumberFormatException e){
            Alert numEx = new Alert(Alert.AlertType.WARNING,"Quantidade inválida");
            numEx.showAndWait();
        }
        // Cadastra o novo item e exibe
//        ItemCart tc = cart.addItem(prod.getCodigo(), qtdade);
//        taCart.appendText("Produto: "+tc.getProduto()+
//                          ", quantidade:"+tc.getQuantidade()+
//                          ", valor item:"+tc.getValorDoItem()+
//                          "\n");
//        atualizaCampos();
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

    public static void main(String args[]){
        launch(args);
    }
}

