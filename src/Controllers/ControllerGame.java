package Controllers;

import Models.ModelGame;
import Views.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.font.NumericShaper;


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
        Node node = (Node) mouseEvent.getSource();
        Integer getX = GridPane.getColumnIndex(node);
        Integer getY = GridPane.getRowIndex(node);


        System.out.println("Clique en X : " + getX + " et Y : " + getY);

        if(modelGame.getPlateau().jaiUneBombe(getX,getY)){
            System.out.println("BOOOOOM GAME OVER");
        }else{
            modelGame.getPlateau().startWaveDetection(launcher.getViewGame().getPlateauGUI(),getX,getY);
        }



        /* -- J'etais parti au debut avec un systeme d'id sur les Button du GridPane du plateau
        mais cela me causer des bugs aleatoire, certain id n'étais null alors que l'objet cibler en poseder bien
        J'ai decider d'utiliser une methode static plus performante proposer par l'objet Gridpane (voir juste en haut) --
        String getId = mouseEvent.getPickResult().getIntersectedNode().getId();
        System.out.println(getId);
        byte getX = Byte.parseByte(getId.split(",")[0]);
        byte getY = Byte.parseByte(getId.split(",")[1]);
        System.out.println("X :" + getX);
        System.out.println("Y :" + getY);*/


    }

}
