
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int kasvatuskoko = 5;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono = new int[5];      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) return;
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) return;
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    private void kasvata(){
        int[] temptaulukko = new int[alkioidenLkm+kasvatuskoko];
        for(int i = 0; i<alkioidenLkm; i++){
            temptaulukko[i] = ljono[i];
        }
        ljono = temptaulukko;
    }

    public boolean lisaa(int luku) {
        if(kuuluu(luku)) return false;
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if(ljono.length == alkioidenLkm) kasvata();
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        if(!kuuluu(luku)) return false;
        boolean loytyi = false;
        for(int i = 0; i<alkioidenLkm-1; i++){
            if(ljono[i] == luku) loytyi = true;
            if(loytyi) ljono[i] = ljono[i+1];  
        }
        ljono[alkioidenLkm] = 0;
        alkioidenLkm--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if(alkioidenLkm == 0) return "{}";
        String s = "{";
        for(int i = 0; i<alkioidenLkm-1; i++){
            s+=ljono[i] + ", ";
        }
        s+=ljono[alkioidenLkm-1]+"}";
        return s;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
         int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            if(!a.kuuluu(bTaulu[i]))  a.lisaa(bTaulu[i]);
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            if(!a.kuuluu(bTaulu[i])) a.poista(bTaulu[i]);
        }
        return a;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            a.poista(bTaulu[i]);
        }
 
        return a;
    }
        
}