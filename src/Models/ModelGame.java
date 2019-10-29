package Models;

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
}
