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


            }else{
                modelGame.getPlateau().startWaveDetection(launcher.getViewGame().getPlateauGUI(),getX,getY);
            }

        /* -- J'etais parti au debut avec un systeme d'id sur les Button du GridPane du plateau
        mais cela m'a causer des bugs aleatoires, certain id étais null alors que l'objet ciblé en posedait bien
        J'ai decider d'utiliser une methode static plus performante proposer par l'objet Gridpane (voir juste en haut) --
        String getId = mouseEvent.getPickResult().getIntersectedNode().getId();
        System.out.println(getId);
        byte getX = Byte.parseByte(getId.split(",")[0]);
        byte getY = Byte.parseByte(getId.split(",")[1]);
        System.out.println("X :" + getX);
        System.out.println("Y :" + getY);*/

        }catch (NullPointerException e){
            System.out.println(mouseEvent.getSource());
        }

        if(modelGame.getPlateau().isHeWinning(launcher.getViewGame().getPlateauGUI())){
            System.out.println("Ta gagné mec !");
        }else{
            System.out.println("Ta pas encore gagné");
        }

    }
}
