/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kauppa;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Ostoskori;
import ohtu.verkkokauppa.PankkiInt;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.VarastoInt;
import ohtu.verkkokauppa.ViitegeneraattoriInt;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author bisi
 */
public class KauppaTest {

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void kahdenEriOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 8);
    }

    @Test
    public void kahdenSamanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }
    @Test
    public void yhdenOnnistuneenYhdenLoppuneenLisayksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli mehua, joka on loppu
        k.tilimaksu("pekka", "12345");
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void aloitaAsiointiKutsuminenNollaaEdellisenOstoksenTiedot() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");
        
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 3);
    }
    @Test
    public void uusiViitenumeroJokaiselleTapahtumalle() {
        // luodaan ensin mock-oliot
        PankkiInt pankki = mock(PankkiInt.class);

        ViitegeneraattoriInt viite = mock(ViitegeneraattoriInt.class);
        // määritellään että viitegeneraattori palauttaa viitten ensin 42, sitten 43, sitten 44
        when(viite.uusi()).
                thenReturn(42).
                thenReturn(43).
                thenReturn(44);

        VarastoInt varasto = mock(VarastoInt.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");
        
        // String nimi, int viitenumero, String tililta, String tilille, int summa
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 43, "12345", "33333-44455", 3);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 44, "12345", "33333-44455", 8);
    }
}
