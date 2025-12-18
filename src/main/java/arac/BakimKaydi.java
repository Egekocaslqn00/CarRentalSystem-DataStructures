package arac;

import java.time.LocalDate;

public class BakimKaydi implements Comparable<BakimKaydi> {
    private String aracId;
    private String aracPlaka;
    private LocalDate sonBakimTarihi;
    private LocalDate gelecekBakimTarihi;
    private int oncelikSeviyesi; // 1-5 arası (1 en acil)
    private String bakimDurumu;
    
    public BakimKaydi(String aracId, String aracPlaka, LocalDate sonBakimTarihi, 
                      LocalDate gelecekBakimTarihi, int oncelikSeviyesi, String bakimDurumu) {
        this.aracId = aracId;
        this.aracPlaka = aracPlaka;
        this.sonBakimTarihi = sonBakimTarihi;
        this.gelecekBakimTarihi = gelecekBakimTarihi;
        this.oncelikSeviyesi = oncelikSeviyesi;
        this.bakimDurumu = bakimDurumu;
    }
    
    @Override
    public int compareTo(BakimKaydi other) {
        // Öncelik seviyesine göre sıralama (küçük numara = yüksek öncelik)
        int oncelikKarsilastirma = Integer.compare(this.oncelikSeviyesi, other.oncelikSeviyesi);
        if (oncelikKarsilastirma != 0) {
            return oncelikKarsilastirma;
        }
        // Eğer öncelik aynıysa, gelecek bakım tarihine göre sırala
        return this.gelecekBakimTarihi.compareTo(other.gelecekBakimTarihi);
    }
    
    // Getters and Setters
    public String getAracId() { return aracId; }
    public void setAracId(String aracId) { this.aracId = aracId; }
    
    public String getAracPlaka() { return aracPlaka; }
    public void setAracPlaka(String aracPlaka) { this.aracPlaka = aracPlaka; }
    
    public LocalDate getSonBakimTarihi() { return sonBakimTarihi; }
    public void setSonBakimTarihi(LocalDate sonBakimTarihi) { this.sonBakimTarihi = sonBakimTarihi; }
    
    public LocalDate getGelecekBakimTarihi() { return gelecekBakimTarihi; }
    public void setGelecekBakimTarihi(LocalDate gelecekBakimTarihi) { this.gelecekBakimTarihi = gelecekBakimTarihi; }
    
    public int getOncelikSeviyesi() { return oncelikSeviyesi; }
    public void setOncelikSeviyesi(int oncelikSeviyesi) { this.oncelikSeviyesi = oncelikSeviyesi; }
    
    public String getBakimDurumu() { return bakimDurumu; }
    public void setBakimDurumu(String bakimDurumu) { this.bakimDurumu = bakimDurumu; }
    
    @Override
    public String toString() {
        return "BakımKaydı{" +
               "plaka='" + aracPlaka + '\'' +
               ", öncelik=" + oncelikSeviyesi +
               ", gelecekBakım=" + gelecekBakimTarihi +
               ", durum='" + bakimDurumu + '\'' +
               '}';
    }
}
