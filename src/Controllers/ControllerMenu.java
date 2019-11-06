package Controllers;

import Models.Menu;
import Models.Plateau;
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

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnStart())){
                model.stopToFollowTheMenu();
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

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnEasy())){
                launcher.setDifficulty(Plateau.FACILE);

                launcher.getViewMenuPrincipal().getBtnMedium().getStyleClass().remove("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnHard().getStyleClass().remove("btnOptionPressed");


                launcher.getViewMenuPrincipal().getBtnEasy().getStyleClass().add("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnMedium().getStyleClass().add("btn-secondary");
                launcher.getViewMenuPrincipal().getBtnHard().getStyleClass().add("btn-secondary");
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnMedium())){
                launcher.setDifficulty(Plateau.NORMAL);

                launcher.getViewMenuPrincipal().getBtnEasy().getStyleClass().remove("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnHard().getStyleClass().remove("btnOptionPressed");


                launcher.getViewMenuPrincipal().getBtnMedium().getStyleClass().add("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnEasy().getStyleClass().add("btn-secondary");
                launcher.getViewMenuPrincipal().getBtnHard().getStyleClass().add("btn-secondary");
            }

            if(mouseEvent.getSource().equals(launcher.getViewMenuPrincipal().getBtnHard())){
                launcher.setDifficulty(Plateau.DIFFICILE);

                launcher.getViewMenuPrincipal().getBtnEasy().getStyleClass().remove("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnMedium().getStyleClass().remove("btnOptionPressed");


                launcher.getViewMenuPrincipal().getBtnHard().getStyleClass().add("btnOptionPressed");
                launcher.getViewMenuPrincipal().getBtnMedium().getStyleClass().add("btn-secondary");
                launcher.getViewMenuPrincipal().getBtnEasy().getStyleClass().add("btn-secondary");
            }




    }
}
