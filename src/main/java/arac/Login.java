package arac;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    private Main mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(this::handleLogin);
        exitButton.setOnAction(e -> System.exit(0));
    }

    public void setMainController(Main main) {
        this.mainController = main;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Basit giriş kontrolü (demo amaçlı)
        if (username.equals("admin") && password.equals("1234")) {
            if (mainController != null) {
                try {
                    // Giriş başarılıysa ana ekrana yönlendir
                    mainController.changeScene("arackiralama.fxml", true);
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Hata", "Ana ekran yüklenirken hata oluştu:\n" + e.getMessage());
                }
            } else {
                showAlert(AlertType.ERROR, "Sistem Hatası", "Main Controller bağlantısı yok!");
            }
        } else {
            showAlert(AlertType.ERROR, "Hata", "Kullanıcı adı veya şifre yanlış!");
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}