package arac;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.time.LocalDate;

public class KiralamaDialog {

    @FXML
    private TextField musteriAdiField;
    @FXML
    private DatePicker baslangicTarihiPicker;
    @FXML
    private Spinner<Integer> gunSayisiSpinner;
    @FXML
    private Label toplamUcretLabel;
    @FXML
    private Button btnKirala;
    @FXML
    private Button btnIptal;

    private Arac selectedArac;
    private AracKiralamaController mainController;
    private boolean confirmed = false;
    private KiralamaKaydi kiralamaKaydi;

    public void setData(Arac arac, AracKiralamaController controller) {
        this.selectedArac = arac;
        this.mainController = controller;

        // Başlangıç tarihini bugüne ayarla
        baslangicTarihiPicker.setValue(LocalDate.now());

        // Gün sayısı spinner'ını ayarla (1-365 gün)
        SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 1);
        gunSayisiSpinner.setValueFactory(valueFactory);

        // Gün sayısı değiştiğinde toplam ücreti güncelle
        gunSayisiSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateTotalPrice();
        });

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int gunSayisi = gunSayisiSpinner.getValue();
        double gunlukUcret = selectedArac.getGunlukUcret();
        double toplamUcret = gunSayisi * gunlukUcret;
        toplamUcretLabel.setText(String.format("Toplam Ücret: %.2f ₺", toplamUcret));
    }

    @FXML
    private void btnKiralaAction(ActionEvent event) {
        try {
            // Validasyon
            if (musteriAdiField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen müşteri adını girin.");
                return;
            }

            String musteriAdi = musteriAdiField.getText();
            LocalDate baslangicTarihi = baslangicTarihiPicker.getValue();
            int gunSayisi = gunSayisiSpinner.getValue();
            LocalDate bitisTarihi = baslangicTarihi.plusDays(gunSayisi);
            double toplamUcret = gunSayisi * selectedArac.getGunlukUcret();

            // Kiralama kaydı oluştur
            String kiralamaId = java.util.UUID.randomUUID().toString();
            kiralamaKaydi = new KiralamaKaydi(
                kiralamaId,
                selectedArac.getId(),
                musteriAdi,
                baslangicTarihi,
                bitisTarihi,
                toplamUcret,
                "Aktif"
            );

            // Aracın durumunu "Kiralanmış" olarak güncelle
            selectedArac.setDurum("Kiralanmış");
            mainController.updateArac(selectedArac);

            // Kiralama kaydını kaydet
            mainController.addKiralamaKaydi(kiralamaKaydi);

            // Geliri güncelle
            mainController.addGelir(toplamUcret);

            confirmed = true;
            showAlert(Alert.AlertType.INFORMATION, "Başarılı",
                "Araç başarıyla kiralandı!\n\nMüşteri: " + musteriAdi +
                "\nGün Sayısı: " + gunSayisi +
                "\nToplam Ücret: " + String.format("%.2f ₺", toplamUcret));

            closeDialog();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Kiralama işlemi başarısız: " + e.getMessage());
        }
    }

    @FXML
    private void btnIptalAction(ActionEvent event) {
        confirmed = false;
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) btnIptal.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public KiralamaKaydi getKiralamaKaydi() {
        return kiralamaKaydi;
    }
}
