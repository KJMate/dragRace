/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.util.ArrayList;
import java.util.List;
import vezerlo.Vezerlo;

/**
 *
 * @author kunec
 */
public class Jatekos {

    private String nev;
    private List<AutoT> autok = new ArrayList<AutoT>() ;
    private AutoT auto;
    private int penz;
    private final int kezdoToke = 15000;
    private boolean versenytNyert = false;
    private int xp;
    private int szint;
    private List<String> autoIdLista;
    private String autoID;
    private Vezerlo vezerlo;

    /**
     * meglévő játékos konstruktora
     *
     * @param nev
     * @param penz
     * @param xp
     * @param szint
     * @param autoID
     */
    public Jatekos(String nev, int penz, int xp, int szint, String autoID) {
        this.nev = nev;
        this.penz = penz;
        this.xp = xp;
        this.szint = szint;
        this.autoID = autoID;
    }

    public void vezerloBeallitas(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    /**
     * ha a játékos futamot nyer akkor növekszik a pénze amiből új autót tud
     * venni, valamint nő az xp is
     */
    public void versenytNyer() {
        if (versenytNyert) {
            penz += 2000;
            xp += 50;
        }
    }

    public void autoFeltoltes(AutoT autoT) {
        autok.add(autoT);
    }

    /**
     * ha a játékosnak van elég pénze tud venni új autót
     *
     * @param auto1
     */
    public void autotVesz(AutoT auto1) {
        if (penz >= auto1.getVetelAr() && szint >= auto1.getSzint()) {
            autok.add(auto1);
            penz -= auto1.getVetelAr();
            vezerlo.sikeresVasarlaUzenet();
        } else {
            vezerlo.sikertelenVasarlaUzenet();
        }

    }

    public void autoValasztaVersenyhez(AutoT auto) {
        this.auto = auto;
    }

    /**
     * az autok mentéséhez összefűzi az aut azonosítokat egy adattaggá
     *
     * @param autoT
     */
    public void autoIDkeszees(AutoT autoT) {
        autoID += autoT.getAutoId() + ";";
    }

    /**
     * autoID készítés elött fut le, hogy üres legyen alapból az adattag,
     * ezáltal egy autót sem tárol többször egy felhasználónál
     */
    public void autoIdUrites() {
        autoID = "";
    }

    /**
     * ha a felhasználó elérte a szintlépéshez szükséges xp-t akkor szintet lép
     */
    public void szintLepes() {
//        switch (xp) {
//            case 1:
//
//                break;
//            case 2:
//
//                break;
//
//            case 3:
//
//                break;
//            case 4:
//
//                break;
//        }
        if (xp >= 1000) {
            szint++;
            xp = 0;
        }
    }

    public String getNev() {
        return nev;
    }

    public AutoT getAuto() {
        return auto;
    }

    public List<AutoT> getAutok() {
        return autok;
    }

    public int getPenz() {
        return penz;
    }

    public int getKezdoToke() {
        return kezdoToke;
    }

    public boolean isVersenytNyert() {
        return versenytNyert;
    }

    public int getXp() {
        return xp;
    }

    public int getSzint() {
        return szint;
    }


    public List<String> getAutoIdLista() {
        return autoIdLista;
    }

    public String getAutoID() {
        return autoID;
    }

    public void setVersenytNyert(boolean versenytNyert) {
        this.versenytNyert = versenytNyert;
    }

    public void setAutok(List<AutoT> autok) {
        this.autok = autok;
    }

}
