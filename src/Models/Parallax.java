package Models;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Parallax {
    private ImageView panel1, panel2, panel3, panel4;
    private double screenHeight;
    private Group root = new Group();
    private double relX,relY;

    /** MACRO CONTROL */
    private int ratio = 110;
    private float coef1 = 0.050f;
    private float coef2 = 0.04f;
    private float coef3 = 0.02f;
    private float coef4 = 0.01f;
    ///////////////////

    public Parallax(String url){


        panel1 = new ImageView(new Image(url + "parallax04.png"));
        panel2 = new ImageView(new Image(url + "parallax03.png"));
        panel3 = new ImageView(new Image(url + "parallax02.png"));
        panel4 = new ImageView(new Image(url + "parallax01.png"));

    }

    public void initSize(double screenHeight){
        this.screenHeight = screenHeight;

        setRatio(panel4,ratio);
        setRatio(panel3,ratio);
        setRatio(panel2,ratio);
        setRatio(panel1,ratio);
    }

    public void setRatio(ImageView img, double percent){
        img.setFitHeight(screenHeight * percent / 100);
        img.setPreserveRatio(true);
        relX = img.getFitWidth()/2 - img.getFitWidth()/2;
        relY = img.getFitHeight()/2 - img.getFitHeight()/2;
        img.setX(relX);
        img.setY(relY);
        root.getChildren().add(img);
    }

    public void followXSecondaryTarget(double x){
        panel1.setX((panel1.getX()/2 - (x*coef1)));
        panel2.setX((panel2.getX()/2 - (x*coef2)));
        panel3.setX((panel3.getX()/2 - (x*coef3)));
        panel4.setX((panel4.getX()/2 - (x*coef4)));
    }

    public Group getRoot() {
        return root;
    }

}
