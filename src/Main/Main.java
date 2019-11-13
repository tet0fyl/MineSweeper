package Main;

import Views.ViewHandler;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("L'app n'est pas encore lancé");
        Application.launch(ViewHandler.class);
        System.out.println("L'application se lance");
    }
}
