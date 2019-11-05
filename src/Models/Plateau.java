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
    int nbBombePlace;
    String modeDifficulte;
    boolean bombeCliquee;
    int caseSizeGUI = 30;

    Plateau(String modeDifficulte) {
        bombeCliquee = false;
        this.modeDifficulte = modeDifficulte;
        if (modeDifficulte == FACILE)
            nbBombe = 5;
        else if (modeDifficulte == NORMAL)
            nbBombe = 10;
        else if (modeDifficulte == DIFFICILE)
            nbBombe = 15;
        plateau = new Case[nbDeCase][nbDeCase];
        nbBombePlace = nbBombe;
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                Case newCase = new Case(this,i,j);
                plateau[i][j] = newCase;
                if (nbBombePlace>0 && Math.random() > 0.80) {
                    System.out.println("Il y a une bombe en x : " + i + " et y : " + j);
                    newCase.setJaiUneBombe(true);
                    nbBombePlace--;
                }else{
                    newCase.setJaiUneBombe(false);
                }
            }
        }
        System.out.println("il reste " + nbBombePlace + " bombe a poser");
        plateauGUI = createLayerFromPlateau();
    }


    public void jaiCliqueSurUneBombe() {
        bombeCliquee = true;
    }


    public boolean jaiUneBombe( int x, int y) {
        if (plateau[x][y].jaiUneBombe){
                jaiCliqueSurUneBombe();
        }
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

    public int getNbBombe(){
        return nbBombe-nbBombePlace;
    }

    public int getLength(){
        return plateau.length;
    }
/*
    public void caseDiscoveredAllGameOver(GridPane grid){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                Button nextBtn = (Button) getNodeFromGridPane(grid, i, j);
                if(jaiUneBombe(i,j)){
                    caseDiscoverBomb(grid,i,j);
                }else{
                    nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(i, j)));
                }
            }
        }
    }*/

/*
    public boolean isHeWinning(GridPane grid){
        int nbDeCaseDecouverte = 0;
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                Button nextBtn = (Button) getNodeFromGridPane(grid, i, j);
                if(nextBtn.getText().equals("")){
                    nbDeCaseDecouverte++;
                }
            }
        }
        System.out.println(nbDeCaseDecouverte);
        System.out.println(nbBombe);
        if(nbDeCaseDecouverte <= nbBombe){
            return true;
        }else{
            return false;
        }
    }*/

    public void openCase(int x, int y){
        getCase(x,y).openIt();
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }

    public Case getCase(int x,int y) {
        return plateau[x][y];
    }

    ////////////////////////////////////////
    /**                         EXPLICATION DU CODE SUIVANT !
     * J'ai tenté un balayage depuis la source du clique des cases contenant 0 bombe dans le voisinage.
     *
     * Ma methode startWaveDetection va lancer la methode yUnderTargetPrimaryDetectionWave qui
     * va lancer une premiere vague sur l'axe des Y vers le bas de la source, et se diviser en deux vagues de X
     * droite et gauche qui vont relancer deux autres vagues en Y en bas et en haut qui vont encore lancer des vagues de
     * X a droite et gauche. Une fois terminer la methode yAboveTargetPrimaryDetectionWave va ce lancer avec le même process
     * que la precedente mais vers le haut. (Mon explication est peut être flou)
     *
     *
     * Ma methode n'est clairement pas optimiser, en plus d'etre lourde (Notament avec la methode getNodeFromGridPane
     * qui parcourt toute les node du gridpane pour query le node), elle ne couvre pas toute les cases si le
     * chemin est un peu lézardeux et elle repasse plusieurs fois sur des cases déjà découverte.
     *
     * Il faudrait que je cherche au niveau des methodes recursive
     */
/*
    /////////////////////////////////////////////////////////////////////////////////

    public void startWaveDetection(GridPane grid, int x , int y){
        yAboveTargetPrimaryDetectionWave(grid,x,y);
        yUnderTargetPrimaryDetectionWave(grid,x,y);
    }



    private void yUnderTargetPrimaryDetectionWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; y < nbDeCase; y++) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            xTargetRightDetectionWave(grid,x,y);
            xTargetLeftDetectionWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }
    private void yAboveTargetPrimaryDetectionWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; y >= 0; y--) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            xTargetRightDetectionWave(grid,x,y);
            xTargetLeftDetectionWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }

    private void xRightSecondaryWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; x < nbDeCase; x++) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }
    private void xLeftSecondaryWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; x >= 0; x--) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }
    private void yUnderSecondaryWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; y < nbDeCase; y++) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            xRightSecondaryWave(grid,x,y);
            xLeftSecondaryWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }
    private void yAboveSecondaryWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; y >= 0; y--) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            xRightSecondaryWave(grid,x,y);
            xLeftSecondaryWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }

    private void xTargetRightDetectionWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; x >= 0; x--) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            yAboveSecondaryWave(grid,x,y);
            yUnderSecondaryWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }



    private void xTargetLeftDetectionWave(GridPane grid, int x, int y) {
        x=x;
        y=y;
        for (; x < nbDeCase; x++) {
            Button nextBtn = (Button) getNodeFromGridPane(grid, x, y);
            yAboveSecondaryWave(grid,x,y);
            yUnderSecondaryWave(grid,x,y);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }


 */




    /////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////

}