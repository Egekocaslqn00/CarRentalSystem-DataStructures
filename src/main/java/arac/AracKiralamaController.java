package arac;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AracKiralamaController implements Initializable {

    // --- PENCERE BUTONLARI ---
    @FXML private Button closeButton;
    @FXML private Button minimizeButton;

    // --- TABLO ---
    @FXML private TableView<Arac> resultTableView;
    @FXML private TableColumn<Arac, Void> favoriteColumn, imageColumn;
    @FXML private TableColumn<Arac, String> markaColumn, modelColumn, plakaColumn, sinifColumn, renkColumn, yakitColumn, vitesColumn, durumColumn;
    @FXML private TableColumn<Arac, Integer> yilColumn, kmColumn;
    @FXML private TableColumn<Arac, Double> ucretColumn;

    // --- FİLTRELER ---
    @FXML private TextField searchField, minUcretField, maxUcretField, minYilField, maxYilField;
    @FXML private CheckBox markaAudi, markaBMW, markaMercedes, markaFord, markaToyota, markaHonda, markaVolkswagen;
    @FXML private CheckBox yakitBenzin, yakitDiesel, yakitElektrik;
    @FXML private CheckBox vitesOtomatik, vitesManuel;
    @FXML private CheckBox sinifEkonomi, sinifLuks, sinifSUV;
    @FXML private ComboBox<String> renkComboBox;
    @FXML private CheckBox durumMevcut, durumKiralanmis, durumBakimda, sadeceFavoriler;
    @FXML private Label lblToplamArac, lblMevcut, lblKiralanmis, lblGelir, lblStatus, lblTarih;

    // --- VERİLER ---
    private HashMap<String, Arac> araclarMap = new HashMap<>();
    private Stack<Arac> silinenlerStack = new Stack<>();
    private Queue<String> rezervasyonQueue = new LinkedList<>();
    private PriorityQueue<Arac> bakimPQ = new PriorityQueue<>((a1, a2) -> Integer.compare(a2.getKilometre(), a1.getKilometre()));
    private LinkedList<String> kiralamaLog = new LinkedList<>();
    private TreeMap<Double, List<Arac>> ucretTreeMap = new TreeMap<>();
    private DataManager dataManager = new DataManager();
    private double toplamGelir = 0.0;
    private Main mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();

        // Renk Filtresi
        if (renkComboBox != null) {
            renkComboBox.getItems().addAll("Hepsi", "Beyaz", "Siyah", "Gri", "Kırmızı", "Mavi", "Gümüş");
            renkComboBox.getSelectionModel().selectFirst();
        }

        // PENCERE BUTONLARINI AKTİF ET
        setupWindowButtons();

        loadData();
        startClock();
    }

    // --- PENCERE KAPATMA VE KÜÇÜLTME ---
    private void setupWindowButtons() {
        if (closeButton != null) {
            closeButton.setOnAction(event -> System.exit(0));
        }
        if (minimizeButton != null) {
            minimizeButton.setOnAction(event -> {
                Stage stage = (Stage) minimizeButton.getScene().getWindow();
                stage.setIconified(true);
            });
        }
    }

    private void setupTable() {
        markaColumn.setCellValueFactory(new PropertyValueFactory<>("marka"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        plakaColumn.setCellValueFactory(new PropertyValueFactory<>("plaka"));
        yilColumn.setCellValueFactory(new PropertyValueFactory<>("yil"));
        kmColumn.setCellValueFactory(new PropertyValueFactory<>("kilometre"));
        sinifColumn.setCellValueFactory(new PropertyValueFactory<>("sinifi"));
        renkColumn.setCellValueFactory(new PropertyValueFactory<>("renk"));
        yakitColumn.setCellValueFactory(new PropertyValueFactory<>("yakitTipi"));
        vitesColumn.setCellValueFactory(new PropertyValueFactory<>("vitesTipi"));
        ucretColumn.setCellValueFactory(new PropertyValueFactory<>("gunlukUcret"));
        durumColumn.setCellValueFactory(new PropertyValueFactory<>("durum"));

        ucretColumn.setCellFactory(tc -> new TableCell<>() {
            @Override protected void updateItem(Double p, boolean e) { super.updateItem(p, e); setText(e ? null : String.format("%.0f ₺", p)); }
        });

        durumColumn.setCellFactory(tc -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); }
                else {
                    setText(item);
                    if (item.equals("Mevcut")) setStyle("-fx-background-color: #d4edda; -fx-text-fill: #155724; -fx-alignment: CENTER;");
                    else if (item.equals("Kiralanmış")) setStyle("-fx-background-color: #f8d7da; -fx-text-fill: #721c24; -fx-alignment: CENTER;");
                    else setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        if(imageColumn != null) {
            imageColumn.setCellFactory(p -> new TableCell<>() {
                private final ImageView iv = new ImageView(); { iv.setFitWidth(50); iv.setFitHeight(30); iv.setPreserveRatio(true); }
                @Override protected void updateItem(Void i, boolean e) {
                    super.updateItem(i, e);
                    if(e) setGraphic(null);
                    else {
                        try {
                            Arac a = getTableView().getItems().get(getIndex());
                            java.io.InputStream is = getClass().getResourceAsStream(a.getImagePath());
                            if(is == null) is = getClass().getResourceAsStream("/arac/images/default.jpg");
                            if(is != null) iv.setImage(new Image(is));
                        } catch(Exception ex) { iv.setImage(null); }
                        setGraphic(iv);
                    }
                }
            });
        }
    }

    private void loadData() {
        araclarMap = dataManager.loadData();
        performSearch();
        updateStats();
    }

    // =======================================================================
    // --- BİLGİ PENCERESİ (FULL DETAYLI - RESİMLİ - SAHİP BİLGİLİ) ---
    // =======================================================================
    @FXML
    private void btnBilgi(ActionEvent event) {
        Arac s = resultTableView.getSelectionModel().getSelectedItem();
        if (s == null) { showAlert("Uyarı", "Lütfen bir araç seçin."); return; }

        Stage stage = new Stage();
        stage.setTitle("Araç Kartı: " + s.getPlaka());
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox content = new VBox(15);
        content.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(600);

        // 1. BÜYÜK RESİM
        ImageView imgView = new ImageView();
        try {
            java.io.InputStream is = getClass().getResourceAsStream(s.getImagePath());
            if (is == null) is = getClass().getResourceAsStream("/arac/images/default.jpg");
            if (is != null) {
                imgView.setImage(new Image(is));
                imgView.setFitWidth(400); imgView.setPreserveRatio(true);
                imgView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 2);");
                VBox imgBox = new VBox(imgView); imgBox.setAlignment(Pos.CENTER);
                content.getChildren().add(imgBox);
            }
        } catch (Exception e) {}

        // 2. BAŞLIK
        Label lblHeader = new Label(s.getMarka() + " " + s.getModel());
        lblHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        content.getChildren().add(lblHeader);

        // 3. TEKNİK TABLO
        GridPane grid = new GridPane();
        grid.setHgap(15); grid.setVgap(8);
        grid.setStyle("-fx-background-color: #f8f9fa; -fx-padding: 10; -fx-background-radius: 5;");

        addInfo(grid, "Plaka:", s.getPlaka(), 0, 0);
        addInfo(grid, "Yıl:", String.valueOf(s.getYil()), 0, 1);
        addInfo(grid, "KM:", s.getKilometre() + " km", 0, 2);
        addInfo(grid, "Yakıt:", s.getYakitTipi(), 1, 0);
        addInfo(grid, "Vites:", s.getVitesTipi(), 1, 1);
        addInfo(grid, "Renk:", s.getRenk(), 1, 2);
        addInfo(grid, "Kişi:", s.getYolcuSayisi() + " Kişi", 0, 3);
        addInfo(grid, "Ücret:", s.getGunlukUcret() + " TL", 1, 3);
        content.getChildren().add(grid);

        content.getChildren().add(new Separator());

        // 4. DONANIM & NOTLAR
        Label lblExtra = new Label((s.isKlima()?"❄️ Klima Var  ":"❌ Klima Yok  ") + (s.isOtopark()?"🅿️ Park Sensörü Var":"❌ Park Sensörü Yok"));
        lblExtra.setStyle("-fx-font-weight:bold; -fx-text-fill: #2980b9; -fx-font-size: 14px;");

        Label lblNot = new Label("Not: " + (s.getNotlar() == null || s.getNotlar().isEmpty() ? "Açıklama yok." : s.getNotlar()));
        lblNot.setWrapText(true);
        lblNot.setStyle("-fx-background-color: #fff3cd; -fx-padding: 10; -fx-background-radius: 5;");
        lblNot.setMaxWidth(400); // Taşmayı önlemek için

        content.getChildren().addAll(lblExtra, lblNot, new Separator());

        // 5. SAHİP BİLGİLERİ (İşte burası!)
        Label lblSahipBaslik = new Label("Sahip Bilgileri:");
        lblSahipBaslik.setStyle("-fx-font-weight:bold; -fx-text-fill: #e67e22;");
        GridPane ownerGrid = new GridPane(); ownerGrid.setHgap(10); ownerGrid.setVgap(5);
        addInfo(ownerGrid, "İsim:", s.getSahip(), 0, 0);
        addInfo(ownerGrid, "Tel:", s.getTelefon(), 0, 1);
        addInfo(ownerGrid, "Adres:", s.getAdres(), 0, 2);
        content.getChildren().addAll(lblSahipBaslik, ownerGrid, new Separator());

        // 6. BAKIM BİLGİLERİ
        Label lblBakim = new Label("Bakım Durumu: " + s.getBakimDurumu() + "\nSon Bakım: " + s.getSonBakimTarihi());
        lblBakim.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");
        content.getChildren().add(lblBakim);

        // KAPAT
        Button btnClose = new Button("Kapat");
        btnClose.setOnAction(e -> stage.close());
        btnClose.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand; -fx-pref-width: 200;");
        VBox footer = new VBox(btnClose); footer.setAlignment(Pos.CENTER);
        content.getChildren().add(footer);

        stage.setScene(new Scene(scroll, 450, 650));
        stage.show();
    }

    private void addInfo(GridPane g, String t, String v, int c, int r) {
        Label tl = new Label(t); tl.setStyle("-fx-font-weight:bold; -fx-text-fill:#7f8c8d;");
        Label vl = new Label(v==null?"-":v); vl.setStyle("-fx-text-fill:#2c3e50; -fx-font-weight:bold;");
        g.add(tl, c*2, r); g.add(vl, c*2+1, r);
    }

    // --- FİLTRELEME & DİĞERLERİ ---
    @FXML private void btnAra(ActionEvent event) { performSearch(); }
    private void performSearch() {
        String txt = searchField.getText().toLowerCase();
        List<Arac> filtered = araclarMap.values().stream().filter(a -> {
            if (!txt.isEmpty() && !a.getMarka().toLowerCase().contains(txt) && !a.getModel().toLowerCase().contains(txt)) return false;
            boolean markaSecili = (markaAudi!=null && markaAudi.isSelected()) || (markaBMW!=null && markaBMW.isSelected());
            if (markaSecili && markaAudi.isSelected() && !a.getMarka().equalsIgnoreCase("Audi")) return false;
            return true;
        }).collect(Collectors.toList());
        resultTableView.getItems().setAll(filtered);
    }

    public void addArac(Arac a) { araclarMap.put(a.getId(), a); dataManager.saveData(araclarMap); performSearch(); updateStats(); }
    public void updateArac(Arac a) { araclarMap.put(a.getId(), a); dataManager.saveData(araclarMap); performSearch(); }
    public void addKiralamaKaydi(KiralamaKaydi k) { kiralamaLog.addFirst("[" + LocalDateTime.now() + "] " + k.getAracId()); }
    public void addGelir(double m) { toplamGelir += m; updateStats(); }

    @FXML void btnEkle(ActionEvent e) { openDialog("veriEkle_Guncelle.fxml", null); }
    @FXML void btnGuncelle(ActionEvent e) { Arac s = resultTableView.getSelectionModel().getSelectedItem(); if(s!=null) openDialog("veriEkle_Guncelle.fxml", s); }
    @FXML void btnSil(ActionEvent e) { Arac s = resultTableView.getSelectionModel().getSelectedItem(); if(s!=null) { silinenlerStack.push(s); araclarMap.remove(s.getId()); dataManager.saveData(araclarMap); performSearch(); updateStats(); } }
    @FXML void btnKirala(ActionEvent e) {
        Arac s = resultTableView.getSelectionModel().getSelectedItem();
        if(s!=null && "Mevcut".equals(s.getDurum())) {
            try { FXMLLoader l = new FXMLLoader(getClass().getResource("kiralama_dialog.fxml")); Parent r = l.load(); KiralamaDialog c = l.getController(); c.setData(s, this); new Stage(){{setScene(new Scene(r)); showAndWait();}}; } catch(Exception ex) { ex.printStackTrace(); }
        } else showAlert("Hata", "Müsait araç seçin.");
    }

    @FXML void btnSiraAl(ActionEvent e) { TextInputDialog d = new TextInputDialog(); d.setHeaderText("Müşteri Sırası"); d.showAndWait().ifPresent(ad -> rezervasyonQueue.offer(ad)); }
    @FXML void btnSiradakiMusteri(ActionEvent e) { if(!rezervasyonQueue.isEmpty()) showAlert("Sıradaki", rezervasyonQueue.poll()); else showAlert("Bilgi", "Boş"); }
    @FXML void btnBakimListesi(ActionEvent e) { bakimPQ.clear(); bakimPQ.addAll(araclarMap.values()); StringBuilder sb = new StringBuilder("Bakım:\n"); while(!bakimPQ.isEmpty()){ Arac a=bakimPQ.poll(); sb.append(a.getKilometre()).append(" KM - ").append(a.getPlaka()).append("\n"); } showAlert("Bakım", sb.toString()); }
    @FXML void btnGecmisListesi(ActionEvent e) { StringBuilder sb = new StringBuilder("Geçmiş:\n"); kiralamaLog.forEach(s->sb.append(s).append("\n")); showAlert("Geçmiş", sb.toString()); }
    @FXML void btnGeriAl(ActionEvent e) { if(!silinenlerStack.isEmpty()) { Arac a = silinenlerStack.pop(); addArac(a); } }
    @FXML void btnUcretSirala(ActionEvent e) { ucretTreeMap.clear(); araclarMap.values().forEach(a->ucretTreeMap.computeIfAbsent(a.getGunlukUcret(), k->new ArrayList<>()).add(a)); StringBuilder sb = new StringBuilder("Fiyatlar:\n"); ucretTreeMap.forEach((k,v)->v.forEach(a->sb.append(k).append(" TL - ").append(a.getMarka()).append("\n"))); showAlert("Fiyatlar", sb.toString()); }
    @FXML void btnTemizle(ActionEvent e) { searchField.clear(); performSearch(); }

    private void openDialog(String f, Arac a) { try { FXMLLoader l = new FXMLLoader(getClass().getResource(f)); Parent r = l.load(); VeriEkleGuncelleController c = l.getController(); c.setMainController(this); if(a!=null) c.loadAracForUpdate(a); new Stage(){{setScene(new Scene(r)); showAndWait();}}; } catch(Exception ex) { ex.printStackTrace(); } }
    private void updateStats() { if(lblToplamArac!=null) lblToplamArac.setText(String.valueOf(araclarMap.size())); if(lblGelir!=null) lblGelir.setText(String.format("%.0f ₺", toplamGelir)); if(lblMevcut!=null) lblMevcut.setText(String.valueOf(araclarMap.values().stream().filter(a->"Mevcut".equals(a.getDurum())).count())); if(lblKiralanmis!=null) lblKiralanmis.setText(String.valueOf(araclarMap.values().stream().filter(a->"Kiralanmış".equals(a.getDurum())).count())); }
    private void startClock() { Timeline t = new Timeline(new KeyFrame(Duration.ZERO, e -> { if(lblTarih!=null) lblTarih.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))); }), new KeyFrame(Duration.seconds(1))); t.setCycleCount(Animation.INDEFINITE); t.play(); }
    private void showAlert(String t, String c) { new Alert(AlertType.INFORMATION, c).showAndWait(); }
    public void setMainController(Main m) { this.mainController = m; }
    @FXML void btnExport() {} @FXML void btnResimEkle() {}
}