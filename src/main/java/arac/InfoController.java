package arac;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InfoController {

    @FXML private Label lblMarkaModel, lblPlaka, lblYilKm, lblYakitVites, lblUcret, lblDurum, lblSahip, lblTelefon, lblAdres;
    @FXML private ImageView aracResmi;
    @FXML private Button btnKapat;

    public void setAracBilgileri(Arac a) {
        if (a == null) return;

        lblMarkaModel.setText(a.getMarka() + " " + a.getModel());
        lblPlaka.setText(a.getPlaka());
        lblYilKm.setText(a.getYil() + " Model / " + a.getKilometre() + " KM");
        lblYakitVites.setText(a.getYakitTipi() + " / " + a.getVitesTipi());
        lblUcret.setText(a.getGunlukUcret() + " ₺");
        lblDurum.setText(a.getDurum());
        lblSahip.setText(a.getSahip());
        lblTelefon.setText(a.getTelefon());
        lblAdres.setText(a.getAdres());

        try {
            String path = a.getImagePath();
            if (path == null || getClass().getResourceAsStream(path) == null) {
                path = "/arac/images/default.jpg";
            }
            aracResmi.setImage(new Image(getClass().getResourceAsStream(path)));
        } catch (Exception e) {
            // Resim yüklenemezse boş kalsın
        }
    }

    @FXML
    public void initialize() {
        if (btnKapat != null) {
            btnKapat.setOnAction(e -> ((Stage) btnKapat.getScene().getWindow()).close());
        }
    }
}