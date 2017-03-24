/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vezerlo;

import adatkezeles.AdatBevitel;
import adatkezeles.AdatInput;
import alaposztalyok.AutoT;
import alaposztalyok.ComputerPlayer;
import alaposztalyok.FordulatSzamMutato;
import alaposztalyok.Jatekos;
import alaposztalyok.Zene;
import felulet.AdatPanel;
import felulet.BejelentkezoPanel;
import felulet.BoltPanel;
import felulet.JatekPanel;
import felulet.KezdoPanel;
import felulet.SugoPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kunec
 */
public class Vezerlo implements Runnable {

    private BejelentkezoPanel bejelentkezoPanel;
    private KezdoPanel kezdoPanel;
    private SugoPanel sugoPanel;
    private AdatPanel adatPanel;
    private BoltPanel boltPanel;
    private JatekPanel jatekPanel;

    private List<Jatekos> jatekosok = new ArrayList<>();
    private AdatInput adatInput = AdatBevitel.getPeldany();
    private List<AutoT> autok = new ArrayList<>();

    private int jatekosAutokx = 200;
    private int jatekosAutoky = 200;

    private int gepAutoKx = 300;
    private int gepAutoKy = 200;
    private Jatekos jatekos;
    private ComputerPlayer computerPlayer;
    private FordulatSzamMutato mutato;

    //** -- folyamatosan változó értékek -- **//
    private double autoActualRev;
    private double autoActualSebeseg;
    private int autoActualFokozat;

    //háttérhez
    private int allapot;
    private boolean elindulas;
    private final long kesleltetes = 1000 / 60;

    private Image topLevelHatter = new ImageIcon(this.getClass().getResource("/palyaKepek/road_layer3.jpg")).getImage();
    private Image controlHatter = new ImageIcon(this.getClass().getResource("/palyaKepek/panel.png")).getImage();
    private Image startVonalKep = new ImageIcon(this.getClass().getResource("/palyaKepek/road_start.jpg")).getImage();
    private Image utKoz = new ImageIcon(this.getClass().getResource("/palyaKepek/road_tile.jpg")).getImage();
    private Image celVonalKep = new ImageIcon(this.getClass().getResource("/palyaKepek/finish.png")).getImage();
    private Image inditasLampa = new ImageIcon(this.getClass().getResource("/palyaKepek/start_props.png")).getImage();

    private int megtettUt;
    private final int PALYA_HOSSZ = 50000;

    private final int START_LAMPA_VONAL = 266;
    private final int FELSO_PALYA_VONAL = 200;
    private final float FELSO_KICSINYITESI_ARANY = 0.75f;
    private final int ALSO_PALYA_VONAL = 334;
    private final int RAJTVONAL = 450;
    private int utX;
    private double sebesseg;
    private boolean aktivitas;
    private Thread szal;
    Thread computerSzal;
    private String zene1 = "/hangok/gyorsulas.wav";
    private Zene zene = new Zene(zene1);
    private File fajl;

    /**
     * vezérlő egyik konstruktora az alábbi adatokat a KezdoFramen állítja be
     *
     * @param kezdoPanel
     * @param sugoPanel
     */
    public Vezerlo(KezdoPanel kezdoPanel, SugoPanel sugoPanel) {
        this.kezdoPanel = kezdoPanel;
        this.sugoPanel = sugoPanel;
    }

    /**
     * vezérlő másik konstruktora ezeket az adatokat a JatekFramen állítja be
     *
     * @param bejelentkezoPanel
     * @param adatPanel
     * @param boltPanel
     * @param jatekPanel
     */
    public Vezerlo(BejelentkezoPanel bejelentkezoPanel, AdatPanel adatPanel, BoltPanel boltPanel, JatekPanel jatekPanel) {
        this.bejelentkezoPanel = bejelentkezoPanel;
        this.adatPanel = adatPanel;
        this.boltPanel = boltPanel;
        this.jatekPanel = jatekPanel;
    }

    /**
     * ezt mondjuk nem tudom miért van
     */
    public void frissit() {
        bejelentkezoPanel.repaint();
    }

    /**
     * a textfild értékét kapja meg amit a felhasználó ad és kiválasztja az
     * adatbázisból a megfelelő játékost és értékeit
     *
     * @param text - bejelentkező panel txtfild értéke
     */
    public void bejelentkezes(String text) {
        for (int i = 0; i < jatekosok.size(); i++) {
            if (text.equals(jatekosok.get(i).getNev())) {
                tovabbaSzemelyesFeluletre(jatekosok.get(i));
                break;
            } else {
                try {
                    String uresID = text;
                    adatInput.createJatekos(new Jatekos(text, 150000, 0, 1, uresID));
                    tovabbaSzemelyesFeluletreUjJatekoskent(new Jatekos(text, 150000, 0, 1, uresID));
                } catch (Exception ex) {
                    Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * beolvassa az adatbázisban lévő adatokat mind az autókat, mind a
     * felhasználókat
     */
    public void beolvas() {
        try {
            adatInput.kapcsolodas();
            jatekosok = adatInput.listAll();
            //adatInput.autoKapcsolodas();
            autok = adatInput.listAllAuto();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * átajda az adatpanelnek a felhasználó adatait és betölti a felületet új
     * játékos esetén fut le
     *
     * @param jatekos
     */
    private void tovabbaSzemelyesFeluletreUjJatekoskent(Jatekos jatekos) {
        adatPanel.setVisible(true);
        bejelentkezoPanel.setVisible(false);
        adatPanel.adatAtadas(jatekos);
        jatekos.vezerloBeallitas(this);
    }

    /**
     * átajda az adatpanelnek a felhasználó adatait és betölti a felületet
     *
     * @param jatekos
     */
    private void tovabbaSzemelyesFeluletre(Jatekos jatekos) {
        adatPanel.setVisible(true);
        bejelentkezoPanel.setVisible(false);
        autoLekerdezes(jatekos);
        adatPanel.adatAtadas(jatekos);
        jatekos.vezerloBeallitas(this);
    }

    /**
     * aktuális játékos belép a boltba, ahol alkalma nyílik vásárolni
     *
     * @param jatekos
     */
    public void boltbaLep(Jatekos jatekos) {
        boltPanel.setVisible(true);
        adatPanel.setVisible(false);
        boltPanel.adatKiir(jatekos);
        boltPanel.beolvasottAutokAtadasa(autok);
    }

    /**
     * az aktuális felhasználó a boltból vissza tér az adatpanelre
     *
     * @param jatekos
     */
    public void boltbolKilep(Jatekos jatekos) {
        boltPanel.setVisible(false);
        adatPanel.setVisible(true);
        adatPanel.adatAtadas(jatekos);
    }

    /**
     * az adatpanelen lévő pentés gomb hatására elmenti az aktuális felhasználó
     * adatait
     *
     * @param jatekos
     */
    public void mentes(Jatekos jatekos) {
        String adat;
        try {
            adatInput.updateJatekos(jatekos);
            fajl = new File("src/main/resources/autoAdat/" + jatekos.getNev() + ".txt");
            fajl.createNewFile();
//marja;tipus;logo;szin;ár;
//szint;lóerő;tömeg;kobtenci;meghajás;fordulatszám;sebesség fokozatok;detail;sima;
//kerék;fék;ültetés;kardanTengelyTavolsag;hátó
            if (fajl.exists()) {
                try (PrintWriter pw = new PrintWriter(fajl, "UTF-8")) {
                    for (int i = 0; i < jatekos.getAutok().size(); i++) {
                        adat = jatekos.getAutok().get(i).getMarka()
                                + ";" + jatekos.getAutok().get(i).getTipus() + ";" + jatekos.getAutok().get(i).getLogoString()
                                + ";" + jatekos.getAutok().get(i).getSzinKod() + ";" + jatekos.getAutok().get(i).getVetelAr()
                                + ";" + jatekos.getAutok().get(i).getSzint() + ";" + jatekos.getAutok().get(i).getLoero()
                                + ";" + jatekos.getAutok().get(i).getTomeg() + ";" + jatekos.getAutok().get(i).getKobcenti()
                                + ";" + jatekos.getAutok().get(i).getMeghajtas() + ";" + jatekos.getAutok().get(i).getMaximumFordulatszam()
                                + ";" + jatekos.getAutok().get(i).getSebessegfokozatok() + ";" + jatekos.getAutok().get(i).getKocsiKepDetailString()
                                + ";" + jatekos.getAutok().get(i).getKocsiMintazatString() + ";" + jatekos.getAutok().get(i).getKocsiFekString()
                                + ";" + jatekos.getAutok().get(i).getKocsiKerekString() + ";" + jatekos.getAutok().get(i).getUltetes()
                                + ";" + jatekos.getAutok().get(i).getHatsoLokharitoTavolsag() + ";" + jatekos.getAutok().get(i).getKardanTengelyTavolsag();
                        pw.println(adat);
                        pw.flush();
                    }
                    pw.flush();
                }
            } else {
                System.out.println("problem");
            }
            JOptionPane.showMessageDialog(adatPanel, "Sikeres mentés", "Mentés", 1);
        } catch (Exception ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void kilepVersenyrol() {
        jatekPanel.setVisible(false);
        adatPanel.setVisible(true);
    }

    /**
     * frissíti a játék panelt
     */
    public void jatekPanelFrissit() {
        jatekPanel.repaint();
    }

    /**
     * az adatbázisban tárolt autoidkat, amik az éppen aktuális felhaszálóhoz
     * tartoznak felszabdalja és vissza állítja az utolsó mentést a garázsban
     * lévő autókról
     *
     * @param jatekos
     */
    private void autoLekerdezes(Jatekos jatekos) {
        String[] adatok;
        String marka;
        String tipus;
        int vetelAr;
        int loero;
        int tomeg;
        int maximumFordulatszam;
        String kocsiMintazat;
        String kocsiDetail;
        String kocsiFek;
        String kocsiKerek;
        int ultetes;
        int hatsoLokharitoTavolsag;
        int kardanTengelyTavolsag;
        int szint;
        int meghajtas;
        int sebessegfokozatok;
        String szinKod;
        String logo;
        int kobcenti;
        String sor;

        try (InputStream ins = this.getClass().getResourceAsStream("/autoAdat/" + jatekos.getAutoID());
                Scanner fajlScanner = new Scanner(ins, "UTF-8")) {

            while (fajlScanner.hasNextLine()) {
                sor = fajlScanner.nextLine();
                if (!sor.isEmpty() && !sor.substring(0, 1).equals("/")) {
                    adatok = sor.split(";");
                    marka = adatok[0];
                    tipus = adatok[1];
                    logo = adatok[2];
                    szinKod = adatok[3];
                    vetelAr = Integer.valueOf(adatok[4]);
                    szint = Integer.valueOf(adatok[5]);
                    loero = Integer.valueOf(adatok[6]);
                    tomeg = Integer.valueOf(adatok[7]);
                    kobcenti = Integer.valueOf(adatok[8]);
                    meghajtas = Integer.valueOf(adatok[9]);
                    maximumFordulatszam = Integer.valueOf(adatok[10]);
                    sebessegfokozatok = Integer.valueOf(adatok[11]);
                    kocsiDetail = adatok[12];
                    kocsiMintazat = adatok[13];
                    kocsiKerek = adatok[15];
                    kocsiFek = adatok[14];
                    ultetes = Integer.valueOf(adatok[16]);
                    hatsoLokharitoTavolsag = Integer.valueOf(adatok[17]);
                    kardanTengelyTavolsag = Integer.valueOf(adatok[18]);

                    jatekos.autoFeltoltes(new AutoT(marka, tipus, logo, szinKod, vetelAr, sebessegfokozatok,
                            loero, tomeg, maximumFordulatszam, kocsiMintazat, kocsiDetail, kocsiFek,
                            kocsiKerek, ultetes, hatsoLokharitoTavolsag, kardanTengelyTavolsag, szint, meghajtas, kobcenti));
                }

                /* for (String adat1 : adat) {
                if (!adat1.equals("")) {
                    index1 = Integer.valueOf(adat1);
                    if (autok.get(i).getAutoId() == index1) {
                        jatekos.autoFeltoltes(autok.get(index1 - 1));
                    }
                }
            }*/
                // }
            }
        } catch (IOException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * sikeres vásárlás esetén jelenik meg a felhasználónak
     */
    public void sikeresVasarlaUzenet() {
        JOptionPane.showMessageDialog(boltPanel, "Vásárlás sikeresen megtörtént", "Üzenet", 1);
    }

    /**
     * sikertelen vásárlás esetén jelenik meg a felhasználónak
     */
    public void sikertelenVasarlaUzenet() {
        JOptionPane.showMessageDialog(boltPanel, "Nincs elég pénz vagy alacsony a szint", "Vásárlás nem megengedett", 1);
    }

    /**
     * A súgó gombra kattintva átirányítja a felhaszálót a játék leírását
     * tartalmazó panelre
     */
    public void sugoPanel() {
        sugoPanel.setVisible(true);
        kezdoPanel.setVisible(false);
    }

    @Override
    public void run() {
        computerPlayer.computerAktivitasValtas(true);
        mutatoLetrehozas();
        visszaSzamlalas();
        while (aktivitas) {
            utX += -sebesseg;
            megtettUt += Math.abs(sebesseg);
            jatekos.getAuto().setActualSpeed(sebesseg);
            verseny();
            jatekPanelFrissit();
            versenyVege();
           // idomeres();
            alszik();
        }
    }

    /**
     * beállítja a versenyhez szükséges paramétereket és elindítja
     *
     * @param jatekos
     */
    public void start(Jatekos jatekos) {
        adatPanel.setVisible(false);
        jatekPanel.setVisible(true);
        computerPlayer = new ComputerPlayer(this, jatekos);
        computerPlayer.autoValasztas();
        zene.play();
        aktivitas = true;
        szal = new Thread(this);
        szal.start();
        jatekos.getAuto().koordinataBeallitasJatekhoz(jatekosAutokx, jatekosAutoky);
        this.jatekos = jatekos;

    }

    public void frissitJatek() {
        jatekPanel.repaint();
    }

    /**
     * létrehozza a fordulatszám mutatót
     */
    private void mutatoLetrehozas() {
        int mutatokx = 255;
        int mutatoky = 510;
        long ido = 100;
        int aktualisSzog = 100;
        int mutatoMinRev = 800;
        int mutatoMaxRev = 9000;
        mutato = new FordulatSzamMutato(this, mutatokx, mutatoky, aktualisSzog, ido, mutatoMaxRev, mutatoMinRev);
        mutato.start();
    }

    /**
     * autó sebesség fokozatának a váltását teszi lehetővé
     */
    public void valtas() {
        Random randomAttetel = new Random();
        double levon = randomAttetel.nextInt(2000) + 500;
        if (autoActualFokozat != jatekos.getAuto().getSebessegfokozatok()) {
            jatekos.getAuto().valtas(1, levon);
            mutato.valtas(levon);
        }
        jatekPanel.aktualisFokozatotKiir(jatekos.getAuto().getActualGear());
    }

    public void mousePressed(MouseEvent evt) {
        mutato.mousePressed(evt);
        jatekos.getAuto().mousePressed(evt);
    }

    public void mouseReleased(MouseEvent evt) {
        mutato.mouseReleased(evt);
        jatekos.getAuto().mouseReleased(evt);
    }

    /**
     * sebesség számításhoz beállítja az adatokat
     */
    private void verseny() {
        autoActualRev = mutato.getValosFordulatszam();
        autoActualFokozat = this.jatekos.getAuto().getActualGear();
        autoActualSebeseg = this.jatekos.getAuto().getActualSpeed();
        gazAdas(autoActualRev, autoActualFokozat, autoActualSebeseg);
    }

    /**
     * játékos autójának a sebességét számolja ki
     *
     * @param autoActualRev
     * @param autoActualFokozat
     * @param autoActualSebeseg
     */
    public void gazAdas(double autoActualRev, int autoActualFokozat, double autoActualSebeseg) {
        double valtasArany = this.autoActualRev / (1000 - this.autoActualFokozat * 150);
        double gyorsulas = (((9.806 * jatekos.getAuto().getTomeg()) / jatekos.getAuto().getTomeg())
                * (jatekos.getAuto().getLoero() / 100));
        sebesseg = gyorsulas + valtasArany;
        jatekPanel.sebessegetKiir(sebesseg);
    }

    /**
     * kirajzolja a hátteret, a mutatót, a kettő autót és a verszeny végeztével
     * a nyert vagy veszített feliratot
     *
     * @param g
     */
    public void rajzol(Graphics g) {
        if (topLevelHatter != null && controlHatter != null && startVonalKep != null && utKoz != null
                && celVonalKep != null && inditasLampa != null && computerPlayer != null && mutato != null) {
            hatterKirajzolas(g);
            utKirajzolas(g);
            computerPlayer.rajzol(g);
            startLampaKirajzolas(g);
            jatekos.getAuto().rajzol(g);
            interfeszKirajzolas(g);
            allapotKirajzolas(g);
            mutato.rajzol(g);
            if (megtettUt - celVonalKep.getWidth(null) < PALYA_HOSSZ - 500) {
                //System.out.println("csááá");
            } else {
                g.drawImage(celVonalKep, 500, topLevelHatter.getHeight(null), celVonalKep.getWidth(null), celVonalKep.getHeight(null) + 40, null);

            }
        }
        if (megtettUt >= PALYA_HOSSZ && computerPlayer.getNyertaGep() <= 20500) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Tahoma", Font.BOLD, 36));
            g.drawString("NYERTÉL :) ", 250, 250);

        } else if (megtettUt <= PALYA_HOSSZ && computerPlayer.getNyertaGep() >= 20500) {
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 36));
            g.drawString("VESZTETTÉL :( ", 250, 250);
        }
    }

    private void hatterKirajzolas(Graphics g) {
        int kepMagassag = topLevelHatter.getHeight(null);
        int kezdoX = 0, kezdoY = 0, kepSzelesseg = jatekPanel.getWidth();
        g.drawImage(topLevelHatter, kezdoX, kezdoY, kepSzelesseg, kepMagassag, null);
    }

    private void interfeszKirajzolas(Graphics g) {
        int kepMagassag = controlHatter.getHeight(null);
        int kezdoX = 0, kezdoY = jatekPanel.getHeight() - kepMagassag, kepSzelesseg = jatekPanel.getWidth();
        g.drawImage(controlHatter, kezdoX, kezdoY, kepSzelesseg, kepMagassag, null);
    }

    private void utKirajzolas(Graphics g) {
        int kepMagassag = startVonalKep.getHeight(null);
        //double aranyTop = feluletSzelesseg / topLevelHatter.getWidth(null);
        int kepMagassagTop = topLevelHatter.getHeight(null);
        int kezdoX = utX;
        int kezdoY = kepMagassagTop;
        int kepSzelesseg = startVonalKep.getWidth(null) + 10;
        if (megtettUt < 2 * kepSzelesseg) {
            g.drawImage(startVonalKep, kezdoX, kezdoY, kepSzelesseg, startVonalKep.getHeight(null), null);
            g.drawImage(utKoz, kezdoX + kepSzelesseg, kezdoY, kepSzelesseg, kepMagassag, null);
            g.drawImage(utKoz, kezdoX + (2 * kepSzelesseg), kezdoY, kepSzelesseg, kepMagassag, null);
        } else if (megtettUt - kepSzelesseg < PALYA_HOSSZ) {
            int a = Math.round(((float) megtettUt) / kepSzelesseg);
            int b = kepSzelesseg * a;
            int c = megtettUt - b;
            g.drawImage(utKoz, -c - kepSzelesseg, kezdoY, kepSzelesseg, kepMagassag, null);
            g.drawImage(utKoz, -c, kezdoY, kepSzelesseg, kepMagassag, null);
            g.drawImage(utKoz, -c + kepSzelesseg, kezdoY, kepSzelesseg, kepMagassag, null);
        } else {
            sebesseg = 0;
            g.drawImage(utKoz, 0, kezdoY, kepSzelesseg, kepMagassag, null);
            g.drawImage(celVonalKep, kepSzelesseg - celVonalKep.getWidth(null) - 800, kezdoY, celVonalKep.getWidth(null), kepMagassag, null);

        }
    }

    /**
     * szál késleltetése
     */
    private void alszik() {
        try {
            Thread.sleep(kesleltetes);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startLampaKirajzolas(Graphics g) {
        if (megtettUt < 2 * jatekPanel.getWidth()) {
            g.drawImage(inditasLampa, RAJTVONAL + utX - 60, START_LAMPA_VONAL - inditasLampa.getHeight(null), null);
        }
    }

    /**
     * visszaszámol a verseny indításáig
     */
    private void visszaSzamlalas() {
        for (int i = 2; i >= 0; i--) {
            if (i == 0) {
                allapot = 0;
                jatekPanelFrissit();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
                }
                elindulas = true;
                computerSzal = new Thread(computerPlayer);
                computerSzal.start();

            } else {
                try {
                    allapot = i;
                    elindulas = false;
                    jatekPanelFrissit();
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Vezerlo.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * verseny indításhoz szükséges szövegek kirajzolása
     *
     * @param g
     */
    private void allapotKirajzolas(Graphics g) {
        switch (allapot) {
            case 0:
                if (!elindulas) {
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Tahoma", Font.BOLD, 36));
                    g.drawString("Go!", 60, 100);
                }
                break;
            case 1:
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Tahoma", Font.BOLD, 36));
                g.drawString("Set!", 60, 100);
                break;
            case 2:
                g.setColor(Color.RED);
                g.setFont(new Font("Tahoma", Font.BOLD, 36));
                g.drawString("Ready!", 60, 100);
                break;
        }
    }

    /**
     * verseny végeztével, mindent alap helyzetbe állít, ha nyert a felhasználó
     * akkor menti az adatokat és vissza adja az adatPanelt
     */
    private void versenyVege() {
        if (megtettUt >= PALYA_HOSSZ && computerPlayer.getNyertaGep() <= 20500) {
            jatekos.setVersenytNyert(true);
            jatekos.versenytNyer();
            jatekos.szintLepes();
            aktivitas = false;
            elindulas = false;
            computerPlayer.computerAktivitasValtas(false);
            szal = null;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    adatPanel.setVisible(true);
                    jatekPanel.setVisible(false);
                    adatPanel.adatAtadas(jatekos);
                    megtettUt = 0;
                    utX = 0;
                    computerSzal = null;
                    zene.stop();
                    jatekos.autoIdUrites();
                    for (int i = 0; i < jatekos.getAutok().size(); i++) {
                        jatekos.autoIDkeszees(jatekos.getAutok().get(i));
                    }
                    mentes(jatekos);
                    jatekos.getAuto().fokozatVisszaAllitas(1);
                    computerPlayer = null;
                    mutato = null;
                    //jatekos.getAuto().koordinataBeallitasJatekhoz(jatekosAutokx, jatekosAutoky);
                }
            }, 3000);
        } else if (megtettUt <= PALYA_HOSSZ && computerPlayer.getNyertaGep() >= 20500) {
            aktivitas = false;
            elindulas = false;
            szal = null;
            computerPlayer.computerAktivitasValtas(false);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    adatPanel.setVisible(true);
                    jatekPanel.setVisible(false);
                    megtettUt = 0;
                    zene.stop();
                    utX = 0;
                    computerSzal = null;
                    mutato = null;
                    jatekos.getAuto().fokozatVisszaAllitas(1);
                    computerPlayer = null;
                    adatPanel.adatAtadas(jatekos);
                }
            }, 3000);
        }
    }

    private void idomeres() {
        try {
            int masodperc = 0;
            int tized = 0;
            int szazad = 0;
            int ezred = 0;
            while (true) {
                ezred += 1;
                if (ezred == 9) {
                    szazad += 1;
                    ezred = 0;
                    if (szazad == 9) {
                        tized += 1;
                        szazad = 0;
                        if (tized == 9) {
                            masodperc += 1;
                            tized = 0;
                        }
                    }
                }
                String t = String.format("%1d,%1d%1d%1d", masodperc, tized, szazad, ezred);
                System.out.println(t);
                Thread.sleep(1);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
