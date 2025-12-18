package arac;

import java.time.LocalDate;

public class KiralamaKaydi {
    private String kiralamaId;
    private String aracId;
    private String musteriAdi;
    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;
    private double toplamUcret;
    private String durum; // "Aktif", "Tamamlandı", "İptal"
    
    public KiralamaKaydi(String kiralamaId, String aracId, String musteriAdi, 
                         LocalDate baslangicTarihi, LocalDate bitisTarihi, 
                         double toplamUcret, String durum) {
        this.kiralamaId = kiralamaId;
        this.aracId = aracId;
        this.musteriAdi = musteriAdi;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.toplamUcret = toplamUcret;
        this.durum = durum;
    }
    
    // Getters and Setters
    public String getKiralamaId() { return kiralamaId; }
    public void setKiralamaId(String kiralamaId) { this.kiralamaId = kiralamaId; }
    
    public String getAracId() { return aracId; }
    public void setAracId(String aracId) { this.aracId = aracId; }
    
    public String getMusteriAdi() { return musteriAdi; }
    public void setMusteriAdi(String musteriAdi) { this.musteriAdi = musteriAdi; }
    
    public LocalDate getBaslangicTarihi() { return baslangicTarihi; }
    public void setBaslangicTarihi(LocalDate baslangicTarihi) { this.baslangicTarihi = baslangicTarihi; }
    
    public LocalDate getBitisTarihi() { return bitisTarihi; }
    public void setBitisTarihi(LocalDate bitisTarihi) { this.bitisTarihi = bitisTarihi; }
    
    public double getToplamUcret() { return toplamUcret; }
    public void setToplamUcret(double toplamUcret) { this.toplamUcret = toplamUcret; }
    
    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
    
    @Override
    public String toString() {
        return kiralamaId + "|" + aracId + "|" + musteriAdi + "|" + 
               baslangicTarihi + "|" + bitisTarihi + "|" + toplamUcret + "|" + durum;
    }
}
