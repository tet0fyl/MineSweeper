package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class ViewGame {

    private ModelGame model;
    private Group root;
    private Text txt;
    private GridPane plateauGUI;

    public ViewGame(Group root, ModelGame model){
        this.root = root;
        this.model = model;

        plateauGUI=model.getPlateau().getPlateauGUI();
        System.out.println(plateauGUI);
        root.getChildren().clear();
        root.getChildren().add(plateauGUI);

    }

    public void setEvents(ControllerGame controllerGame){

        for(Node node : plateauGUI.getChildren())
            node.setOnMouseClicked(controllerGame);
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }
}
