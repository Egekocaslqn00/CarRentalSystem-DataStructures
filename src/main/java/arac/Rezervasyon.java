package arac;

import java.time.LocalDateTime;

public class Rezervasyon {
    private String rezervasyonId;
    private String aracId;
    private String musteriAdi;
    private String telefon;
    private LocalDateTime rezervasyonZamani;
    private int oncelik; // 1-5 arası (1 en yüksek öncelik)
    
    public Rezervasyon(String rezervasyonId, String aracId, String musteriAdi, 
                       String telefon, LocalDateTime rezervasyonZamani, int oncelik) {
        this.rezervasyonId = rezervasyonId;
        this.aracId = aracId;
        this.musteriAdi = musteriAdi;
        this.telefon = telefon;
        this.rezervasyonZamani = rezervasyonZamani;
        this.oncelik = oncelik;
    }
    
    // Getters and Setters
    public String getRezervasyonId() { return rezervasyonId; }
    public void setRezervasyonId(String rezervasyonId) { this.rezervasyonId = rezervasyonId; }
    
    public String getAracId() { return aracId; }
    public void setAracId(String aracId) { this.aracId = aracId; }
    
    public String getMusteriAdi() { return musteriAdi; }
    public void setMusteriAdi(String musteriAdi) { this.musteriAdi = musteriAdi; }
    
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    
    public LocalDateTime getRezervasyonZamani() { return rezervasyonZamani; }
    public void setRezervasyonZamani(LocalDateTime rezervasyonZamani) { this.rezervasyonZamani = rezervasyonZamani; }
    
    public int getOncelik() { return oncelik; }
    public void setOncelik(int oncelik) { this.oncelik = oncelik; }
    
    @Override
    public String toString() {
        return "Rezervasyon{" +
               "id='" + rezervasyonId + '\'' +
               ", müşteri='" + musteriAdi + '\'' +
               ", araç='" + aracId + '\'' +
               ", öncelik=" + oncelik +
               '}';
    }
}
