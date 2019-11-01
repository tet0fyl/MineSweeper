package Views;

import Controllers.ControllerGame;
import Controllers.ControllerMenu;
import Models.Menu;
import Models.ModelGame;
import Models.Plateau;
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
    private Menu modelMenu;
    private ViewMenuPrincipal viewMenuPrincipal;
    private ControllerMenu controllerMenu;
    public int squareSizeScene = 600;
    private String difficulty = Plateau.FACILE;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        root=new BorderPane();
        scene= new Scene(root,squareSizeScene,squareSizeScene);

        launchMenu();

        root.getStylesheets().add("Asset/css/style.css");


        stage.setScene(scene);
        stage.show();

    }

    public void setEventHandlerGame(ControllerGame controllerGame){
        viewGame.setEvents(controllerGame);
    }
    public void setEventHandlerMenu(ControllerMenu controllerMenu){viewMenuPrincipal.setEvents(controllerMenu);}

    public void launchGame(){
        modelGame = new ModelGame(difficulty);
        viewGame = new ViewGame(root, modelGame);
        controllerGame = new ControllerGame(this,modelGame);
    }

    public void launchMenu(){
        modelMenu = new Menu();
        viewMenuPrincipal = new ViewMenuPrincipal(root, modelMenu);
        controllerMenu = new ControllerMenu(this,modelMenu);

    }

    public ViewGame getViewGame(){
        return viewGame;
    }
    public ViewMenuPrincipal getViewMenuPrincipal(){return viewMenuPrincipal;}


    public Stage getStage() {
        return stage;
    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public void setDifficulty(String difficulte) {
        this.difficulty = difficulte;
    }
}
