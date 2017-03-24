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
public class ElozoGomb extends JButton implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 7130895031976081181L;
    private int szelesseg = 50;
    private int magassag = 50;
    private String hangEleres = "/hangok/click.wav";
    private Zene zene;
    private Image gombKep = new ImageIcon(this.getClass().getResource("/kepek/nyil_bal.png")).getImage();

    public ElozoGomb() {
        this.setSize(szelesseg, magassag);
        this.setVisible(false);
        this.setEnabled(true);
        this.setBackground(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        zene = new Zene(hangEleres);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gombKep, 0, 0, this.getWidth(), this.getHeight(), null);
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
        gombKep = new ImageIcon(this.getClass().getResource("/kepek/nyil_nyomva_bal.png")).getImage();
        zene.play();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/kepek/nyil_bal.png")).getImage();
        zene.stop();
    }

}
