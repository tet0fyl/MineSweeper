package Views;

import Controllers.ControllerGame;
import Models.ModelGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewHandler extends Application {
    private Stage stage;
    private Scene scene;
    private Group root;
    private ModelGame modelGame;
    private ViewGame viewGame;
    private ControllerGame controllerGame;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        root=new Group();
        scene= new Scene(root,500,500);

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
