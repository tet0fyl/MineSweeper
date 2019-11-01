package Models;

public class ModelGame {

    private Plateau plateau;
    public Timer timer;
    private Explosion explosion;


    public ModelGame(String difficulty){
        plateau = new Plateau(difficulty);
        explosion = new Explosion();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Explosion getExplosion() {
        return explosion;
    }
}
