package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewHandler extends Application {
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private ModelGame modelGame;
    private ViewGame viewGame;
    private ControllerGame controllerGame;
    public int squareSizeScene = 600;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        root=new BorderPane();
        scene= new Scene(root,squareSizeScene,squareSizeScene);

        launchGame();

        scene.getStylesheets().add("Asset/css/style.css");
        stage.setScene(scene);
        stage.show();

    }

    public void setEventHandlerGame(ControllerGame controllerGame){
        viewGame.setEvents(controllerGame);
    }

    public void launchGame(){
        modelGame = new ModelGame();
        viewGame = new ViewGame(root, modelGame);
        controllerGame = new ControllerGame(this,modelGame);
    }

    public ViewGame getViewGame(){
        return viewGame;
    }

}
