package Models;

import Model.Parallax;
import Tool.Path;

public class Menu {
    public Model.Parallax parallax;

    public Menu(){
        parallax = new Parallax(Path.urlParallaxBg);
    }
}
