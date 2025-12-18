# Araç Kiralama Yönetim Sistemi v2.0 Sunum Rehberi

## 1. Giriş ve Projenin Amacı

**Ne Söylemelisiniz?**

> "Merhaba hocam, değerli arkadaşlar. Bugün sizlere Veri Yapıları dersi projemiz olan 'Araç Kiralama Yönetim Sistemi'nin gelişmiş ikinci versiyonunu sunacağım. Bu projenin temel amacı, bir araç kiralama şirketinin envanterini verimli bir şekilde yönetmek için modern, kullanıcı dostu ve zengin özellikli bir masaüstü uygulaması geliştirmekti. Projemizde, JavaFX ile modern bir arayüz tasarlarken, arka planda HashMap ve TreeMap gibi temel veri yapılarının etkin kullanımını sergilemeyi hedefledik."

**Vurgulanacak Noktalar:**

*   **Projenin Adı:** Araç Kiralama Yönetim Sistemi v2.0
*   **Teknoloji:** JavaFX
*   **Temel Amaç:** Veri yapılarını (HashMap, TreeMap) kullanarak verimli bir envanter yönetim sistemi oluşturmak.
*   **Hedef:** Modern, kullanışlı ve zengin özellikli bir uygulama.

---

## 2. Kullanılan Veri Yapıları ve Nedenleri

**Ne Söylemelisiniz?**

> "Projemizin kalbinde iki temel veri yapısı bulunuyor: **HashMap** ve **TreeMap**.
> 
> *   **HashMap:** Araçlarımızı benzersiz bir ID ile saklamak için HashMap kullandık. Bu yapı, bir araca ID'si üzerinden erişmek istediğimizde bize ortalama O(1) yani sabit zamanlı bir erişim hızı sunuyor. Bu sayede, binlerce araç arasından bir tanesini güncellemek veya silmek gibi işlemler anında gerçekleştirilebiliyor.
> 
> *   **TreeMap:** Araçları günlük kiralama ücretine göre sıralı bir şekilde tutmak için ise TreeMap'i tercih ettik. TreeMap, verileri anahtarlara (bizim durumumuzda fiyata) göre doğal sıralı bir şekilde tutar. Bu, belirli bir fiyat aralığındaki araçları listelemek veya en ucuz/en pahalı araçları bulmak gibi işlemleri oldukça verimli hale getiriyor.
> 
> Bu iki veri yapısını bir arada kullanarak hem hızlı erişim (HashMap) hem de sıralı erişim (TreeMap) avantajlarından faydalandık."

**Tablo ile Destekleyin:**

| Veri Yapısı | Kullanım Amacı                               | Performans Avantajı                                    |
| :---------- | :------------------------------------------- | :----------------------------------------------------- |
| `HashMap`   | Araçları benzersiz ID ile depolama           | O(1) karmaşıklığında hızlı ekleme, silme ve erişim     |
| `TreeMap`   | Araçları fiyata göre sıralı bir şekilde tutma | O(log n) karmaşıklığında sıralı veri erişimi ve aralık sorguları |

---

## 3. Uygulamanın Özellikleri (Demo Kısmı)

Bu bölümde uygulamayı canlı olarak göstererek özellikleri tek tek anlatmalısınız.

**Sırasıyla Anlatılacak Özellikler:**

1.  **Modern Arayüz ve Dashboard:**
    *   "Uygulamayı açtığımızda bizi modern, gradient renk geçişlerine sahip bir arayüz karşılıyor. Üst kısımda projenin genel durumu hakkında hızlı bilgi veren bir **dashboard** bulunuyor. Toplam araç sayısı, mevcut, kiralanmış ve bakımdaki araç sayıları gibi istatistikler anlık olarak güncelleniyor."

2.  **Gelişmiş Filtreleme ve ScrollPane:**
    *   "Sol tarafta, çok sayıda filtreleme seçeneğimiz var. Marka, yakıt tipi, sınıf, ücret aralığı gibi birçok kritere göre arama yapabiliyoruz. Filtre paneli, `ScrollPane` içine yerleştirildiği için, ekrana sığmayan özellikleri aşağı kaydırarak rahatça kullanabiliyoruz."

3.  **Gerçek Zamanlı Arama:**
    *   "Filtrelerin üzerindeki hızlı arama kutusu, yazdığınız anda sonuçları filtreler. Örneğin, 'BMW' yazdığımda tablo anında güncelleniyor. Bu, kullanıcı deneyimini oldukça iyileştiren bir özellik."

4.  **Görsel ve Etkileşimli Tablo:**
    *   "Araç listemiz artık çok daha görsel. Her aracın küçük bir resmini, favori durumunu gösteren bir yıldız ikonu ve görüntülenme sayısını tabloda görebiliyoruz."
    *   "Durum sütunu, aracın durumuna göre (Mevcut, Kiralanmış, Bakımda) farklı renklerle vurgulanıyor. Bu, görsel olarak durumu hızlıca anlamamızı sağlıyor."
    *   "Bir araca tıkladığımızda, görüntülenme sayısı artıyor. Bu, popüler araçları takip etmek için basit bir istatistik sunuyor."

5.  **Favori Sistemi:**
    *   "Yıldız ikonuna tıklayarak bir aracı favorilerinize ekleyip çıkarabilirsiniz. Ardından sol paneldeki 'Sadece Favoriler' filtresiyle sadece favori araçlarınızı listeleyebilirsiniz."

6.  **Tam Ekran Modu:**
    *   "Sağ üstteki butonla veya F11 tuşuyla uygulama tam ekran moduna geçirilebilir. Bu, sunumlar veya daha geniş bir çalışma alanı için oldukça kullanışlı."

7.  **Araç Ekleme ve Güncelleme:**
    *   (Bir araç ekleyerek veya güncelleyerek gösterin.) "Araç ekleme ve güncelleme formlarımız da tam ekran açılarak kullanıcıya rahat bir veri giriş imkanı sunuyor."

8.  **Excel'e Aktarma (Export):**
    *   "Tablodaki mevcut listeyi tek bir tuşla Excel dosyası olarak dışarı aktarabiliyoruz. Bu, raporlama ve veri analizi için önemli bir özellik."
    *   (Butona basıp oluşturulan Excel dosyasını gösterin.)

9.  **Veri Yedekleme:**
    *   "'Yedekle' butonu ile mevcut veri dosyamız olan `araclar.txt`'nin o anki zaman damgasıyla bir yedeğini oluşturabiliyoruz. Bu, veri kaybını önlemek için kritik bir fonksiyondur."

10. **Bildirim Sistemi:**
    *   "Yaptığımız her işlem (kaydetme, silme, yedekleme vb.) sonrasında alt durum çubuğunda kullanıcıyı bilgilendiren dinamik bildirimler çıkıyor. Bu, kullanıcıya geri bildirim vererek ne olduğını anlamasını sağlar."

---

## 4. Kod Yapısı ve Geliştirmeler

**Ne Söylemelisiniz?**

> "Projenin kod yapısını daha modüler ve yönetilebilir hale getirmek için bazı önemli değişiklikler yaptım.
> 
> *   **Genişletilmiş `Arac` Sınıfı:** `Arac.java` sınıfına resim yolu, favori durumu, görüntülenme sayısı gibi 20'den fazla yeni özellik ekledim. Bu, projenin fonksiyonelliğini büyük ölçüde artırdı.
> 
> *   **Yenilenen FXML:** `arackiralama.fxml` dosyasını baştan tasarladım. `ScrollPane`, `BorderPane` gibi modern layout'lar kullanarak daha dinamik ve estetik bir görünüm elde ettim. CSS ile stil özellikleri (gradient renkler, gölgeler, buton tasarımları) ekledim.
> 
> *   **Güçlendirilmiş Controller:** `AracKiralamaController.java` sınıfı, tüm yeni özelliklerin mantığını içeriyor. Gerçek zamanlı arama, istatistik güncelleme, Excel'e aktarma (Apache POI kütüphanesi ile), favori yönetimi gibi tüm işlemler bu sınıfta yönetiliyor.
> 
> *   **Bağımlılık Yönetimi:** Projeye Excel işlemleri için **Apache POI** kütüphanesini Maven aracılığıyla ekledim. `pom.xml` dosyasında gerekli düzenlemeleri yaptım."

---

## 5. Sonuç ve Gelecek Geliştirmeler

**Ne Söylemelisiniz?**

> "Sonuç olarak, bu proje ile veri yapılarının pratik uygulamalarını ve modern bir masaüstü uygulamasının nasıl geliştirilebileceğini kapsamlı bir şekilde deneyimlemiş oldum. Proje, sadece bir ödev olmanın ötesine geçerek, gerçek dünyada kullanılabilecek bir potansiyele ulaştı.
> 
> Gelecekte bu projeye **müşteri yönetimi**, **kiralama geçmişi takibi**, **detaylı raporlama ve grafikler** ve hatta **çoklu dil desteği** gibi özellikler eklenebilir.
> 
> Dinlediğiniz için teşekkür ederim. Sorularınız varsa alabilirim."

**İyi Sunumlar!**
