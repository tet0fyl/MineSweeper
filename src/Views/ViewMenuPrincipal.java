package Views;

import Controllers.ControllerMenu;
import Models.Menu;
import Tool.Path;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ViewMenuPrincipal {
    private BorderPane root;
    private Menu model;
    private Text mainTitle,txtChoiceDifficulty;
    private ChoiceBox cbChoiceDifficulty;
    private Button btnStart,btnTableauDesScore,btnExit;
    private VBox mainMenuVBox;
    private Group parallax;



    public ViewMenuPrincipal(BorderPane root, Menu model){
        this.root=root;
        this.model = model;

        mainTitle = new Text("MineSweeper");
        mainTitle.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 30));

        BorderPane.setAlignment(mainTitle,Pos.CENTER);

        mainMenuVBox = new VBox();

        btnStart = new Button("Start");
        btnStart.getStyleClass().add("btn");
        btnStart.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 20));


        txtChoiceDifficulty = new Text("Choose your difficulty :");
        txtChoiceDifficulty.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 20));



        cbChoiceDifficulty = new ChoiceBox(FXCollections.observableArrayList("Easy","Medium","Hard"));
        cbChoiceDifficulty.getSelectionModel().selectFirst();
        cbChoiceDifficulty.getStyleClass().add("btn");



        btnTableauDesScore = new Button("Tableau des Scores");
        btnTableauDesScore.getStyleClass().add("btn");
        btnTableauDesScore.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 20));


        btnExit = new Button("Exit");
        btnExit.getStyleClass().add("btn");
        btnExit.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(Path.fontSpongeBob), 20));


        mainMenuVBox.getChildren().add(btnStart);
        mainMenuVBox.getChildren().add(txtChoiceDifficulty);
        mainMenuVBox.getChildren().add(cbChoiceDifficulty);
        mainMenuVBox.getChildren().add(btnTableauDesScore);
        mainMenuVBox.getChildren().add(btnExit);

        mainMenuVBox.setAlignment(Pos.TOP_CENTER);
        mainMenuVBox.setPadding(new Insets(100,0,0,0));
        mainMenuVBox.setSpacing(25);

        parallax = model.parallax.getRoot();
        model.parallax.initSize(root.getHeight());

        root.getChildren().clear();
        root.getChildren().add(parallax);
        root.setTop(mainTitle);
        root.setCenter(mainMenuVBox);



    }

    public void setEvents(ControllerMenu controllerMenu){
        btnStart.setOnMouseClicked(controllerMenu);

        for(Node node:root.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }
        for(Node node:mainMenuVBox.getChildren()){
            node.setOnMouseMoved(controllerMenu);
        }

    }

    public Button getBtnExit() {
        return btnExit;
    }

    public Button getBtnStart() {
        return btnStart;
    }
}
