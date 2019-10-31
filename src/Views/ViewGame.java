package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import Models.Timer;
import Tool.Path;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class ViewGame {

    private ModelGame model;
    private BorderPane root;
    private ImageView imgBomb;
    private Text txtNbBombe,timer;
    private GridPane plateauGUI;
    private HBox hBoxStatusBar, hBoxBottom;
    private Button btnRetour;

    public ViewGame(BorderPane root, ModelGame model){
        this.root = root;
        this.model = model;
        hBoxStatusBar=new HBox();
        hBoxBottom = new HBox();

        imgBomb = new ImageView(Path.urlBombImg);
        imgBomb.setFitHeight(50);
        imgBomb.setFitWidth(50);

        timer = new Text();
        this.model.timer = new Timer(timer) ;
        timer.setFont(Font.font(30));
        timer.setTextAlignment(TextAlignment.RIGHT);
        //HBox.setMargin(timer,new Insets(0,0,0,200));

        txtNbBombe = new Text(String.valueOf(model.getPlateau().getNbBombe()));

        hBoxStatusBar.setPadding(new Insets(15, 12, 15, 12));
        hBoxStatusBar.setSpacing(20);
        hBoxStatusBar.setAlignment(Pos.CENTER_LEFT);

        hBoxStatusBar.getChildren().add(imgBomb);
        hBoxStatusBar.getChildren().add(txtNbBombe);
        hBoxStatusBar.getChildren().add(timer);

        plateauGUI=model.getPlateau().getPlateauGUI();
        plateauGUI.setAlignment(Pos.CENTER);

        btnRetour = new Button("RETOUR");
        hBoxBottom.getChildren().add(btnRetour);
        hBoxBottom.setAlignment(Pos.CENTER);

        root.getChildren().clear();
        root.setTop(hBoxStatusBar);
        root.setCenter(plateauGUI);
        root.setBottom(hBoxBottom);


    }

    public void setEvents(ControllerGame controllerGame){

        for(Node node : plateauGUI.getChildren()){
            node.setOnMouseClicked(controllerGame);
        }

        btnRetour.setOnMouseClicked(controllerGame);
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
}
