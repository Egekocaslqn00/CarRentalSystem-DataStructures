package arac;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class VeriEkleGuncelleController {

    @FXML
    private TextField markaField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField plakaField;
    @FXML
    private TextField yilField;
    @FXML
    private TextField renkField;
    @FXML
    private ComboBox<String> yakitTipiComboBox;
    @FXML
    private TextField kilometreField;
    @FXML
    private TextField gunlukUcretField;
    @FXML
    private ComboBox<String> durumComboBox;
    @FXML
    private DatePicker kayitTarihiPicker;
    @FXML
    private TextField sahipField;
    @FXML
    private TextField telefonField;
    @FXML
    private TextField adresField;
    @FXML
    private ComboBox<String> sinifiComboBox;
    @FXML
    private ComboBox<Integer> yolcuSayisiComboBox;
    @FXML
    private ComboBox<String> vitesTipiComboBox;
    @FXML
    private CheckBox klimaCheckBox;
    @FXML
    private CheckBox otoparkCheckBox;
    @FXML
    private TextArea notlarArea;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button closeButton;
    @FXML
    private VBox imagePreviewBox;
    @FXML
    private Button btnResimSec;

    private AracKiralamaController mainController;
    private Arac currentArac;
    private boolean isUpdate = false;
    private String selectedImagePath = null;
    private ImageView imagePreview;

    @FXML
    public void initialize() {
        // ComboBox'ları doldur
        yakitTipiComboBox.getItems().addAll("Benzin", "Diesel", "Hibrid", "Elektrik", "LPG");
        durumComboBox.getItems().addAll("Mevcut", "Kiralanmış", "Bakımda");
        sinifiComboBox.getItems().addAll("Ekonomi", "Komfort", "Lux", "SUV");
        vitesTipiComboBox.getItems().addAll("Otomatik", "Manuel");
        yolcuSayisiComboBox.getItems().addAll(2, 4, 5, 7);

        kayitTarihiPicker.setValue(LocalDate.now());

        // Resim önizleme kutusu oluştur
        if (imagePreviewBox != null) {
            imagePreview = new ImageView();
            imagePreview.setFitWidth(150);
            imagePreview.setFitHeight(100);
            imagePreview.setPreserveRatio(true);
            imagePreview.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");
            imagePreviewBox.getChildren().add(imagePreview);
        }

        // Buton event'leri
        if (minimizeButton != null) {
            minimizeButton.setOnAction(e -> minimizeWindow());
        }
        if (closeButton != null) {
            closeButton.setOnAction(e -> closeWindow());
        }
        if (btnResimSec != null) {
            btnResimSec.setOnAction(e -> selectImage());
        }
    }

    public void setMainController(AracKiralamaController controller) {
        this.mainController = controller;
    }

    public void loadAracForUpdate(Arac arac) {
        this.currentArac = arac;
        this.isUpdate = true;
        this.selectedImagePath = arac.getImagePath();

        markaField.setText(arac.getMarka());
        modelField.setText(arac.getModel());
        plakaField.setText(arac.getPlaka());
        yilField.setText(String.valueOf(arac.getYil()));
        renkField.setText(arac.getRenk());
        yakitTipiComboBox.setValue(arac.getYakitTipi());
        kilometreField.setText(String.valueOf(arac.getKilometre()));
        gunlukUcretField.setText(String.valueOf(arac.getGunlukUcret()));
        durumComboBox.setValue(arac.getDurum());
        kayitTarihiPicker.setValue(arac.getKayitTarihi());
        sahipField.setText(arac.getSahip());
        telefonField.setText(arac.getTelefon());
        adresField.setText(arac.getAdres());
        sinifiComboBox.setValue(arac.getSinifi());
        yolcuSayisiComboBox.setValue(arac.getYolcuSayisi());
        vitesTipiComboBox.setValue(arac.getVitesTipi());
        klimaCheckBox.setSelected(arac.isKlima());
        otoparkCheckBox.setSelected(arac.isOtopark());
        notlarArea.setText(arac.getNotlar());
        
        // Mevcut resmi göster
        if (selectedImagePath != null && imagePreview != null) {
            try {
                Image image = new Image(getClass().getResourceAsStream(selectedImagePath));
                imagePreview.setImage(image);
            } catch (Exception e) {
                // Varsayılan resim göster
            }
        }
    }

    @FXML
    private void btnKaydet(ActionEvent event) {
        try {
            // Validasyon
            if (markaField.getText().isEmpty() || modelField.getText().isEmpty() ||
                plakaField.getText().isEmpty() || yilField.getText().isEmpty() ||
                gunlukUcretField.getText().isEmpty() || durumComboBox.getValue() == null) {
                showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen tüm zorunlu alanları doldurun.");
                return;
            }

            String id = isUpdate ? currentArac.getId() : java.util.UUID.randomUUID().toString();
            
            Arac arac = new Arac(
                    id,
                    markaField.getText(),
                    modelField.getText(),
                    plakaField.getText(),
                    Integer.parseInt(yilField.getText()),
                    renkField.getText(),
                    yakitTipiComboBox.getValue() != null ? yakitTipiComboBox.getValue() : "",
                    Integer.parseInt(kilometreField.getText()),
                    Double.parseDouble(gunlukUcretField.getText()),
                    durumComboBox.getValue(),
                    kayitTarihiPicker.getValue(),
                    sahipField.getText(),
                    telefonField.getText(),
                    adresField.getText(),
                    sinifiComboBox.getValue() != null ? sinifiComboBox.getValue() : "",
                    yolcuSayisiComboBox.getValue() != null ? yolcuSayisiComboBox.getValue() : 0,
                    vitesTipiComboBox.getValue() != null ? vitesTipiComboBox.getValue() : "",
                    klimaCheckBox.isSelected(),
                    otoparkCheckBox.isSelected(),
                    notlarArea.getText()
            );
            
            // Seçilen resmi kaydet
            if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                arac.setImagePath(selectedImagePath);
            }

            if (isUpdate) {
                mainController.updateArac(arac);
                showAlert(Alert.AlertType.INFORMATION, "Başarılı", "Araç başarıyla güncellendi.");
            } else {
                mainController.addArac(arac);
                showAlert(Alert.AlertType.INFORMATION, "Başarılı", "Araç başarıyla eklendi.");
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Sayısal alanları doğru formatta girin.");
        }
    }
    
    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Resim Seç");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Resim Dosyaları", "*.jpg", "*.jpeg", "*.png", "*.gif"),
            new FileChooser.ExtensionFilter("Tüm Dosyalar", "*.*")
        );
        
        File selectedFile = fileChooser.showOpenDialog(btnResimSec.getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Resmi proje klasörüne kopyala
                String imagesDir = getClass().getResource("/arac/images/").getPath();
                File imagesDirFile = new File(imagesDir);
                if (!imagesDirFile.exists()) {
                    imagesDirFile.mkdirs();
                }
                
                String fileName = java.util.UUID.randomUUID().toString() + getFileExtension(selectedFile);
                File destFile = new File(imagesDirFile, fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                selectedImagePath = "/arac/images/" + fileName;
                
                // Önizlemeyi güncelle
                Image image = new Image(selectedFile.toURI().toString());
                imagePreview.setImage(image);
                
                showAlert(Alert.AlertType.INFORMATION, "Başarılı", "Resim başarıyla seçildi.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Resim yüklenirken hata oluştu: " + e.getMessage());
            }
        }
    }
    
    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDot = fileName.lastIndexOf('.');
        return lastDot > 0 ? fileName.substring(lastDot) : "";
    }

    @FXML
    private void btnTemizle(ActionEvent event) {
        markaField.clear();
        modelField.clear();
        plakaField.clear();
        yilField.clear();
        renkField.clear();
        yakitTipiComboBox.setValue(null);
        kilometreField.clear();
        gunlukUcretField.clear();
        durumComboBox.setValue(null);
        kayitTarihiPicker.setValue(LocalDate.now());
        sahipField.clear();
        telefonField.clear();
        adresField.clear();
        sinifiComboBox.setValue(null);
        yolcuSayisiComboBox.setValue(null);
        vitesTipiComboBox.setValue(null);
        klimaCheckBox.setSelected(false);
        otoparkCheckBox.setSelected(false);
        notlarArea.clear();
    }

    private void minimizeWindow() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
