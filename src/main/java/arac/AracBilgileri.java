package arac;

import javafx.beans.property.*;
import java.time.LocalDate;

public class AracBilgileri {
    private StringProperty id;
    private StringProperty marka;
    private StringProperty model;
    private StringProperty plaka;
    private IntegerProperty yil;
    private StringProperty renk;
    private StringProperty yakitTipi;
    private IntegerProperty kilometre;
    private DoubleProperty gunlukUcret;
    private StringProperty durum;
    private ObjectProperty<LocalDate> kayitTarihi;
    private StringProperty sahip;
    private StringProperty telefon;
    private StringProperty adres;
    private StringProperty sinifi;
    private IntegerProperty yolcuSayisi;
    private StringProperty vitesTipi;
    private BooleanProperty klima;
    private BooleanProperty otopark;
    private StringProperty notlar;

    // Constructor
    public AracBilgileri() {
        this.id = new SimpleStringProperty();
        this.marka = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.plaka = new SimpleStringProperty();
        this.yil = new SimpleIntegerProperty();
        this.renk = new SimpleStringProperty();
        this.yakitTipi = new SimpleStringProperty();
        this.kilometre = new SimpleIntegerProperty();
        this.gunlukUcret = new SimpleDoubleProperty();
        this.durum = new SimpleStringProperty();
        this.kayitTarihi = new SimpleObjectProperty<>();
        this.sahip = new SimpleStringProperty();
        this.telefon = new SimpleStringProperty();
        this.adres = new SimpleStringProperty();
        this.sinifi = new SimpleStringProperty();
        this.yolcuSayisi = new SimpleIntegerProperty();
        this.vitesTipi = new SimpleStringProperty();
        this.klima = new SimpleBooleanProperty();
        this.otopark = new SimpleBooleanProperty();
        this.notlar = new SimpleStringProperty();
    }

    // Getters ve Setters

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getMarka() {
        return marka.get();
    }

    public void setMarka(String marka) {
        this.marka.set(marka);
    }

    public StringProperty markaProperty() {
        return marka;
    }

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public StringProperty modelProperty() {
        return model;
    }

    public String getPlaka() {
        return plaka.get();
    }

    public void setPlaka(String plaka) {
        this.plaka.set(plaka);
    }

    public StringProperty plakaProperty() {
        return plaka;
    }

    public int getYil() {
        return yil.get();
    }

    public void setYil(int yil) {
        this.yil.set(yil);
    }

    public IntegerProperty yilProperty() {
        return yil;
    }

    public String getRenk() {
        return renk.get();
    }

    public void setRenk(String renk) {
        this.renk.set(renk);
    }

    public StringProperty renkProperty() {
        return renk;
    }

    public String getYakitTipi() {
        return yakitTipi.get();
    }

    public void setYakitTipi(String yakitTipi) {
        this.yakitTipi.set(yakitTipi);
    }

    public StringProperty yakitTipiProperty() {
        return yakitTipi;
    }

    public int getKilometre() {
        return kilometre.get();
    }

    public void setKilometre(int kilometre) {
        this.kilometre.set(kilometre);
    }

    public IntegerProperty kilometreProperty() {
        return kilometre;
    }

    public double getGunlukUcret() {
        return gunlukUcret.get();
    }

    public void setGunlukUcret(double gunlukUcret) {
        this.gunlukUcret.set(gunlukUcret);
    }

    public DoubleProperty gunlukUcretProperty() {
        return gunlukUcret;
    }

    public String getDurum() {
        return durum.get();
    }

    public void setDurum(String durum) {
        this.durum.set(durum);
    }

    public StringProperty durumProperty() {
        return durum;
    }

    public LocalDate getKayitTarihi() {
        return kayitTarihi.get();
    }

    public void setKayitTarihi(LocalDate kayitTarihi) {
        this.kayitTarihi.set(kayitTarihi);
    }

    public ObjectProperty<LocalDate> kayitTarihiProperty() {
        return kayitTarihi;
    }

    public String getSahip() {
        return sahip.get();
    }

    public void setSahip(String sahip) {
        this.sahip.set(sahip);
    }

    public StringProperty sahipProperty() {
        return sahip;
    }

    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public String getAdres() {
        return adres.get();
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
    }

    public StringProperty adresProperty() {
        return adres;
    }

    public String getSinifi() {
        return sinifi.get();
    }

    public void setSinifi(String sinifi) {
        this.sinifi.set(sinifi);
    }

    public StringProperty sinifiProperty() {
        return sinifi;
    }

    public int getYolcuSayisi() {
        return yolcuSayisi.get();
    }

    public void setYolcuSayisi(int yolcuSayisi) {
        this.yolcuSayisi.set(yolcuSayisi);
    }

    public IntegerProperty yolcuSayisiProperty() {
        return yolcuSayisi;
    }

    public String getVitesTipi() {
        return vitesTipi.get();
    }

    public void setVitesTipi(String vitesTipi) {
        this.vitesTipi.set(vitesTipi);
    }

    public StringProperty vitesTipiProperty() {
        return vitesTipi;
    }

    public boolean isKlima() {
        return klima.get();
    }

    public void setKlima(boolean klima) {
        this.klima.set(klima);
    }

    public BooleanProperty klimaProperty() {
        return klima;
    }

    public boolean isOtopark() {
        return otopark.get();
    }

    public void setOtopark(boolean otopark) {
        this.otopark.set(otopark);
    }

    public BooleanProperty otoparkProperty() {
        return otopark;
    }

    public String getNotlar() {
        return notlar.get();
    }

    public void setNotlar(String notlar) {
        this.notlar.set(notlar);
    }

    public StringProperty notlarProperty() {
        return notlar;
    }

    // toListing() metodu
    public Arac toListing() {
        String newId = (getId() != null && !getId().isEmpty()) ? getId() : java.util.UUID.randomUUID().toString();

        Arac arac = new Arac(
                newId,
                getMarka(),
                getModel(),
                getPlaka(),
                getYil(),
                getRenk(),
                getYakitTipi(),
                getKilometre(),
                getGunlukUcret(),
                getDurum(),
                (getKayitTarihi() != null) ? getKayitTarihi() : LocalDate.now(),
                getSahip(),
                getTelefon(),
                getAdres(),
                getSinifi(),
                getYolcuSayisi(),
                getVitesTipi(),
                isKlima(),
                isOtopark(),
                getNotlar()
        );

        return arac;
    }
}
