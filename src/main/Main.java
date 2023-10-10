package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the database connection
        DatabaseConnector databaseConnector = new DatabaseConnector();

        // Initialize the PassLogic with the database connection
        PassLogic passLogic = new PassLogic(databaseConnector);

        // Start the PasswordManagerUI with the initialized PassLogic
        PasswordManagerUI passwordManagerUI = new PasswordManagerUI(passLogic);
        passwordManagerUI.start(primaryStage);
    }
}
