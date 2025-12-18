package arac;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Arac {
    private StringProperty id, marka, model, plaka, renk, yakitTipi, durum, sahip, telefon, adres, sinifi, vitesTipi, notlar, imagePath, bakimDurumu;
    private IntegerProperty yil, kilometre, yolcuSayisi, viewCount;
    private DoubleProperty gunlukUcret;
    private BooleanProperty klima, otopark, isFavorite;
    private ObjectProperty<LocalDate> kayitTarihi, lastViewedDate, sonBakimTarihi, gelecekBakimTarihi;

    public Arac(String id, String marka, String model, String plaka, int yil, String renk, String yakitTipi, int kilometre, double ucret, String durum, LocalDate kayit, String sahip, String tel, String adr, String sinif, int yolcu, String vites, boolean klima, boolean otopark, String notlar) {
        this.id = new SimpleStringProperty(id);
        this.marka = new SimpleStringProperty(marka);
        this.model = new SimpleStringProperty(model);
        this.plaka = new SimpleStringProperty(plaka);
        this.yil = new SimpleIntegerProperty(yil);
        this.renk = new SimpleStringProperty(renk);
        this.yakitTipi = new SimpleStringProperty(yakitTipi);
        this.kilometre = new SimpleIntegerProperty(kilometre);
        this.gunlukUcret = new SimpleDoubleProperty(ucret);
        this.durum = new SimpleStringProperty(durum);
        this.kayitTarihi = new SimpleObjectProperty<>(kayit != null ? kayit : LocalDate.now());
        this.sahip = new SimpleStringProperty(sahip);
        this.telefon = new SimpleStringProperty(tel);
        this.adres = new SimpleStringProperty(adr);
        this.sinifi = new SimpleStringProperty(sinif);
        this.yolcuSayisi = new SimpleIntegerProperty(yolcu);
        this.vitesTipi = new SimpleStringProperty(vites);
        this.klima = new SimpleBooleanProperty(klima);
        this.otopark = new SimpleBooleanProperty(otopark);
        this.notlar = new SimpleStringProperty(notlar);

        // Ekstra Özellikler
        this.imagePath = new SimpleStringProperty("/arac/images/default.jpg");
        this.isFavorite = new SimpleBooleanProperty(false);
        this.viewCount = new SimpleIntegerProperty(0);
        this.bakimDurumu = new SimpleStringProperty("İyi");
        this.sonBakimTarihi = new SimpleObjectProperty<>(LocalDate.now().minusMonths(1));
        this.gelecekBakimTarihi = new SimpleObjectProperty<>(LocalDate.now().plusMonths(6));
    }

    // --- GETTER & SETTER ---
    public String getId() { return id.get(); }
    public String getMarka() { return marka.get(); }
    public String getModel() { return model.get(); }
    public String getPlaka() { return plaka.get(); }
    public int getYil() { return yil.get(); }
    public String getYakitTipi() { return yakitTipi.get(); }
    public int getKilometre() { return kilometre.get(); }
    public double getGunlukUcret() { return gunlukUcret.get(); }
    public String getDurum() { return durum.get(); }
    public void setDurum(String d) { this.durum.set(d); }
    public String getImagePath() { return imagePath.get(); }
    public void setImagePath(String p) { this.imagePath.set(p); }
    public String getRenk() { return renk.get(); }
    public String getSinifi() { return sinifi.get(); }
    public String getVitesTipi() { return vitesTipi.get(); }
    public boolean isFavorite() { return isFavorite.get(); }
    public void setFavorite(boolean f) { isFavorite.set(f); }
    public int getViewCount() { return viewCount.get(); }
    public void incrementViewCount() { this.viewCount.set(viewCount.get() + 1); }
    public String getSahip() { return sahip.get(); }
    public String getTelefon() { return telefon.get(); }
    public String getAdres() { return adres.get(); }
    public int getYolcuSayisi() { return yolcuSayisi.get(); }
    public boolean isKlima() { return klima.get(); }
    public boolean isOtopark() { return otopark.get(); }
    public String getNotlar() { return notlar.get(); }
    public LocalDate getKayitTarihi() { return kayitTarihi.get(); }
    public String getBakimDurumu() { return bakimDurumu.get(); }
    public void setBakimDurumu(String s) { this.bakimDurumu.set(s); }
    public LocalDate getSonBakimTarihi() { return sonBakimTarihi.get(); }
    public void setSonBakimTarihi(LocalDate d) { this.sonBakimTarihi.set(d); }
    public LocalDate getGelecekBakimTarihi() { return gelecekBakimTarihi.get(); }
    public void setGelecekBakimTarihi(LocalDate d) { this.gelecekBakimTarihi.set(d); }

    @Override
    public String toString() {
        return String.join("|", getId(), getMarka(), getModel(), getPlaka(), String.valueOf(getYil()),
                getRenk(), getYakitTipi(), String.valueOf(getKilometre()), String.valueOf(getGunlukUcret()), getDurum(),
                getKayitTarihi().toString(), getSahip(), getTelefon(), getAdres(), getSinifi(),
                String.valueOf(getYolcuSayisi()), getVitesTipi(), String.valueOf(isKlima()), String.valueOf(isOtopark()),
                getNotlar(), getImagePath(), String.valueOf(isFavorite()), String.valueOf(getViewCount()),
                getBakimDurumu(), getSonBakimTarihi().toString(), getGelecekBakimTarihi().toString());
    }

    public static Arac fromString(String line) {
        try {
            String[] p = line.split("\\|");
            if (p.length < 10) return null;
            Arac a = new Arac(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]), p[5], p[6],
                    Integer.parseInt(p[7]), Double.parseDouble(p[8]), p[9],
                    p.length > 10 ? parseDate(p[10]) : LocalDate.now(),
                    p.length > 11 ? p[11] : "", p.length > 12 ? p[12] : "", p.length > 13 ? p[13] : "",
                    p.length > 14 ? p[14] : "Ekonomi", p.length > 15 ? Integer.parseInt(p[15]) : 5,
                    p.length > 16 ? p[16] : "Manuel", p.length > 17 && Boolean.parseBoolean(p[17]),
                    p.length > 18 && Boolean.parseBoolean(p[18]), p.length > 19 ? p[19] : "");

            if (p.length > 20 && !p[20].isEmpty()) a.setImagePath(p[20]);
            if (p.length > 21) a.setFavorite(Boolean.parseBoolean(p[21]));
            if (p.length > 22) a.viewCount.set(Integer.parseInt(p[22]));
            if (p.length > 23) a.setBakimDurumu(p[23]);
            if (p.length > 24) a.setSonBakimTarihi(parseDate(p[24]));
            if (p.length > 25) a.setGelecekBakimTarihi(parseDate(p[25]));

            return a;
        } catch (Exception e) { return null; }
    }

    private static LocalDate parseDate(String d) {
        try { return LocalDate.parse(d); } catch (Exception e) { return LocalDate.now(); }
    }
}