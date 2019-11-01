package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import Models.Timer;
import Tool.PathCst;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ViewGame {

    private ModelGame model;
    private BorderPane root;
    private ImageView imgBomb;
    private Text txtNbBombe,timer;
    private GridPane plateauGUI;
    private HBox hBoxStatusBar, hBoxBottom;
    private Button btnRetour;
    ImageView imgBg;
    private BorderPane gameOverPopUp;
    private VBox vBoxGameOverPopUp;
    private TextField strPseudo;
    private Button btnRetourMenu;
    private Text txtGameOver, txtEntrerVotreNom;

    public ViewGame(BorderPane root, ModelGame model){
        this.root = root;
        this.model = model;
        hBoxStatusBar=new HBox();
        hBoxBottom = new HBox();

        gameOverPopUp = new BorderPane();

        initVBoxGameOverPopUp();


        imgBomb = new ImageView(PathCst.urlBombImg);
        imgBomb.setFitHeight(50);
        imgBomb.setFitWidth(50);

        timer = initText("00:00");
        this.model.timer = new Timer(timer) ;
        HBox.setMargin(timer,new Insets(0,0,0,300));

        txtNbBombe = initText(String.valueOf(model.getPlateau().getNbBombe()));

        hBoxStatusBar.setPadding(new Insets(15, 12, 15, 12));
        hBoxStatusBar.setSpacing(20);
        hBoxStatusBar.setAlignment(Pos.CENTER_LEFT);

        hBoxStatusBar.getChildren().add(imgBomb);
        hBoxStatusBar.getChildren().add(txtNbBombe);
        hBoxStatusBar.getChildren().add(timer);

        plateauGUI=model.getPlateau().getPlateauGUI();
        plateauGUI.setAlignment(Pos.CENTER);

        btnRetour = initButton("Retour");
        hBoxBottom.getChildren().add(btnRetour);
        hBoxBottom.setAlignment(Pos.CENTER);

        initBgImg();

        clearAndInitRoot();


    }


    public void initVBoxGameOverPopUp(){
        vBoxGameOverPopUp = new VBox();

        txtGameOver = initTitle("GAME OVER");

        txtEntrerVotreNom = initText("Entrez votre nom :");

        strPseudo = new TextField();
        strPseudo.setPrefWidth(100d);
        strPseudo.setPromptText("AAA");

        btnRetourMenu = initButton("Continuer");

        vBoxGameOverPopUp.getChildren().add(txtGameOver);
        vBoxGameOverPopUp.getChildren().add(txtEntrerVotreNom);
        vBoxGameOverPopUp.getChildren().add(strPseudo);
        vBoxGameOverPopUp.getChildren().add(btnRetourMenu);

        vBoxGameOverPopUp.setAlignment(Pos.TOP_CENTER);
        vBoxGameOverPopUp.setMinWidth(600);
        VBox.setMargin(txtGameOver,new Insets(125,0,50,0));
        vBoxGameOverPopUp.setSpacing(25);

        gameOverPopUp.getChildren().clear();
        gameOverPopUp.setCenter(vBoxGameOverPopUp);
    }

    public BorderPane getGameOverPopUp() {
        return gameOverPopUp;
    }

    public void clearAndInitRoot(){
        root.getChildren().clear();
        root.getChildren().add(imgBg);
        root.setTop(hBoxStatusBar);
        root.setCenter(plateauGUI);
        root.setBottom(hBoxBottom);
    }



    public Text initTitle(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 50));
        t.setFill(Color.WHITE);
        t.setRotate(17);
        BorderPane.setAlignment(t,Pos.CENTER);
        return t;
    }

    public Text initText(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 35));
        t.setFill(Color.WHITE);
        BorderPane.setAlignment(t,Pos.CENTER);
        return t;
    }

    public Button initButton(String text){
        Button b = new Button(text);
        b.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 20));
        b.getStyleClass().add("btn");
        return b;
    }

    public void initBgImg(){
        imgBg = new ImageView(PathCst.urlMainBg);
        imgBg.setFitHeight(root.getHeight());
        imgBg.setPreserveRatio(true);
    }



    public void setEvents(ControllerGame controllerGame){

        for(Node node : plateauGUI.getChildren()){
            node.setOnMouseClicked(controllerGame);
        }

        btnRetour.setOnMouseClicked(controllerGame);
        btnRetourMenu.setOnMouseClicked(controllerGame);
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }

    public Text getTimer() {
        return timer;
    }

    public Button getBtnRetour() {
        return btnRetour;
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getBtnRetourMenu() {
        return btnRetourMenu;
    }
}
