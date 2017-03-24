/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teszt_csomag;

import alaposztalyok.AutoT;
import alaposztalyok.Jatekos;
import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author kunec
 */
public class Teszt {

    public Teszt() {
    }

//Játékos osztály tesztelése
    private final String nev1 = "Máté";
    private final String nev2 = "Márk";
    private final String nev3 = "Dávid";
    private final int penz1 = 10000;
    private final int penz2 = 20000;
    private final int penz3 = 5000;
    private final int xp1 = 800;
    private final int xp2 = 1000;
    private final int xp3 = 950;
    private final int szint1 = 1;
    private final int szint2 = 2;
    private final int szint3 = 1;
    private String autoID;

    private AutoT auto;
    private Jatekos jatekos1;
    private Jatekos jatekos2;
    private Jatekos jatekos3;

    String marka = "";
    String tipus = "";
    int vetelAr = 12000;
    int loero = 12;
    int tomeg = 502;
    int id = 1;
    int maximumFordulatszam = 9000;
    String kocsiMintazat = "";
    String kocsiDetail = "";
    String kocsiFek = "";
    String kocsiKerek = "";
    int ultetes = 5;
    int hatsoLokharitoTavolsag = 10;
    int kardanTengelyTavolsag = 12;
    int szint = 1;
    int meghajtas = 3;
    int sebessegfokozatok = 6;
    Image kocsiKepMintazata;
    Image kocsiKepDetail;
    Image kocsiFekKep;
    Image kocsiKerekKep;
    String szinKod;
    String logo = "";
    Image logoKep;
    private final int aktualisFokozat = 3;

    @Before
    public void setUp() {
        kocsiKepMintazata = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiDetail)).getImage();
        kocsiKepDetail = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiMintazat)).getImage();
        kocsiFekKep = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiFek)).getImage();
        kocsiKerekKep = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiKerek)).getImage();
        logoKep = new ImageIcon(this.getClass().getResource("/logos/" + logo)).getImage();
        jatekos1 = new Jatekos(nev1, penz1, xp1, szint1, autoID);
        jatekos2 = new Jatekos(nev2, penz2, xp2, szint2, autoID);
        jatekos3 = new Jatekos(nev3, penz3, xp3, szint3, autoID);

    }

    @Test
    public void szintLepesTeszt() {
        //felvetés: jatekos1 nem tud szintet lépni
        jatekos1.szintLepes();
        assertFalse(jatekos1.getSzint() == 2);

        //feltevés: jatekos2 tud szintet lépni
        jatekos2.szintLepes();
        assertTrue(jatekos2.getSzint() == 3);

    }

    @Test
    public void versenytNyertTeszt() {
        //feltevés: jatekos1 megnyerte a versenyt és kapott pénzt meg xp-t
        jatekos1.setVersenytNyert(true);
        jatekos1.versenytNyer();
        assertTrue(jatekos1.getPenz() == 12000);
        assertTrue(jatekos1.getXp() == 850);

        //feltevés: jatekos2 nem nyert versenyt így nem kap semmit
        jatekos2.setVersenytNyert(false);
        assertTrue(jatekos2.getPenz() == 20000);
        assertTrue(jatekos2.getXp() == 1000);

        //felvetés: jatekos3 versenyt nyert, kapott xpt és szintet tud lépni
        jatekos3.setVersenytNyert(true);
        jatekos3.versenytNyer();
        jatekos3.szintLepes();

        assertTrue(jatekos3.getSzint() == 2);
        assertTrue(jatekos3.getXp() == 0);//szintlépés miatt 0-nak kellene lennie
        assertTrue(jatekos3.getPenz() == 7000);
    }

    @Test
    public void fokozatVisszaAllitTeszt() {
        //feltételezés: autó aktuális sebesség fokozata 2
        auto.fokozatVisszaAllitas(2);
        assertTrue(auto.getActualGear() == 2);

    }
}
