package Views;

import Controllers.ControllerMenu;
import Models.Menu;
import Models.Plateau;
import Tool.PathCst;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ViewMenuPrincipal {
    private BorderPane rootBorderPaneMenu, rootBorderPaneOption,rootBorderPaneScore;
    private BorderPane root;
    private Menu model;
    private Text mainTitle,mainTitleOption, textTitleScore,txtChooseDifficulty;
    private Button btnStart,btnTableauDesScore,btnOption,btnExit,btnRetourOption, btnRetourScore, btnEasy,btnMedium,btnHard;
    private VBox mainMenuVBox, vBoxOption, vBoxScore;
    private HBox slider;
    private Group parallax;
    private GridPane scoreGrid = new GridPane();



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
        root.getChildren().clear();
        root.getChildren().add(parallax);

        slider = model.slider.initSlider(root,rootBorderPaneMenu,rootBorderPaneOption, rootBorderPaneScore);
        root.getChildren().add(slider);

    }

    public void initVBoxScore(){
        vBoxScore = new VBox();

        textTitleScore = initTitle("Table Score");
        for (int i = 0; i < model.getListScore().size() ; i++) {
            String row = model.getListScore().get(i);
            String pseudo = row.split(",")[0];
            String difficulty = row.split(",")[1];
            String time = row.split(",")[2];

            Text txtPseudo = initText(pseudo);
            Text txtDifficulty = initText(difficulty);
            Text txtTime = initText(time);

            if(i<1){
                txtPseudo.setFill(Color.ORANGE);
                txtDifficulty.setFill(Color.ORANGE);
                txtTime.setFill(Color.ORANGE);
            }else{

            }

            scoreGrid.add(txtPseudo,0,i);
            scoreGrid.add(txtDifficulty,2,i);
            scoreGrid.add(txtTime,3,i);

        }

        scoreGrid.setAlignment(Pos.CENTER);
        scoreGrid.setHgap(20);

        btnRetourScore = initButton("Retour");

        vBoxScore.getChildren().add(textTitleScore);
        vBoxScore.getChildren().add(scoreGrid);
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

        txtChooseDifficulty = initText("Choose the difficulty :");

        HBox hbox = new HBox();

        btnEasy = initButton(Plateau.FACILE);
        btnMedium = initButtonSecondary(Plateau.NORMAL);
        btnHard = initButtonSecondary(Plateau.DIFFICILE);

        hbox.getChildren().add(btnEasy);
        hbox.getChildren().add(btnMedium);
        hbox.getChildren().add(btnHard);
        HBox.setMargin(btnEasy,new Insets(0,10,0,0));
        HBox.setMargin(btnMedium,new Insets(0,10,0,0));
        HBox.setMargin(btnHard,new Insets(0,10,0,0));
        hbox.setAlignment(Pos.CENTER);

        btnRetourOption = initButton("Retour");

        vBoxOption.getChildren().add(mainTitleOption);
        vBoxOption.getChildren().add(txtChooseDifficulty);
        vBoxOption.getChildren().add(hbox);
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
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 50));
        t.setFill(Color.WHITE);
        t.setRotate(17);
        BorderPane.setAlignment(t,Pos.CENTER);
        return t;
    }

    public Text initText(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 20));
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

    public Button initButtonSecondary(String text){
        Button b = new Button(text);
        b.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 20));
        b.getStyleClass().add("btn-secondary");
        return b;
    }


    public void setEvents(ControllerMenu controllerMenu){
        btnStart.setOnMouseClicked(controllerMenu);
        btnOption.setOnMouseClicked(controllerMenu);
        btnExit.setOnMouseClicked(controllerMenu);
        btnTableauDesScore.setOnMouseClicked(controllerMenu);
        btnRetourOption.setOnMouseClicked(controllerMenu);
        btnRetourScore.setOnMouseClicked(controllerMenu);
        btnEasy.setOnMouseClicked(controllerMenu);
        btnMedium.setOnMouseClicked(controllerMenu);
        btnHard.setOnMouseClicked(controllerMenu);
    }

    public void initParallax(){

        parallax = model.parallax.getRoot();
        model.parallax.initSize(600);
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

    public Button getBtnEasy() {
        return btnEasy;
    }

    public Button getBtnMedium() {
        return btnMedium;
    }

    public Button getBtnHard() {
        return btnHard;
    }

    public HBox getSlider() {
        return slider;
    }
}
