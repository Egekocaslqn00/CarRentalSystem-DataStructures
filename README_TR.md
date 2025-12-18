# Araç Kiralama Sistemi - Veri Yapıları Projesi

Bu proje, Java ve JavaFX kullanılarak geliştirilmiş bir masaüstü araç kiralama yönetim sistemidir. Proje, veri yapıları dersi kapsamında, temel veri yapılarının pratik uygulamasını göstermek amacıyla oluşturulmuştur.

![Araç Kiralama Sistemi Paneli](dashboard.png)

---

## 💡 Kullanılan Veri Yapıları

Bu projede, sistemin farklı işlevlerini verimli bir şekilde yönetmek için 6 temel veri yapısı kullanılmıştır. Her bir veri yapısının kullanım amacı aşağıda açıklanmıştır:

| Veri Yapısı | Kullanım Alanı | Amaç |
| :--- | :--- | :--- |
| **HashMap** | Araç Veritabanı | Araçları benzersiz bir ID ile `(key, value)` şeklinde saklayarak, araç bilgilerine O(1) karmaşıklığında hızlı erişim sağlar. | 
| **Stack** | Geri Alma İşlemi | Yapılan son işlemleri (örneğin, araç silme) bir yığına atarak, "Geri Al" özelliği ile bu işlemlerin tersine çevrilmesine olanak tanır. | 
| **Priority Queue** | Bakım Yönetimi | Araçların bakım önceliğini (örneğin, en son bakımdan geçen süreye göre) yönetir. En acil bakım gerektiren aracı O(log n) karmaşıklığında bulur. |
| **Linked List** | Kiralama Geçmişi | Her aracın kiralama geçmişini veya genel işlem loglarını zaman sıralı bir şekilde bağlı liste yapısında tutar. |
| **Binary Search Tree** | Fiyata Göre Sıralama/Arama | Araçları günlük kiralama ücretlerine göre ikili arama ağacında organize eder. Bu, araçları fiyata göre verimli bir şekilde listelemeyi ve belirli bir fiyat aralığında aramayı kolaylaştırır. |
| **Dynamic Array (ArrayList)** | Tablo Veri Yönetimi | JavaFX `TableView` bileşeninde gösterilen araç listesini dinamik bir dizi (ObservableList) içinde yönetir. Filtrelenmiş veya sıralanmış verilerin arayüzde esnek bir şekilde gösterimini sağlar. |

---

## 🚀 Projeyi Çalıştırma

Projeyi kendi bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyebilirsiniz:

### Gereksinimler

- **Java Development Kit (JDK)** 11 veya üstü
- **Apache Maven**
- **JavaFX SDK**

### Kurulum Adımları

1.  **Projeyi Klonlayın:**
    ```bash
    git clone https://github.com/Egekocaslqn00/CarRentalSystem-DataStructures.git
    cd CarRentalSystem-DataStructures
    ```

2.  **Maven Bağımlılıklarını Yükleyin:**
    Proje ana dizinindeyken aşağıdaki komutu çalıştırarak gerekli bağımlılıkları (JavaFX vb.) yükleyin.
    ```bash
    mvn clean install
    ```

3.  **Projeyi Çalıştırın:**
    Projeyi Maven üzerinden çalıştırmak için aşağıdaki komutu kullanın:
    ```bash
    mvn javafx:run
    ```

4.  **IDE ile Çalıştırma (IntelliJ IDEA / Eclipse):
    - Projeyi bir Maven projesi olarak IDE'nize import edin.
    - `src/main/java/arac/Main.java` dosyasını bulun.
    - `Main` sınıfını çalıştırarak uygulamayı başlatın.
    - **Not:** Eğer JavaFX global olarak kurulu değilse, IDE'nizin VM seçeneklerine JavaFX modüllerini eklemeniz gerekebilir.

---

## 📁 Proje Yapısı

- `src/main/java/arac/`: Java kaynak kodlarını içerir.
  - `Main.java`: Uygulamanın başlangıç noktası.
  - `AracKiralamaController.java`: Ana panelin (dashboard) mantığını yöneten kontrolcü sınıfı.
  - `Arac.java`: Araç nesnelerinin özelliklerini tanımlayan model sınıfı.
  - `DataManager.java`: Araç verilerini `araclar.txt` dosyasından okuma ve yazma işlemlerini yönetir.
  - Diğer sınıflar: Veri yapıları ve arayüz bileşenlerini yöneten yardımcı sınıflar.
- `src/main/resources/arac/`: FXML arayüz dosyalarını ve CSS stil dosyalarını içerir.
- `araclar.txt`: Araç verilerinin saklandığı metin tabanlı veritabanı.
- `pom.xml`: Projenin Maven bağımlılıklarını ve yapılandırma ayarlarını içerir.
