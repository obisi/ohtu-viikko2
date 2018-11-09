package ohtu.verkkokauppa;

public class Kauppa {

    private VarastoIF varasto;
    private PankkiIF pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriIF viitegeneraattori;
    private String kaupanTili;

    public Kauppa(VarastoIF vif, PankkiIF pif, ViitegeneraattoriIF vgif) {
        varasto = vif;
        pankki = pif;
        viitegeneraattori = vgif;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
