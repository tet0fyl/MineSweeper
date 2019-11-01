package Models;

import Tool.PathCst;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Menu {
    public Parallax parallax;
    public Slider slider = new Slider();
    public AnimationTimer followTheMenu;
    public Path fileScore;
    private List<String> listScore;
    private String difficulty;


    private double memoryXTranslation = 0;

    public Menu(){
        parallax = new Parallax(PathCst.urlParallaxBg);
        requestCsvFile();
    }

    public void requestCsvFile(){
        fileScore = Paths.get(PathCst.urlData);
        try {
            listScore = Files.readAllLines(fileScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startToFollowTheMenu(HBox targetX){
        followTheMenu = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(memoryXTranslation != targetX.getTranslateX()){
                    parallax.followXSecondaryTarget(-1*targetX.getTranslateX());
                    memoryXTranslation = targetX.getTranslateX();
                }
            }
        };
        followTheMenu.start();
    }

    public void stopToFollowTheMenu(){
        followTheMenu.stop();
    }

    public List<String> getListScore() {
        return listScore;
    }

    public void setDifficulty(String difficulty) {
         this.difficulty = difficulty;
    }
}
