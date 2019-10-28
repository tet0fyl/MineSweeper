package Models;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Plateau{
    static byte FACILE = 0;
    static byte NORMAL = 1;
    static byte DIFFICILE = 2;

    Case plateau[][];
    GridPane plateauGUI;
    byte nbDeCase = 10;
    int nbBombe;
    byte modeDifficulte;
    boolean bombeCliquee;
    boolean voirLePlacementDesBombe = false;

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
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                plateau[i][j] = new Case(false);
                if (nbBombe>0 && (int)(Math.random()*100) < 25) {
                    System.out.println("Il y a une bombe en x : " + i + " et y : " + j);
                    plateau[i][j].jaiUneBombe = true;
                    nbBombe--;
                }
            }
        }
        System.out.println("il reste " + nbBombe + " bombe a poser");
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

                btn.setMinWidth(30);
                btn.setMinHeight(30);
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

    public void caseDiscoverStyling(Button btn){
        btn.setTextFill(Color.RED);
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
     * X a droite et gauche. (Ce n'est pas très claire je sais)
     *
     *
     * Ma methode n'est clairement pas optimiser, en plus d'etre lourde, elle ne couvre pas toute les cases si le
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