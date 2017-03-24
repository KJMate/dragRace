package alaposztalyok;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import vezerlo.Vezerlo;

/**
 *
 * @author Trajer
 */
public class FordulatSzamMutato extends Thread {

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;
    private Vezerlo vezerlo;

    private int mutatoKx;
    private int mutatoKy; //mutatóhoz
    private int kepSzelesseg;
    private int kepMagassag;
    private int maxFordulatszam;
    private double szog;
    private long ido;
    private Image mutatoKep = new ImageIcon(this.getClass().getResource("/palyaKepek/arrow.png")).getImage();

    private int maxRev;
    private int minRev;
    private double actualRev;
    private double valosFordulatszam;

    public FordulatSzamMutato(Vezerlo vezerlo, int mutatoKx, int mutatoKy, double szog, long ido, int maxRev, int minRev) {
        this.vezerlo = vezerlo;
        this.mutatoKx = mutatoKx;
        this.mutatoKy = mutatoKy;
        this.szog = szog;
        this.ido = ido;
        this.maxRev = maxRev;
        this.minRev = minRev;
    }

    public void revving(double rev) {

        if (rev > 0) {
            actualRev += rev;
            valosFordulatszam = actualRev;
            szog = actualRev;
            if (actualRev >= maxRev) {
                actualRev = maxRev;
                szog = actualRev;
                valosFordulatszam = actualRev;
            }
        } else {
            actualRev += rev;
            szog = actualRev;
            valosFordulatszam = actualRev;
            if (actualRev <= minRev) {
                actualRev = minRev;
                szog = actualRev;
                valosFordulatszam = actualRev;
            }
        }

    }

    public void valtas(double levon) {
        actualRev -= levon;
        valosFordulatszam -= levon;
    }

    public Image getMutatoKep() {
        return mutatoKep;
    }

    public void setMaxFordulatszam(int maxFordulatszam) {
        this.maxFordulatszam = maxFordulatszam;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }

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

    public double getValosFordulatszam() {
        return valosFordulatszam;
    }

    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        double nobekszik = 0.001;
                        revving(nobekszik);
                        vezerlo.jatekPanelFrissit();
                    } while (mouseDown);
                    isRunning = false;
                    while (!mouseDown) {
                        double nobekszik = -0.001;
                        revving(nobekszik);
                        vezerlo.jatekPanelFrissit();
                    }
                    isRunning = false;
                }
            }.start();
        }
    }

    public void rajzol(Graphics g) {
        AffineTransform at = AffineTransform.getTranslateInstance(mutatoKx, mutatoKy);
        at.rotate(Math.toRadians(szog / 50), mutatoKep.getWidth(null) - 14, mutatoKep.getHeight(null) / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mutatoKep, at, null);
    }
}
