public class PasswordEntry {
    private String website;
    private String username;
    private String encryptedPassword;

    public PasswordEntry(String website, String username, String encryptedPassword) {
        this.website = website;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    // Getters and setters for website, username, and encryptedPassword
}