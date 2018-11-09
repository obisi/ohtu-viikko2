
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito implements KirjanpitoIF {

    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<>();
    }
    
    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}