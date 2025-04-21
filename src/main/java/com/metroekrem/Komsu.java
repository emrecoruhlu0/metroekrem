package com.metroekrem;

// Komşu istasyon bilgilerini tutan sınıf (Edge)
public class Komsu {
    private Istasyon istasyon;  // Komşu istasyon
    private String hatIsmi;     // Hangi hatta bağlı olduğu
    public Komsu sonraki;       // Bağlı liste yapısı için

    public Komsu(Istasyon istasyon, String hatIsmi) {
        this.istasyon = istasyon;
        this.hatIsmi = hatIsmi;
        this.sonraki = null;
    }

    public Istasyon getIstasyon() {
        return istasyon;
    }

    public String getHatIsmi() {
        return hatIsmi;
    }
}
