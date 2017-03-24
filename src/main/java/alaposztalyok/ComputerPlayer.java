/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import adatkezeles.AdatBevitel;
import adatkezeles.AdatInput;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import vezerlo.Vezerlo;

/**
 *
 * @author kunec
 */
public class ComputerPlayer implements Runnable {

    private int kx = 205;
    private int ky;
    private int dx;
    private long ido;
    private AutoT auto;
    private Vezerlo vezerlo;
    private List<AutoT> autok = new ArrayList<>();
    private Jatekos jatekos;
    private double sebesseg;
    private double ertek;
    private int nyertaGep;
    private boolean computerSzalAktivitas;

    public ComputerPlayer(Vezerlo vezerlo, Jatekos jatekos) {
        this.vezerlo = vezerlo;
        this.jatekos = jatekos;
        autoBeolvasas();
    }

    /**
     * irányítja a számítógép mozgását
     */
    @Override
    public void run() {
        // auto.start();
        while (computerSzalAktivitas) {
            try {
                mozog();
                nyertaGep += sebesseg;
                sebesseg -= jatekos.getAuto().getActualSpeed() / 2;
                kx += sebesseg;
                auto.koordinataBeallitasJatekhoz(kx, 75);
                frissit();
                pihen();
            } catch (InterruptedException ex) {
                Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * az aktuális autó alapján, kiszámolja a sebességet és a váltást
     */
    private void mozog() {
        if (auto.getActualGear() == 1) {
            ertek = 17.6;
        } else {
            ertek = 8.5;
        }
        auto.revving(ertek);
        Random rand = new Random();
        int random = rand.nextInt(500);
        Random randomAttetel = new Random();
        double levon = randomAttetel.nextInt(2000) + 500;
        vezerlo.gazAdas(random, random, random);
        if (auto.getActualRev() >= (auto.getMaximumFordulatszam() - random) && auto.getActualGear() != auto.getSebessegfokozatok()) {
            auto.valtas(1, levon);
        }
        double valtasArany = auto.getActualRev() / (1000 - auto.getActualGear() * 150);
        double gyorsulas = (((9.806 * auto.getTomeg()) / auto.getTomeg()) * (auto.getLoero() / 100));
        sebesseg = gyorsulas + valtasArany;

    }

    private void frissit() {
        vezerlo.frissitJatek();
    }

    private void pihen() throws InterruptedException {
        Thread.sleep(1000 / 60);
    }

    /**
     * beolvassa az autókat a számítógép részére
     */
    private void autoBeolvasas() {
        try {
            AdatInput adatInput = AdatBevitel.getPeldany();
           // adatInput.autoKapcsolodas();
            autok = adatInput.listAllAuto();
            autoBeallitas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a játékos szintjéhez mérten autót választ
     */
    public void autoValasztas() {
        Random rand = new Random();
        int index = rand.nextInt(autok.size());

        if (autok.get(index).getSzint() <= jatekos.getSzint()) {
            auto = autok.get(index);
        } else {
            autoValasztas();
        }
       // auto = autok.get(index);
    }

    public void rajzol(Graphics g) {
        auto.rajzol(g);
    }

    /**
     * beolvasás után beállítja az autókat
     */
    private void autoBeallitas() {
        String szinKod;
        for (int i = 0; i < autok.size(); i++) {
            szinKod = String.valueOf(autok.get(i).getSzinKod());
            autok.get(i).setMegadottSzin(Color.decode(szinKod));
            autok.get(i).beallit(205, 75,
                    autok.get(i).getKocsiMintazatString(),
                    autok.get(i).getKocsiKepDetailString(),
                    autok.get(i).getKocsiFekString(),
                    autok.get(i).getKocsiKerekString(), 
                   /*autok.get(i).getKocsiMintazat().getWidth(null),
                    autok.get(i).getKocsiMintazat().getHeight(null),*/
                    autok.get(i).getUltetes(), 
                    autok.get(i).getHatsoLokharitoTavolsag(),
                    autok.get(i).getKardanTengelyTavolsag(), 1);
        }
    }

    /**
     * folyamat aktivitását vezérli
     *
     * @param aktivitas
     */
    public void computerAktivitasValtas(boolean aktivitas) {
        computerSzalAktivitas = aktivitas;
    }

    public int getKx() {
        return kx;
    }

    public int getNyertaGep() {
        return nyertaGep;
    }

    public AutoT getAuto() {
        return auto;
    }
}
