package com.metroekrem;

// Metro Ağı ana sınıfı
public class MetroAgi {
    private Istasyon[] istasyonlar;  // Tüm istasyonlar
    private Hat[] hatlar;            // Tüm hatlar
    private int istasyonSayisi;
    private int hatSayisi;
    private int maksIstasyonSayisi;
    private int maksHatSayisi;

    public MetroAgi(int maksIstasyonSayisi, int maksHatSayisi) {
        this.maksIstasyonSayisi = maksIstasyonSayisi;
        this.maksHatSayisi = maksHatSayisi;
        this.istasyonlar = new Istasyon[maksIstasyonSayisi];
        this.hatlar = new Hat[maksHatSayisi];
        this.istasyonSayisi = 0;
        this.hatSayisi = 0;
    }

    // Yeni istasyon ekleme
    public Istasyon istasyonEkle(String isim) {
        if (istasyonSayisi >= maksIstasyonSayisi) {
            System.out.println("Maksimum istasyon sayısına ulaşıldı!");
            return null;
        }

        // İstasyon daha önce eklenmiş mi kontrol et
        for (int i = 0; i < istasyonSayisi; i++) {
            if (istasyonlar[i].getIsim().equals(isim)) {
                return istasyonlar[i];  // Varsa mevcut olanı döndür
            }
        }

        // Yeni istasyon ekle
        Istasyon yeniIstasyon = new Istasyon(isim);
        istasyonlar[istasyonSayisi] = yeniIstasyon;
        istasyonSayisi++;
        return yeniIstasyon;
    }

    // Yeni hat ekleme
    public Hat hatEkle(String isim) {
        if (hatSayisi >= maksHatSayisi) {
            System.out.println("Maksimum hat sayısına ulaşıldı!");
            return null;
        }

        // Hat daha önce eklenmiş mi kontrol et
        for (int i = 0; i < hatSayisi; i++) {
            if (hatlar[i].getIsim().equals(isim)) {
                return hatlar[i];  // Varsa mevcut olanı döndür
            }
        }

        // Yeni hat ekle
        Hat yeniHat = new Hat(isim);
        hatlar[hatSayisi] = yeniHat;
        hatSayisi++;
        return yeniHat;
    }

    // İsimle istasyon bulma
    public Istasyon istasyonBul(String isim) {
        for (int i = 0; i < istasyonSayisi; i++) {
            if (istasyonlar[i].getIsim().equals(isim)) {
                return istasyonlar[i];
            }
        }
        return null;  // Bulunamadı
    }

    // İsimle hat bulma
    public Hat hatBul(String isim) {
        for (int i = 0; i < hatSayisi; i++) {
            if (hatlar[i].getIsim().equals(isim)) {
                return hatlar[i];
            }
        }
        return null;  // Bulunamadı
    }

    // İki istasyon arasında bağlantı oluşturma
    public void baglantiEkle(String istasyon1Isim, String istasyon2Isim, String hatIsmi) {
        Istasyon ist1 = istasyonBul(istasyon1Isim);
        Istasyon ist2 = istasyonBul(istasyon2Isim);

        if (ist1 == null || ist2 == null) {
            System.out.println("İstasyonlardan biri bulunamadı!");
            return;
        }

        // Çift yönlü bağlantı oluştur
        ist1.komsuEkle(ist2, hatIsmi);
        ist2.komsuEkle(ist1, hatIsmi);
    }

    // Hat oluşturma ve istasyonlarını sırayla ekleme
    public void hatOlustur(String hatIsmi, String[] istasyonIsimleri) {
        Hat hat = hatEkle(hatIsmi);

        if (hat == null) {
            return;
        }

        // Önceki istasyon referansı
        Istasyon oncekiIstasyon = null;

        // Hat üzerindeki her istasyonu ekle
        for (String istasyonIsmi : istasyonIsimleri) {
            Istasyon istasyon = istasyonBul(istasyonIsmi);

            // İstasyon yoksa oluştur
            if (istasyon == null) {
                istasyon = istasyonEkle(istasyonIsmi);
            }

            // Hatta ekle
            hat.istasyonEkle(istasyon);

            // Önceki istasyonla bağlantı oluştur
            if (oncekiIstasyon != null) {
                baglantiEkle(oncekiIstasyon.getIsim(), istasyon.getIsim(), hatIsmi);
            }

            oncekiIstasyon = istasyon;
        }
    }

    // Aktarma noktalarını işaretle (birden fazla hatta yer alan istasyonlar)
    public void aktarmaNoktalariniIsaretle() {
        // Her istasyon için bağlı olduğu hat sayısını bul
        for (int i = 0; i < istasyonSayisi; i++) {
            Istasyon istasyon = istasyonlar[i];
            // Hangi hatlara ait olduğunu bulmak için set kullanımını simüle edelim
            String[] baglananHatlar = new String[hatSayisi];
            int hatSayaci = 0;

            // Komşularını kontrol et
            Komsu komsu = istasyon.getKomsuListesi();
            while (komsu != null) {
                // Bu hat daha önce eklendi mi kontrol et
                boolean hatEklendi = false;
                for (int j = 0; j < hatSayaci; j++) {
                    if (baglananHatlar[j].equals(komsu.getHatIsmi())) {
                        hatEklendi = true;
                        break;
                    }
                }

                if (!hatEklendi) {
                    baglananHatlar[hatSayaci] = komsu.getHatIsmi();
                    hatSayaci++;
                }

                komsu = komsu.sonraki;
            }

            // Birden fazla hatta bağlıysa aktarma noktası olarak işaretle
            if (hatSayaci > 1) {
                istasyon.setAktarmaNoktasi(true);
            }
        }
    }

    // İki istasyon arasındaki en kısa yolu bul (BFS algoritması)
    public void enKisaYoluBul(String baslangicIsmi, String bitisIsmi) {
        Istasyon baslangic = istasyonBul(baslangicIsmi);
        Istasyon bitis = istasyonBul(bitisIsmi);

        if (baslangic == null || bitis == null) {
            System.out.println("Başlangıç veya bitiş istasyonu bulunamadı!");
            return;
        }

        // BFS için gerekli veri yapıları
        boolean[] ziyaretEdildi = new boolean[istasyonSayisi];
        Istasyon[] ebeveyn = new Istasyon[istasyonSayisi];
        String[] kullanılanHat = new String[istasyonSayisi];

        // Kuyruk yapısı (basit dizi implementasyonu)
        Istasyon[] kuyruk = new Istasyon[istasyonSayisi];
        int bas = 0;
        int son = 0;

        // Başlangıç istasyonunu işaretle ve kuyruğa ekle
        for (int i = 0; i < istasyonSayisi; i++) {
            if (istasyonlar[i] == baslangic) {
                ziyaretEdildi[i] = true;
                break;
            }
        }
        kuyruk[son++] = baslangic;

        // BFS algoritması
        boolean yolBulundu = false;

        while (bas < son) {
            Istasyon mevcutIstasyon = kuyruk[bas++];

            // Hedef istasyona ulaşıldı mı kontrol et
            if (mevcutIstasyon == bitis) {
                yolBulundu = true;
                break;
            }

            // Komşuları ziyaret et
            Komsu komsu = mevcutIstasyon.getKomsuListesi();
            while (komsu != null) {
                Istasyon komsuIstasyon = komsu.getIstasyon();

                // Komşu ziyaret edildi mi kontrol et
                int komsuIndeks = -1;
                for (int i = 0; i < istasyonSayisi; i++) {
                    if (istasyonlar[i] == komsuIstasyon) {
                        komsuIndeks = i;
                        break;
                    }
                }

                if (komsuIndeks != -1 && !ziyaretEdildi[komsuIndeks]) {
                    ziyaretEdildi[komsuIndeks] = true;
                    ebeveyn[komsuIndeks] = mevcutIstasyon;
                    kullanılanHat[komsuIndeks] = komsu.getHatIsmi();
                    kuyruk[son++] = komsuIstasyon;
                }

                komsu = komsu.sonraki;
            }
        }

        // Yol bulundu mu kontrol et ve yazdır
        if (yolBulundu) {
            System.out.println("En kısa yol (" + baslangicIsmi + " -> " + bitisIsmi + "):");

            // Yolu oluştur (sondan başa doğru)
            Istasyon[] yol = new Istasyon[istasyonSayisi];
            String[] hatlar = new String[istasyonSayisi];
            int yolUzunlugu = 0;

            Istasyon mevcutIstasyon = bitis;
            while (mevcutIstasyon != baslangic) {
                yol[yolUzunlugu] = mevcutIstasyon;

                // Ebeveyn istasyonu bul
                int mevcutIndeks = -1;
                for (int i = 0; i < istasyonSayisi; i++) {
                    if (istasyonlar[i] == mevcutIstasyon) {
                        mevcutIndeks = i;
                        break;
                    }
                }

                hatlar[yolUzunlugu] = kullanılanHat[mevcutIndeks];
                mevcutIstasyon = ebeveyn[mevcutIndeks];
                yolUzunlugu++;
            }

            // Başlangıç istasyonunu da ekle
            yol[yolUzunlugu] = baslangic;
            yolUzunlugu++;

            // Yolu yazdır (baştan sona doğru)
            String mevcutHat = null;
            for (int i = yolUzunlugu - 1; i >= 0; i--) {
                if (i < yolUzunlugu - 1) {
                    String hat = hatlar[i];
                    if (mevcutHat == null || !mevcutHat.equals(hat)) {
                        mevcutHat = hat;
                        System.out.println("  [" + hat + " hattına geç]");
                    }
                }

                System.out.println("  " + (yolUzunlugu - i) + ". " + yol[i].getIsim() +
                        (yol[i].isAktarmaNoktasi() ? " (Aktarma Noktası)" : ""));
            }

            System.out.println("Toplam " + (yolUzunlugu - 1) + " durak.");
        } else {
            System.out.println("Bu iki istasyon arasında yol bulunamadı!");
        }
    }

    // Tüm hatları ve istasyonları yazdır
    public void bilgileriYazdir() {
        System.out.println("METRO AĞI BİLGİLERİ");
        System.out.println("===================");

        System.out.println("\nTÜM HATLAR (" + hatSayisi + "):");
        for (int i = 0; i < hatSayisi; i++) {
            System.out.println("\n" + (i + 1) + ". " + hatlar[i].getIsim());
            hatlar[i].istasyonlariYazdir();
        }

        System.out.println("\nTÜM İSTASYONLAR (" + istasyonSayisi + "):");
        for (int i = 0; i < istasyonSayisi; i++) {
            System.out.print((i + 1) + ". " + istasyonlar[i].getIsim());
            if (istasyonlar[i].isAktarmaNoktasi()) {
                System.out.print(" (Aktarma Noktası)");
            }
            System.out.println();

            // İstasyonun bağlantılarını yazdır
            Komsu komsu = istasyonlar[i].getKomsuListesi();
            if (komsu != null) {
                System.out.println("   Bağlantılar:");
                while (komsu != null) {
                    System.out.println("   - " + komsu.getIstasyon().getIsim() +
                            " (" + komsu.getHatIsmi() + " hattı)");
                    komsu = komsu.sonraki;
                }
            }
        }
    }
}