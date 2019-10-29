package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import Tool.Path;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ViewGame {

    private ModelGame model;
    private BorderPane root;
    private ImageView imgBomb;
    private Text txtNbBombe,timer;
    private GridPane plateauGUI;
    private HBox hBoxStatusBar;
    private VBox vBoxFace;

    public ViewGame(BorderPane root, ModelGame model){
        this.root = root;
        this.model = model;
        hBoxStatusBar=new HBox();

        imgBomb = new ImageView(Path.urlBombImg);
        imgBomb.setFitHeight(50);
        imgBomb.setFitWidth(50);

        timer = new Text("00:00");
        timer.setFont(Font.font(30));
        HBox.setMargin(timer,new Insets(0,0,0,200));

        txtNbBombe = new Text(String.valueOf(model.getPlateau().getNbBombe()));

        hBoxStatusBar.setPadding(new Insets(15, 12, 15, 12));
        hBoxStatusBar.setSpacing(20);
        hBoxStatusBar.setAlignment(Pos.CENTER_LEFT);

        hBoxStatusBar.getChildren().add(imgBomb);
        hBoxStatusBar.getChildren().add(txtNbBombe);
        hBoxStatusBar.getChildren().add(timer);



        plateauGUI=model.getPlateau().getPlateauGUI();

        root.getChildren().clear();
        root.setTop(hBoxStatusBar);
        root.setCenter(plateauGUI);

    }

    public void setEvents(ControllerGame controllerGame){

        for(Node node : plateauGUI.getChildren())
            node.setOnMouseClicked(controllerGame);
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }

    public Text getTimer() {
        return timer;
    }
}
