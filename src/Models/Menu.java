package Models;

import Tool.Path;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.HBox;

public class Menu {
    public Parallax parallax;
    public Slider slider = new Slider();
    public AnimationTimer followTheMenu;

    private double memoryXTranslation;

    public Menu(){
        parallax = new Parallax(Path.urlParallaxBg);



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
}
