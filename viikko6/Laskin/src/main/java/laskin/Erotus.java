/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author bisi
 */
public class Erotus implements Komento {

    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private int viimeisin;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {

        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;

    }

    @Override
    public void suorita() {
        viimeisin = sovellus.tulos();
        try {
            int syote = Integer.parseInt(syotekentta.getText());
            sovellus.miinus(syote);
            tuloskentta.setText(Integer.toString(sovellus.tulos()));
        } catch (Exception e) {
            System.out.println("input error");
        }
    }

    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(viimeisin);
        tuloskentta.setText(Integer.toString(sovellus.tulos()));
    }

}
