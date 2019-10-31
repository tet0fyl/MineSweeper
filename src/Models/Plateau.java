package Models;

import Tool.Path;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Plateau{
    static byte FACILE = 0;
    static byte NORMAL = 1;
    static byte DIFFICILE = 2;

    Case plateau[][];
    GridPane plateauGUI;
    byte nbDeCase = 15;
    int nbBombe;
    int nbBombePlace;
    byte modeDifficulte;
    boolean bombeCliquee;
    boolean voirLePlacementDesBombe = false;
    int caseSizeGUI = 30;

    Plateau(byte modeDifficulte) {
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
                plateau[i][j] = new Case(false);
                if (nbBombe>0 && Math.random() > 0.93) {
                    System.out.println("Il y a une bombe en x : " + i + " et y : " + j);
                    plateau[i][j].jaiUneBombe = true;
                    nbBombePlace--;
                }
            }
        }
        System.out.println("il reste " + nbBombePlace + " bombe a poser");
        plateauGUI = createLayerFromPlateau();
    }

    public void jaiCliqueSurUneBombe() {
        bombeCliquee = true;
    }

    public byte combienDeBombeDansMonVoisinage(int x, int y) {
        byte nbBombeDansLeVoisinage = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == j || (x+i) == -1 || (y+j) == -1 || (x+i) == nbDeCase || (y+j) == nbDeCase) {
                    continue;
                }
                if (plateau[x+i][y+j].jaiUneBombe) {
                    nbBombeDansLeVoisinage++;
                }
            }
        }

        return nbBombeDansLeVoisinage;
    }

    public boolean jaiUneBombe(int x, int y) {
        if (plateau[x][y].jaiUneBombe)
            jaiCliqueSurUneBombe();
        return plateau[x][y].jaiUneBombe;
    }

    public boolean partiePerdue() {
        return bombeCliquee;
    }

    public GridPane createLayerFromPlateau(){
        GridPane g = new GridPane();
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                Button btn = new Button();

                if(voirLePlacementDesBombe){
                    if(jaiUneBombe(i,j)){
                        btn.setText("B");
                    }else{
                        btn.setText(String.valueOf(combienDeBombeDansMonVoisinage(i,j)));
                    }
                }

                btn.setMinWidth(caseSizeGUI);
                btn.setMinHeight(caseSizeGUI);
                btn.getStyleClass().add("btn");


                /* Affectation d'id que j'ai abandonner par la suite
                btn.setId(i+","+j);
                btn.setText("0");*/

                g.add(btn,i,j);
            }
        }
        return g;
    }

    public GridPane getPlateauGUI() {
        return plateauGUI;
    }

    public byte getNbDeCase() {
        return nbDeCase;
    }

    public int getNbBombe(){
        return nbBombe-nbBombePlace;
    }

    public void caseDiscoverBomb(GridPane grid, int x, int y){
        Button btn = (Button)getNodeFromGridPane(grid,x,y);
        ImageView imgBomb = new ImageView(Path.urlBombImg);
        imgBomb.setFitWidth(caseSizeGUI-20);
        imgBomb.setFitHeight(caseSizeGUI-20);
        btn.setGraphic(imgBomb);

    }

    public void caseDiscoveredAllGameOver(GridPane grid){
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                Button nextBtn = (Button) getNodeFromGridPane(grid, i, j);
                if(jaiUneBombe(i,j)){
                    caseDiscoverBomb(grid,i,j);
                }else{
                    nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(i, j)));
                }
                caseDiscoverStyling(nextBtn);

            }
        }

    }

    public void caseDiscoverStyling(Button btn){

    }

    public void setVoirLePlacementDesBombe(boolean bool){
        voirLePlacementDesBombe = bool;
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
     * Je serais curieux de voir la manière optimiser de le faire.
     */
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
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
            caseDiscoverStyling(nextBtn);
            nextBtn.setText(String.valueOf(combienDeBombeDansMonVoisinage(x, y)));
            if (Integer.valueOf(nextBtn.getText()) > 0) {
                break;
            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    /////////////////////////////////////////////////////////////////////////////////
                        ////////////////////////////////////////
}