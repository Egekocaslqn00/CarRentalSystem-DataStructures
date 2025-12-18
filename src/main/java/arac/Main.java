package arac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        // DÜZELTME: static load yerine nesne oluşturuyoruz ki controller'a erişebilelim
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Login controller'a ulaşıp Main referansını gönderiyoruz (Bağlantı burada kuruluyor)
        Login loginController = loader.getController();
        if (loginController != null) {
            loginController.setMainController(this);
        }

        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED); // Üst çubuğu kaldırır
        stage.setScene(scene);
        stage.setTitle("Giriş Ekranı");
        stage.setResizable(false); // Sabit boyut
        stage.show();
    }

    // Sahne değiştirme metodu
    public void changeScene(String fxmlFile, boolean maximize) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        if (maximize) {
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getVisualBounds().getWidth();
            double screenHeight = screen.getVisualBounds().getHeight();
            primaryStage.setWidth(screenWidth);
            primaryStage.setHeight(screenHeight);
            primaryStage.setX(screen.getVisualBounds().getMinX());
            primaryStage.setY(screen.getVisualBounds().getMinY());
            primaryStage.setResizable(true);
            // primaryStage.setFullScreen(true); // İstersen tam ekran yapabilirsin
        } else {
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
        }

        // Gidilen sayfadaki controller'a da Main referansını veriyoruz
        Object controller = loader.getController();

        // Eğer gittiğin sayfa AracKiralamaController ise:
        if (controller instanceof AracKiralamaController) {
            // DÜZELTME: getMainController yerine setMainController yaptık
            ((AracKiralamaController) controller).setMainController(this);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}