package com.metroekrem;

// Hat sınıfı (Belirli bir metro hattı)
public class Hat {
    private String isim;        // Hat adı (örneğin: "Kırmızı Hat", "M1", vb.)
    private IstasyonHat baslangic;  // Hat üzerindeki istasyonların sıralı listesi

    public Hat(String isim) {
        this.isim = isim;
        this.baslangic = null;
    }

    public String getIsim() {
        return isim;
    }

    // Hatta istasyon ekleme (sıralı)
    public void istasyonEkle(Istasyon istasyon) {
        IstasyonHat yeniIstasyon = new IstasyonHat(istasyon);

        // İlk istasyon ekleniyorsa
        if (baslangic == null) {
            baslangic = yeniIstasyon;
        } else {
            // Listenin sonuna ekle
            IstasyonHat temp = baslangic;
            while (temp.sonraki != null) {
                temp = temp.sonraki;
            }
            temp.sonraki = yeniIstasyon;
            yeniIstasyon.onceki = temp;  // Çift yönlü bağlantı
        }
    }

    // Hattaki istasyonları sırayla yazdır
    public void istasyonlariYazdir() {
        System.out.println(isim + " istasyonları:");
        IstasyonHat temp = baslangic;
        int sayac = 1;

        while (temp != null) {
            System.out.println(sayac + ". " + temp.getIstasyon().getIsim() +
                    (temp.getIstasyon().isAktarmaNoktasi() ? " (Aktarma Noktası)" : ""));
            temp = temp.sonraki;
            sayac++;
        }
    }

    // Hattın başlangıç istasyonunu döndür
    public IstasyonHat getBaslangic() {
        return baslangic;
    }
}
