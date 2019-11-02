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

    public ControllerGame(ViewHandler launcher, ModelGame modelGame){
        this.launcher=launcher;
        this.modelGame= modelGame;

        launcher.setEventHandlerGame(this);
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        modelGame.timer.start();

        if(mouseEvent.getSource().equals(launcher.getViewGame().getBtnRetour())){
            modelGame.timer.stopTimer();
            launcher.launchMenu();
        }

        if(mouseEvent.getSource().equals(launcher.getViewGame().getBtnRetourMenu())){
            launcher.launchMenu();
        }
        try{
            Node node = (Node) mouseEvent.getSource();
            Integer getX = GridPane.getColumnIndex(node);
            Integer getY = GridPane.getRowIndex(node);

            if(modelGame.getPlateau().jaiUneBombe(getX,getY)){
                modelGame.getPlateau().caseDiscoverBomb(launcher.getViewGame().getPlateauGUI(),getX,getY);
                modelGame.getExplosion().prepare(launcher.squareSizeScene);
                launcher.getViewGame().getRoot().getChildren().add(modelGame.getExplosion().getExplosionGif());
                launcher.getViewGame().getRoot().getChildren().add(modelGame.getExplosion().getScreenBreak());
                modelGame.getExplosion().start();
                modelGame.getPlateau().caseDiscoveredAllGameOver(launcher.getViewGame().getPlateauGUI());
                modelGame.timer.stop();
                launcher.getViewGame().getBtnRetour().setOpacity(0);
                launcher.getViewGame().getRoot().getChildren().add(launcher.getViewGame().getGameOverPopUp());


            }else if(modelGame.getPlateau().isHeWinning(launcher.getViewGame().getPlateauGUI())){
                launcher.getViewGame().getRoot().getChildren().add(launcher.getViewGame().getWinPopUp());
            }else{
                modelGame.getPlateau().startWaveDetection(launcher.getViewGame().getPlateauGUI(),getX,getY);
            }

        }catch (NullPointerException e){
            System.out.println(mouseEvent.getSource());
        }

    }
}
