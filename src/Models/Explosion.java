package Models;

import Tool.Path;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Explosion extends AnimationTimer {

    private ImageView explosionGif, screenBreak;
    private Image screenBreakTmp;
    private long lastUpdate= 0;
    private boolean firstTime = true;
    public Explosion(){
    }

    public void prepare(int size){
        explosionGif = new ImageView(Path.urlExplosionGif);
        screenBreakTmp = new Image(Path.urlScreenBreak);
        explosionGif.setFitWidth(size);
        explosionGif.setFitHeight(size);
        screenBreak = new ImageView();
        screenBreak.setFitHeight(size);
        screenBreak.setFitWidth(size);
    }

    @Override
    public void handle(long now) {
        if(firstTime){
            firstTime=false;
            lastUpdate = now;
            return;
        }
        if(now - lastUpdate >= 500_000_000L){
            screenBreak.setImage(screenBreakTmp);
        }

        if(now - lastUpdate >= 1_500_000_000L){

            System.out.println("Ca lance");
            explosionGif.setImage(null);
            this.stop();

        }
    }

    public ImageView getExplosionGif() {
        return explosionGif;
    }

    public ImageView getScreenBreak() {
        return screenBreak;
    }
}
