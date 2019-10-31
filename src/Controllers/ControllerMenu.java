package Controllers;

import Models.Menu;
import Models.Slider;
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
        model.startToFollowTheMenu(launcher.getViewMenuPrincipal().getSlider());
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

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnOption())){
                model.slider.transition(Slider.seconde);
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnTableauDesScore())){
                model.slider.transition(Slider.third);
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnRetourOption())){
                model.slider.transition(Slider.main);
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnRetourScore())){
                model.slider.transition(Slider.main);
            }
        }

        model.parallax.move(mouseEvent.getSceneX(),mouseEvent.getSceneY());


    }
}
