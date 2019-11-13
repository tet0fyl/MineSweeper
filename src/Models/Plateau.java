package Models;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Plateau{
    public static String FACILE = "EASY";
    public static String NORMAL = "MEDIUM";
    public static String DIFFICILE = "HARD";

    Case plateau[][];
    GridPane plateauGUI;
    byte nbDeCase = 15;
    int nbBombe;
    int nbBombePlacee;
    String modeDifficulte;
    boolean bombeCliquee;
    boolean partiGagnee;
    int nbCaseClose;
    int caseSizeGUI = 30;

    Plateau(String modeDifficulte) {
        partiGagnee = false;
        bombeCliquee = false;
        this.modeDifficulte = modeDifficulte;
        if (modeDifficulte == FACILE)
            nbBombe = 5;
        else if (modeDifficulte == NORMAL)
            nbBombe = 10;
        else if (modeDifficulte == DIFFICILE)
            nbBombe = 15;
        plateau = new Case[nbDeCase][nbDeCase];
        nbBombePlacee = nbBombe;
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                Case newCase = new Case(this,i,j);
                plateau[i][j] = newCase;
                if (nbBombePlacee >0 && Math.random() > 0.90) {
                    System.out.println("Il y a une bombe en x : " + i + " et y : " + j);
                    newCase.setJaiUneBombe(true);
                    nbBombePlacee--;
                }else{
                    newCase.setJaiUneBombe(false);
                }
            }
        }
        System.out.println("il reste " + nbBombePlacee + " bombe a poser");
        plateauGUI = createLayerFromPlateau();
        nbCaseClose = plateau.length*plateau.length - nbBombe;
    }


    public void jaiCliqueSurUneBombe() {
        bombeCliquee = true;
    }

    public boolean jaiUneBombe( int x, int y) {
        return plateau[x][y].jaiUneBombe;
    }

    public GridPane createLayerFromPlateau(){
        GridPane g = new GridPane();
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                Button btn = new Button();

                btn.setMinWidth(caseSizeGUI);
                btn.setMinHeight(caseSizeGUI);
                btn.getStyleClass().add("btn");
                g.add(btn,i,j);
            }
        }
        return g;
    }

    public void openCase(int x, int y){
        getCase(x,y).openIt();
    }

    public void revealThePlateau(){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                if(!plateau[i][j].open){
                    plateau[i][j].openIt();
                }
            }
        }
    }

    public int getNbBombe(){
        return nbBombe- nbBombePlacee;
    }

    public int getLength(){
        return plateau.length;
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }

    public Case getCase(int x,int y) {
        return plateau[x][y];
    }

    public boolean getPartiePerdue(){
        return bombeCliquee;
    }

    public boolean getPartieGagnee(){
        return partiGagnee;
    }
}