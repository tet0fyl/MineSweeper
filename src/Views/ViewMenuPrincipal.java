package Views;

import Controllers.ControllerMenu;
import Models.Menu;
import Tool.Path;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ViewMenuPrincipal {
    private BorderPane rootBorderPaneMenu, rootBorderPaneOption,rootBorderPaneScore;
    private BorderPane root;
    private Menu model;
    private Text mainTitle,mainTitleOption, textTitleScore;
    private Button btnStart,btnTableauDesScore,btnOption,btnExit,btnRetourOption, btnRetourScore;
    private VBox mainMenuVBox, vBoxOption, vBoxScore;
    private HBox slider;
    private Group parallax;



    public ViewMenuPrincipal(BorderPane root, Menu model){
        this.root =root;
        this.model = model;
        rootBorderPaneMenu = new BorderPane();
        rootBorderPaneOption = new BorderPane();
        rootBorderPaneScore = new BorderPane();

        initVBoxMenu();
        initVBoxOption();
        initVBoxScore();

        initParallax();

        clearAndInitRoot();

    }

    public void clearAndInitRoot(){
        root.getChildren().add(parallax);

        slider = model.slider.initSlider(root,rootBorderPaneMenu,rootBorderPaneOption, rootBorderPaneScore);
        root.getChildren().add(slider);

    }

    public void initParallax(){

        parallax = model.parallax.getRoot();
        model.parallax.initSize(600);
    }

    public void initVBoxScore(){
        vBoxScore = new VBox();

        textTitleScore = initTitle("Table Score");

        btnRetourScore = initButton("Retour");

        vBoxScore.getChildren().add(textTitleScore);
        vBoxScore.getChildren().add(btnRetourScore);

        vBoxScore.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(textTitleScore,new Insets(75,0,50,0));
        vBoxScore.setSpacing(25);

        rootBorderPaneScore.getChildren().clear();
        rootBorderPaneScore.setCenter(vBoxScore);
    }

    public void initVBoxOption(){
        vBoxOption = new VBox();

        mainTitleOption = initTitle("Option");

        btnRetourOption = initButton("Retour");

        vBoxOption.getChildren().add(mainTitleOption);
        vBoxOption.getChildren().add(btnRetourOption);

        vBoxOption.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(mainTitleOption,new Insets(75,0,50,0));
        vBoxOption.setSpacing(25);

        rootBorderPaneOption.getChildren().clear();
        rootBorderPaneOption.setCenter(vBoxOption);
    }

    public void initVBoxMenu(){
        mainMenuVBox = new VBox();

        mainTitle = initTitle("MineSweeper");
        btnStart = initButton("Start");
        btnOption = initButton("Option");
        btnTableauDesScore = initButton("Table Score");
        btnExit = initButton("Exit");



        mainMenuVBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(mainTitle,new Insets(75,0,50,0));
        mainMenuVBox.setSpacing(25);

        mainMenuVBox.getChildren().add(mainTitle);

        mainMenuVBox.getChildren().add(btnStart);
        mainMenuVBox.getChildren().add(btnOption);
        mainMenuVBox.getChildren().add(btnTableauDesScore);
        mainMenuVBox.getChildren().add(btnExit);

        rootBorderPaneMenu.getChildren().clear();
        rootBorderPaneMenu.setCenter(mainMenuVBox);


    }

    public Text initTitle(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 50));
        t.setFill(Color.WHITE);
        t.setRotate(17);
        BorderPane.setAlignment(t,Pos.CENTER);
        return t;
    }

    public Button initButton(String text){
        Button b = new Button(text);
        b.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 20));
        b.getStyleClass().add("btn");
        return b;
    }


    public void setEvents(ControllerMenu controllerMenu){
        btnStart.setOnMouseClicked(controllerMenu);
        btnOption.setOnMouseClicked(controllerMenu);
        btnExit.setOnMouseClicked(controllerMenu);
        btnTableauDesScore.setOnMouseClicked(controllerMenu);
        btnRetourOption.setOnMouseClicked(controllerMenu);
        btnRetourScore.setOnMouseClicked(controllerMenu);


        for(Node node: rootBorderPaneMenu.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }
        for(Node node:mainMenuVBox.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }
        for(Node node:vBoxOption.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }

        for(Node node:slider.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }

    }

    public Button getBtnExit() {
        return btnExit;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnRetourOption(){
        return btnRetourOption;
    }

    public Button getBtnOption(){return btnOption;}

    public Button getBtnTableauDesScore() {
        return btnTableauDesScore;
    }

    public Button getBtnRetourScore() {
        return btnRetourScore;
    }

    public HBox getSlider() {
        return slider;
    }
}
