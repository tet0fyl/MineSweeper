package Controllers;

import Models.ModelGame;
import Views.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class ControllerGame implements EventHandler<MouseEvent> {

    private ModelGame modelGame;
    private ViewHandler launcher;
    private boolean startTheTimerTheFirstClick = true;

    public ControllerGame(ViewHandler launcher, ModelGame modelGame){
        this.launcher=launcher;
        this.modelGame= modelGame;

        launcher.setEventHandlerGame(this);
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        if(startTheTimerTheFirstClick){
            modelGame.timer.start();
            startTheTimerTheFirstClick=false;
        }

        if(mouseEvent.getSource().equals(launcher.getViewGame().getBtnRetour())){
            modelGame.timer.stopTimer();
            launcher.launchMenu();
        }

        if(mouseEvent.getSource().equals(launcher.getViewGame().getBtnRetourMenuGagnee())){
            launcher.launchMenu();
        }

        if(mouseEvent.getSource().equals(launcher.getViewGame().getBtnRetourMenuPerdu())){
            launcher.launchMenu();
        }

        try{
            Node node = (Node) mouseEvent.getSource();
            Integer getX = GridPane.getColumnIndex(node);
            Integer getY = GridPane.getRowIndex(node);

            modelGame.getPlateau().openCase(getX,getY);

            if(modelGame.getPlateau().getPartiePerdue()){
                modelGame.getExplosion().prepare(launcher.squareSizeScene);
                launcher.getViewGame().getRoot().getChildren().add(modelGame.getExplosion().getExplosionGif());
                launcher.getViewGame().getRoot().getChildren().add(modelGame.getExplosion().getScreenBreak());
                modelGame.getExplosion().start();
                modelGame.timer.stop();
                launcher.getViewGame().getBtnRetour().setOpacity(0);
                launcher.getViewGame().getRoot().getChildren().add(launcher.getViewGame().getGameOverPopUp());
                modelGame.getPlateau().revealThePlateau();

            }else if(modelGame.getPlateau().getPartieGagnee()){
                launcher.getViewGame().getRoot().getChildren().add(launcher.getViewGame().getWinPopUp());
                modelGame.timer.stop();
            }



        }catch (NullPointerException e){
            System.out.println(mouseEvent.getSource());
        }

    }
}
