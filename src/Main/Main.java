package Main;

import Views.ViewHandler;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Application.launch(ViewHandler.class);
        System.out.println("L'application se lance");
    }
}
