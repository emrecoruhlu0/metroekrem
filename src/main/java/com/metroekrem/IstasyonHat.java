package com.metroekrem;

// Hat üzerindeki istasyonları tutan sınıf (çift yönlü bağlı liste)
public class IstasyonHat {
    private Istasyon istasyon;
    public IstasyonHat onceki;   // Önceki istasyon
    public IstasyonHat sonraki;  // Sonraki istasyon

    public IstasyonHat(Istasyon istasyon) {
        this.istasyon = istasyon;
        this.onceki = null;
        this.sonraki = null;
    }

    public Istasyon getIstasyon() {
        return istasyon;
    }
}
