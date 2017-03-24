/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gombok;

import alaposztalyok.Zene;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author kunec
 */
public class SugoGomb extends JButton implements MouseListener {

    private int szelesseg = 50;
    private int magassag = 20;
    private String hangEleres = "/hangok/click.wav";
    private Zene zene;
    private Image gombKep = new ImageIcon(this.getClass().getResource("/kepek/gomb_sima.jpg")).getImage();
    private Image szovegKep = new ImageIcon(this.getClass().getResource("/kepek/sugo.png")).getImage();

    public SugoGomb() {
        this.setSize(szelesseg, magassag);
        this.setVisible(true);
        this.setEnabled(true);
        zene = new Zene(hangEleres);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gombKep, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(szovegKep, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //
    }

    @Override
    public void mousePressed(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/kepek/gomb_1.jpg")).getImage();
        zene.play();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/kepek/gomb_sima.jpg")).getImage();
        zene.stop();
    }
}
