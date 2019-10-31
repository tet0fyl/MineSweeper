package Controllers;

import Models.Menu;
import Views.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerMenu implements EventHandler<MouseEvent> {
    private ViewHandler launcher;
    private Menu model;

    public ControllerMenu(ViewHandler launcher, Menu menu){
        this.model = menu;
        this.launcher=launcher;


        launcher.setEventHandlerMenu(this);

    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnStart())){
                launcher.launchGame();
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnExit())){
                launcher.getStage().close();
            }
        }


        model.parallax.move(mouseEvent.getSceneX(),mouseEvent.getSceneY());


    }
}
