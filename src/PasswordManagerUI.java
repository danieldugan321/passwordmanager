import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.Optional;

public class PasswordManagerApp extends Application {
    private PasswordManager passwordManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Manager");

        passwordManager = new PasswordManager();

        Button generateButton = new Button("Generate Password");
        Button encryptButton = new Button("Encrypt Password");
        Button storeButton = new Button("Store Password");
        Button searchButton = new Button("Search Password");
        Button deleteButton = new Button("Delete Password");

        // Set up button actions
        generateButton.setOnAction(e -> {
            String generatedPassword = passwordManager.generatePassword();
            showAlert("Generated Password", "Your generated password is:\n" + generatedPassword);
        });

        encryptButton.setOnAction(e -> {
            String passwordToEncrypt = // Get the password to encrypt from the user
                    String encryptedPassword = passwordManager.encryptPassword(passwordToEncrypt);
            showAlert("Encrypted Password", "Your encrypted password is:\n" + encryptedPassword);
        });

        storeButton.setOnAction(e -> {
            // Create input dialogs to collect website, username, and password from the user
            TextInputDialog websiteDialog = new TextInputDialog();
            websiteDialog.setTitle("Enter Website");
            websiteDialog.setHeaderText(null);
            websiteDialog.setContentText("Website:");

            TextInputDialog usernameDialog = new TextInputDialog();
            usernameDialog.setTitle("Enter Username");
            usernameDialog.setHeaderText(null);
            usernameDialog.setContentText("Username:");

            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Enter Password");
            passwordDialog.setHeaderText(null);
            passwordDialog.setContentText("Password:");

            // Show the input dialogs and collect user input
            Optional<String> websiteResult = websiteDialog.showAndWait();
            Optional<String> usernameResult = usernameDialog.showAndWait();
            Optional<String> passwordResult = passwordDialog.showAndWait();

            // Check if the user provided all necessary information
            if (websiteResult.isPresent() && usernameResult.isPresent() && passwordResult.isPresent()) {
                String website = websiteResult.get();
                String username = usernameResult.get();
                String password = passwordResult.get();

                // Create a new PasswordEntry object and attempt to store it in the database
                PasswordEntry newPasswordEntry = new PasswordEntry(website, username, password);
                boolean success = passwordManager.storePassword(newPasswordEntry);

                if (success) {
                    showAlert("Password Stored", "Password successfully stored.");
                } else {
                    showAlert("Error", "Failed to store password. Please try again.");
                }
            } else {
                // User canceled one of the input dialogs, inform the user
                showAlert("Input Cancelled", "Password storage cancelled. Please provide all required information.");
            }
        });


        searchButton.setOnAction(e -> {
            // Show a search dialog or form for the user to search for passwords
            String searchQuery = // Get the search query from the user
                    List<PasswordEntry> searchResults = passwordManager.searchPasswords(searchQuery);

            // Display search results to the user
            if (searchResults != null && !searchResults.isEmpty()) {
                // Implement code to display search results in a user-friendly way (e.g., in a dialog or table)
            } else {
                showAlert("No Results", "No passwords matching the search query found.");
            }
        });

        deleteButton.setOnAction(e -> {
            // Show a dialog or form for the user to select a password to delete
            PasswordEntry passwordToDelete = // Get the password to delete from the user
            boolean success = passwordManager.deletePassword(passwordToDelete);

            if (success) {
                showAlert("Password Deleted", "Password successfully deleted.");
            } else {
                showAlert("Error", "Failed to delete password. Please try again.");
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(generateButton, encryptButton, storeButton, searchButton, deleteButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}