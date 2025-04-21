package com.metroekrem;

public class MetroOrnek {
    public static void main(String[] args) {
        // Yeni bir metro ağı oluştur (maksimum 50 istasyon, 10 hat)
        MetroAgi istanbul = new MetroAgi(100, 10);

        // M1 hattını oluştur (Yenikapı - Atatürk Havalimanı)
        String[] m1Istasyonlari = {
                "Yenikapı", "Aksaray", "Emniyet", "Topkapı", "Bayrampaşa",
                "Sağmalcılar", "Kartaltepe", "Otogar", "Terazidere", "Davutpaşa",
                "Merter", "Zeytinburnu", "Bakırköy", "Bahçelievler", "Ataköy",
                "Yenibosna", "DTM-İstanbul Fuar Merkezi", "Atatürk Havalimanı"
        };
        istanbul.hatOlustur("M1", m1Istasyonlari);

        // M2 hattını oluştur (Yenikapı - Hacıosman)
        String[] m2Istasyonlari = {
                "Yenikapı", "Vezneciler", "Şişhane", "Taksim", "Osmanbey",
                "Şişli-Mecidiyeköy", "Gayrettepe", "Levent", "4.Levent",
                "Sanayi Mahallesi", "ITÜ-Ayazağa", "Atatürk Oto Sanayi",
                "Darüşşafaka", "Hacıosman"
        };
        istanbul.hatOlustur("M2", m2Istasyonlari);

        // M4 hattını oluştur (Kadıköy - Tavşantepe)
        String[] m4Istasyonlari = {
                "Kadıköy", "Ayrılık Çeşmesi", "Acıbadem", "Ünalan", "Göztepe",
                "Yenisahra", "Kozyatağı", "Bostancı", "Küçükyalı", "Maltepe",
                "Huzurevi", "Gülsuyu", "Esenkent", "Hastane-Adliye", "Soğanlık",
                "Kartal", "Yakacık", "Pendik", "Tavşantepe"
        };
        istanbul.hatOlustur("M4", m4Istasyonlari);

        // Marmaray hattını oluştur (Halkalı - Gebze)
        String[] marmarayIstasyonlari = {
                "Halkalı", "Küçükçekmece", "Mustafa Kemal", "Avcılar", "Küçükköy",
                "Florya", "Yeşilköy", "Yeşilyurt", "Ataköy", "Bakırköy", "Yenikapı",
                "Sirkeci", "Üsküdar", "Ayrılık Çeşmesi", "Söğütlüçeşme", "Göztepe",
                "Bostancı", "İçmeler", "Gebze"
        };
        istanbul.hatOlustur("Marmaray", marmarayIstasyonlari);

        // Aktarma noktalarını otomatik belirle
        istanbul.aktarmaNoktalariniIsaretle();

        // Metro ağı bilgilerini yazdır
        istanbul.bilgileriYazdir();

        // İki istasyon arasındaki en kısa yolu bul ve yazdır
        System.out.println("\n");
        istanbul.enKisaYoluBul("Küçükyalı", "Sirkeci");

//        System.out.println("\n");
//        istanbul.enKisaYoluBul("Atatürk Havalimanı", "Hacıosman");
//
//        System.out.println("\n");
//        istanbul.enKisaYoluBul("Kartal", "Bakırköy");
    }
}