package Models;


import Tool.PathCst;
import Views.ViewMenuPrincipal;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ModelGame {

    private Plateau plateau;
    public Timer timer;
    private Explosion explosion;


    public ModelGame(){
        plateau = new Plateau((byte) 2);
        explosion = new Explosion();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Timer getTimer() {
        return timer;
    }

    public Explosion getExplosion() {
        return explosion;
    }

    public Text initText(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 35));
        t.setFill(Color.WHITE);
        BorderPane.setAlignment(t, Pos.CENTER);
        return t;
    }

    public Button initButton(String text){
        Button b = new Button(text);
        b.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 20));
        b.getStyleClass().add("btn");
        return b;
    }

    public Text initTitle(String text){
        Text t = new Text(text);
        t.setFont(Font.loadFont(ViewMenuPrincipal.class.getResourceAsStream(PathCst.fontSpongeBob), 50));
        t.setFill(Color.WHITE);
        t.setRotate(17);
        BorderPane.setAlignment(t,Pos.CENTER);
        return t;
    }

}
