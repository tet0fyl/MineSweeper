package Models;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
                                        J'ai integrer ma class parallax utiliser sur HungryFish pour ce jeu
                j'ai pu mon confronter au fait qu'un "Librairie" même toute petit n'est pas facile a coder pour
                qu'il soit adaptatif.
 */


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

    /**
     * The constructor detect the Screen size and load the different layer to
     * panel1 which is the foreground until panel4 which is the background and set the
     * a ratio to have margin for the parallax effect
     * (you can adjust the ratio above in the MACRO CONTROL section))
     * @param url the url of the directory containing the fourth PNG image
     *            (must be named parallax01, parallax02, parallax03, parallax04)
     */
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

    /**
     * Use in the constructor method to set the size and the position of each images
     * and add them to the group root parallax
     * @param img one of the ImageView of the parallax layer
     * @param percent the percent of the image layer size to have margin for the parallax effect
     *                (can be adjusted in MACRO CONTROL section)
     */
    public void setRatio(ImageView img, double percent){
        img.setFitHeight(screenHeight * percent / 100);
        img.setPreserveRatio(true);
        relX = img.getFitWidth()/2 - img.getFitWidth()/2;
        relY = img.getFitHeight()/2 - img.getFitHeight()/2;
        img.setX(relX);
        img.setY(relY);
        root.getChildren().add(img);
    }

    /**
     * Move the different image with a coef speed (you can adjust it
     * above in the MACRO CONTROL section) to achieve the parallax effect
     * panel1 is the foreground and panel4 the background
     * @param x the x position of the subject where the parallax is apply
     * @param y the y position of the subject where the parallax is apply
     */
    public void move(double x, double y){
        panel1.setX((panel1.getX()/2 - (x*coef1)));
        panel1.setY((panel1.getY()/2 - (y*coef1)));
        panel2.setX((panel2.getX()/2 - (x*coef2)));
        panel2.setY((panel2.getY()/2 - (y*coef2)));
        panel3.setX((panel3.getX()/2 - (x*coef3)));
        panel3.setY((panel3.getY()/2 - (y*coef3)));
        panel4.setX((panel4.getX()/2 - (x*coef4)));
        panel4.setY((panel4.getY()/2 - (y*coef4)));

    }

    public void followXSecondaryTarget(double x){
        panel1.setX((panel1.getX()/2 - (x*coef1)));
        panel2.setX((panel2.getX()/2 - (x*coef2)));
        panel3.setX((panel3.getX()/2 - (x*coef3)));
        panel4.setX((panel4.getX()/2 - (x*coef4)));
    }


    /**
     * Get the group of the four image parallax
     * @return the group
     */
    public Group getRoot() {
        return root;
    }

    public ImageView getPanel1() {
        return panel1;
    }
}
