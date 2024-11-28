package ee.mihkel.kalkulaator;

public class Toiduaine {
    String nimi;
    int rasv;
    int sysivesik;
    int valk;

    public Toiduaine(String nimi, int rasv, int sysivesik, int valk) {
        this.nimi = nimi;
        this.rasv = rasv;
        this.sysivesik = sysivesik;
        this.valk = valk;
    }

    public String getNimi() {
        return nimi;
    }

    public int getRasv() {
        return rasv;
    }

    public int getSysivesik() {
        return sysivesik;
    }

    public int getValk() {
        return valk;
    }
}

// {"id":1,"title":"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops","price":109.95,"description":"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday","category":"men's clothing","image":"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","rating":{"rate":3.9,"count":120}}

// {"rasv": 1, "sysivesik": 2, "valk": 4}