package com.metroekrem;

// İstasyon sınıfı (Graf düğümü)
public class Istasyon {
    private String isim;
    private boolean aktarmaNoktasi;
    private Komsu komsuListesi;  // Komşu istasyonlar listesi

    public Istasyon(String isim) {
        this.isim = isim;
        this.aktarmaNoktasi = false;
        this.komsuListesi = null;
    }

    public String getIsim() {
        return isim;
    }

    public boolean isAktarmaNoktasi() {
        return aktarmaNoktasi;
    }

    public void setAktarmaNoktasi(boolean aktarmaNoktasi) {
        this.aktarmaNoktasi = aktarmaNoktasi;
    }

    // Komşu istasyon ekleme
    public void komsuEkle(Istasyon istasyon, String hatIsmi) {
        Komsu yeniKomsu = new Komsu(istasyon, hatIsmi);

        // İlk komşu ekleniyorsa
        if (komsuListesi == null) {
            komsuListesi = yeniKomsu;
        } else {
            // Listeye ekle
            Komsu temp = komsuListesi;
            while (temp.sonraki != null) {
                temp = temp.sonraki;
            }
            temp.sonraki = yeniKomsu;
        }
    }

    // Komşuları getir
    public Komsu getKomsuListesi() {
        return komsuListesi;
    }
}
