/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author kunec
 */
public class AutoT extends Thread {

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;

    private int minRev = 800;
    private double actualRev;
    private double actualSpeed;
    private double addrev;
    private int kx;
    private int ky;
    private int kepSzelesseg;
    private int kepMagassag;

    private Image kocsiMintazat;
    private Image kocsiKepDetail;
    private Image kocsiFek;
    private Image kocsiKerek;
    private Image logo;

    private String kocsiMintazatString;
    private String kocsiKepDetailString;
    private String kocsiFekString;
    private String kocsiKerekString;
    private String logoString;

    private int ultetes;
    private int hatsoLokharitoTavolsag;
    private int kardanTengelyTavolsag;
    private int hatsoTengelyX;
    private int hatsoTengelyY;
    private int elsoTengelyX;
    private int elsoTengelyY;
    private int hatsoAgyX;
    private int hatsoAgyY;
    private int elsoAgyX;
    private int elsoAgyY;
    private Color megadottSzin;
    private double szog;
    private final String marka;
    private final String tipus;
    private final int vetelAr;
    private final int sebessegfokozatok;
    private final int loero;
    private final int tomeg;
    private final int maximumFordulatszam;
    private final int szint;
    private final int meghajtas;
    private final int kobcenti;
    private String szinKod;
    private int autoId;
    private int logoKX;
    private int logoKY;
    private int dx;
    private float meretArany;
    private int actualGear = 1;

    /**
     * az adott paraméterekből létrehoz egy új autót
     *
     * @param marka
     * @param tipus
     * @param logoString
     * @param szinKod
     * @param vetelAr
     * @param sebessegFokozatokSzama
     * @param loero
     * @param tomeg
     * @param kocsiMintazatString
     * @param maximumFordulatszam
     * @param kocsiKepDetailString
     * @param kocsiFekString
     * @param kocsiKerekString
     * @param ultetes
     * @param hatsoLokharitoTavolsag
     * @param kardanTengelyTavolsag
     * @param szint
     * @param meghajtas
     * @param kobcenti
     */
    public AutoT(String marka, String tipus, String logoString, String szinKod,
            int vetelAr, int sebessegFokozatokSzama, int loero, int tomeg, int maximumFordulatszam,
            String kocsiMintazatString, String kocsiKepDetailString, String kocsiFekString, String kocsiKerekString, int ultetes,
            int hatsoLokharitoTavolsag, int kardanTengelyTavolsag, int szint, int meghajtas, int kobcenti) {
        // this.autoId = autoId;
        this.marka = marka;
        this.tipus = tipus;
        this.logoString = logoString;
        this.szinKod = szinKod;
        this.vetelAr = vetelAr;
        this.sebessegfokozatok = sebessegFokozatokSzama;
        this.loero = loero;
        this.tomeg = tomeg;
        this.maximumFordulatszam = maximumFordulatszam;
        this.kocsiMintazatString = kocsiMintazatString;
        this.kocsiKepDetailString = kocsiKepDetailString;
        this.kocsiFekString = kocsiFekString;
        this.kocsiKerekString = kocsiKerekString;
        this.ultetes = ultetes;
        this.hatsoLokharitoTavolsag = hatsoLokharitoTavolsag;
        this.kardanTengelyTavolsag = kardanTengelyTavolsag;
        this.szint = szint;
        this.meghajtas = meghajtas;
        this.kobcenti = kobcenti;
    }

    public void kepBeallitas(String kepMinta, String kepDetail, String kepFek, String kepKerek) {

    }

    /**
     * beállítja az autó paramétereit
     *
     * @param kx
     * @param ky
     * @param kepSzelesseg
     * @param kepMagassag
     * @param kocsiMintazat
     * @param kocsiKepDetailS
     * @param fek
     * @param kerek
     * @param ultetes
     * @param hatsoLokX
     * @param kardanX
     * @param meretArany
     */
    public void beallit(int kx, int ky, String kocsiMintazat, String kocsiKepDetailS, String fek, String kerek,
            /*int kepSzelesseg, int kepMagassag,*/ int ultetes, int hatsoLokX, int kardanX, float meretArany) {
        this.kx = (kx - Math.round(kepSzelesseg * meretArany));
        this.ky = (ky - Math.round(kepMagassag * meretArany));
        this.meretArany = meretArany;
        Image kocsiKepDetailT;
        this.kocsiMintazat = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiKepDetailS)).getImage();
        kocsiKepDetailT = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiMintazat)).getImage();
        Image temp = new ImageIcon(dye(kocsiKepDetailT, megadottSzin)).getImage();
        this.kepSzelesseg = this.kocsiMintazat.getWidth(null); //Math.round(meretArany * kepSzelesseg);
        this.kepMagassag = this.kocsiMintazat.getHeight(null); //Math.round(meretArany * kepMagassag);
        this.kocsiKepDetail = temp;
        this.kocsiFek = new ImageIcon(this.getClass().getResource("/autoKepek/" + fek)).getImage();
        this.kocsiKerek = new ImageIcon(this.getClass().getResource("/autoKepek/" + kerek)).getImage();
        this.hatsoLokharitoTavolsag = Math.round(meretArany * hatsoLokX);
        this.kardanTengelyTavolsag = Math.round(meretArany * kardanX);
        this.ultetes = Math.round(meretArany * ultetes);
        this.hatsoTengelyX = hatsoLokharitoTavolsag;
        this.hatsoTengelyY = (int) (kepMagassag - Math.round(meretArany * kocsiFek.getHeight(null)) + (ultetes * meretArany));
        this.elsoTengelyX = hatsoLokharitoTavolsag + kardanTengelyTavolsag;
        this.elsoTengelyY = (int) (kepMagassag - Math.round(meretArany * kocsiFek.getHeight(null)) + (ultetes * meretArany));
        this.hatsoAgyX = Math.round(meretArany * (kocsiFek.getWidth(null) / 2)) - Math.round(meretArany * kocsiKerek.getWidth(null) / 2);
        this.hatsoAgyY = (int) (Math.round(meretArany * kocsiFek.getWidth(null) / 2) - Math.round(meretArany * kocsiKerek.getWidth(null) / 2) + (2 * meretArany));
        this.elsoAgyX = Math.round(meretArany * kocsiFek.getWidth(null) / 2) - Math.round(meretArany * kocsiKerek.getWidth(null) / 2);
        this.elsoAgyY = (int) (Math.round(meretArany * kocsiFek.getWidth(null) / 2) - Math.round(meretArany * kocsiKerek.getWidth(null) / 2) + (2 * meretArany));
    }

    /**
     * ha a játékos már ment egy kört az egyik autóval és újra azt választja a
     * sebesség fokozatot újra egyesbe teszi
     *
     * @param szam
     */
    public void fokozatVisszaAllitas(int szam) {
        actualGear = szam;
    }

    /**
     * fordulatszám folyamata
     *
     * @param rev
     */
    public void revving(double rev) {

        if (rev > 0) {
            actualRev += rev;
            if (actualRev >= maximumFordulatszam) {
                actualRev = maximumFordulatszam;
                szog = 5;
            }
            szog = actualRev;

        } else {
            actualRev += rev;
            if (actualRev <= minRev) {
                actualRev = minRev;
                szog = actualRev;
            }
            szog = actualRev;
        }

    }

    /**
     * az autó váltásáért felelős
     *
     * @param fel
     * @param ujfordulat
     */
    public void valtas(int fel, double ujfordulat) {
        actualGear += fel;
        actualRev -= ujfordulat;
    }

    /**
     * az egérkattintás folyamatát vizsgálja, ha lenyomva van a bal gomb, akkor
     * fut a szál
     *
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }

    /**
     * az egérkattintás folyamatát vizsgálja, amikor felengedjük a bal gombot,
     * akkor megáll a folyamat
     *
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }

    private synchronized boolean checkAndMark() {
        if (isRunning) {
            return false;
        }
        isRunning = true;
        return true;
    }

    /**
     * az aktuális fordulatszámot irányítja
     */
    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        double nobekszik = 0.2;
                        revving(nobekszik);
                        kx += dx;
                    } while (mouseDown);
                    isRunning = false;
                    while (!mouseDown) {
                        double nobekszik = -0.2;
                        revving(nobekszik);
                        kx -= dx;
                    }
                    isRunning = false;
                }
            }.start();
        }
    }

    public void logoBeallitas(String logoKep) {
        this.logo = new ImageIcon(this.getClass().getResource("/logos/" + logoKep)).getImage();
    }

    /**
     * kirajzolja az autót
     *
     * @param g
     */
    public void rajzol(Graphics g) {
        g.drawImage(kocsiFek, kx + hatsoTengelyX, ky + hatsoTengelyY, Math.round(meretArany * kocsiFek.getWidth(null)), Math.round(meretArany * kocsiFek.getHeight(null)), null);
        g.drawImage(kocsiFek, kx + elsoTengelyX, ky + elsoTengelyY, Math.round(meretArany * kocsiFek.getWidth(null)), Math.round(meretArany * kocsiFek.getHeight(null)), null);
        //Layer 2: Kerék kirajzolása.
        g.drawImage(kocsiKerek, kx + hatsoTengelyX + hatsoAgyX, ky + hatsoTengelyY + hatsoAgyY, Math.round(meretArany * kocsiKerek.getWidth(null)), Math.round(meretArany * kocsiKerek.getHeight(null)), null);
        g.drawImage(kocsiKerek, kx + elsoTengelyX + elsoAgyX, ky + elsoTengelyY + elsoAgyY, Math.round(meretArany * kocsiKerek.getWidth(null)), Math.round(meretArany * kocsiKerek.getHeight(null)), null);
        g.drawImage(kocsiKepDetail, kx, ky, kepSzelesseg, kepMagassag, null);
        g.drawImage(kocsiMintazat, kx, ky, kepSzelesseg, kepMagassag, null);

        int hatsoKerekPozicioX;
        int hatsoKerekPozocioY;
        int elsoKerekPozicioX;
        int elsoKerekPozicioY;
        hatsoKerekPozicioX = kx + hatsoTengelyX + hatsoAgyX;
        hatsoKerekPozocioY = ky + hatsoTengelyY + hatsoAgyY;
        elsoKerekPozicioX = kx + elsoTengelyX + elsoAgyX;
        elsoKerekPozicioY = ky + elsoTengelyY + elsoAgyY;
        AffineTransform hatsoKerek = AffineTransform.getTranslateInstance(hatsoKerekPozicioX, hatsoKerekPozocioY);
        AffineTransform elsoKerek = AffineTransform.getTranslateInstance(elsoKerekPozicioX, elsoKerekPozicioY);
        hatsoKerek.rotate(Math.toRadians(szog * 5), kocsiKerek.getWidth(null) / 2, kocsiKerek.getHeight(null) / 2);
        elsoKerek.rotate(Math.toRadians(szog * 5), kocsiKerek.getWidth(null) / 2, kocsiKerek.getHeight(null) / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(kocsiKerek, hatsoKerek, null);
        g2d.drawImage(kocsiKerek, elsoKerek, null);
    }

    /**
     * a színből és az autó képeiből "kivágja" azt a formát amit játék során
     * látunk
     *
     * @param image
     * @param color
     * @return
     */
    private static BufferedImage dye(Image image, Color color) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return dyed;
    }

    public void koordinataBeallitasJatekhoz(int kx, int ky) {
        this.kx = kx;
        this.ky = ky;
    }

    public void setMegadottSzin(Color megadottSzin) {
        this.megadottSzin = megadottSzin;
    }

    public double getActualRev() {
        return actualRev;
    }

    public boolean isMouseDown() {
        return mouseDown;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public int getMinRev() {
        return minRev;
    }

    public int getKx() {
        return kx;
    }

    public int getKy() {
        return ky;
    }

    public int getKepSzelesseg() {
        return kepSzelesseg;
    }

    public int getKepMagassag() {
        return kepMagassag;
    }

    public Image getKocsiMintazat() {
        return kocsiMintazat;
    }

    public Image getKocsiKepDetail() {
        return kocsiKepDetail;
    }

    public Image getKocsiFek() {
        return kocsiFek;
    }

    public Image getKocsiKerek() {
        return kocsiKerek;
    }

    public int getUltetes() {
        return ultetes;
    }

    public int getHatsoLokharitoTavolsag() {
        return hatsoLokharitoTavolsag;
    }

    public int getKardanTengelyTavolsag() {
        return kardanTengelyTavolsag;
    }

    public int getHatsoTengelyX() {
        return hatsoTengelyX;
    }

    public int getHatsoTengelyY() {
        return hatsoTengelyY;
    }

    public int getElsoTengelyX() {
        return elsoTengelyX;
    }

    public int getElsoTengelyY() {
        return elsoTengelyY;
    }

    public int getHatsoAgyX() {
        return hatsoAgyX;
    }

    public int getHatsoAgyY() {
        return hatsoAgyY;
    }

    public int getElsoAgyX() {
        return elsoAgyX;
    }

    public int getElsoAgyY() {
        return elsoAgyY;
    }

    public Color getMegadottSzin() {
        return megadottSzin;
    }

    public double getSzog() {
        return szog;
    }

    public String getMarka() {
        return marka;
    }

    public String getTipus() {
        return tipus;
    }

    public int getVetelAr() {
        return vetelAr;
    }

    public int getLoero() {
        return loero;
    }

    public int getTomeg() {
        return tomeg;
    }

    public int getSzint() {
        return szint;
    }

    public int getMeghajtas() {
        return meghajtas;
    }

    public int getSebessegfokozatok() {
        return sebessegfokozatok;
    }

    public int getKobcenti() {
        return kobcenti;
    }

    public int getMaximumFordulatszam() {
        return maximumFordulatszam;
    }

    public String getSzinKod() {
        return szinKod;
    }

    public int getAutoId() {
        return autoId;
    }

    public Image getLogo() {
        return logo;
    }

    public int getLogoKX() {
        return logoKX;
    }

    public int getLogoKY() {
        return logoKY;
    }

    public void setSzinKod(String szinKod) {
        this.szinKod = szinKod;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public double getActualSpeed() {
        return actualSpeed;
    }

    public double getAddrev() {
        return addrev;
    }

    public int getDx() {
        return dx;
    }

    public float getMeretArany() {
        return meretArany;
    }

    public int getActualGear() {
        return actualGear;
    }

    public String getKocsiMintazatString() {
        return kocsiMintazatString;
    }

    public String getKocsiKepDetailString() {
        return kocsiKepDetailString;
    }

    public String getKocsiFekString() {
        return kocsiFekString;
    }

    public String getKocsiKerekString() {
        return kocsiKerekString;
    }

    public String getLogoString() {
        return logoString;
    }

    public void setActualSpeed(double actualSpeed) {
        this.actualSpeed = actualSpeed;
    }

}
