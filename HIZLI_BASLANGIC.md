# ⚡ Hızlı Başlangıç Kılavuzu

## 🎯 5 Dakikada Çalıştırın!

### 1️⃣ Gereksinimleri Kontrol Edin

```bash
# Java versiyonunu kontrol edin (17 veya üzeri olmalı)
java -version

# Maven versiyonunu kontrol edin
mvn -version
```

**Eğer Java 17 yoksa:**
```bash
# Ubuntu/Debian
sudo apt-get install openjdk-17-jdk

# Windows
# https://adoptium.net/ adresinden indirin
```

**Eğer Maven yoksa:**
```bash
# Ubuntu/Debian
sudo apt-get install maven

# Windows
# https://maven.apache.org/download.cgi adresinden indirin
```

---

### 2️⃣ Projeyi Hazırlayın

```bash
# Proje dizinine gidin
cd AracKiralamaYonetimi

# Bağımlılıkları indirin
mvn clean install

# Projeyi derleyin
mvn clean compile
```

---

### 3️⃣ Uygulamayı Çalıştırın

```bash
# Uygulamayı başlatın
mvn javafx:run
```

---

### 4️⃣ Giriş Yapın

**Giriş Bilgileri:**
- Kullanıcı Adı: `admin`
- Şifre: `1234`

---

## 🎮 İlk Adımlar

### Araç Arama
1. Sol panelden "Marka" bölümünden bir marka seçin (örn: BMW)
2. "🔍 Ara" butonuna tıklayın
3. Sonuçlar tabloda görünecektir

### Yeni Araç Ekleme
1. "➕ Araç Ekle" butonuna tıklayın
2. Formu doldurun
3. "Kaydet" butonuna basın

### Favori Ekleme
1. Tabloda bir araca tıklayın
2. "⭐" sütunundaki yıldıza tıklayın
3. Araç favorilere eklenecektir

### Excel'e Aktarma
1. İstediğiniz filtreleri uygulayın
2. "📊 Excel Export" butonuna tıklayın
3. Dosya konumunu seçin
4. Excel dosyası oluşturulacaktır

### Tam Ekran
1. Sağ üstteki "⛶ Tam Ekran (F11)" butonuna tıklayın
2. VEYA F11 tuşuna basın
3. Çıkmak için ESC tuşuna basın

---

## 🔧 Sorun Giderme

### "mvn: command not found" Hatası
Maven kurulu değil. Yukarıdaki kurulum adımlarını takip edin.

### "JAVA_HOME is not set" Hatası
```bash
# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Windows (Sistem Değişkenleri'nden ayarlayın)
JAVA_HOME=C:\Program Files\Java\jdk-17
```

### Resimler Görünmüyor
`src/main/resources/arac/images/` klasörünün var olduğundan emin olun.

### Veri Dosyası Bulunamadı
İlk çalıştırmada `araclar.txt` otomatik oluşturulacaktır. Eğer oluşmadıysa, proje dizininde manuel oluşturun.

---

## 📚 Daha Fazla Bilgi

- **Detaylı Dokümantasyon:** `README_v2.md`
- **Tüm Özellikler:** `OZELLIKLER.md`
- **Sunum Notları:** `Sunum_Rehberi.md`

---

## 🎉 Başarılı!

Artık uygulamayı kullanmaya hazırsınız. İyi çalışmalar!
