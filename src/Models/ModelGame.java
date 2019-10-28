package Models;

public class ModelGame {

    private Plateau plateau;

    public ModelGame(){
        plateau = new Plateau((byte) 2);
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
