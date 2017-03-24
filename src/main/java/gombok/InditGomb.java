/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gombok;

import alaposztalyok.Zene;
import felulet.JatekFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author kunec
 */
public class InditGomb extends JButton implements MouseListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -8221525621289615531L;
    private int szelesseg = 65;
    private int magassag = 20;
    private String hangEleres = "/hangok/click.wav";
    private Zene zene;
    private Image gombKep = new ImageIcon(this.getClass().getResource("/kepek/gomb_sima.jpg")).getImage();
    private Image szovegKep = new ImageIcon(this.getClass().getResource("/kepek/inditas.png")).getImage();

    public InditGomb() {
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        JatekFrame akvariumFrame = new JatekFrame();
        akvariumFrame.setVisible(true);
        // lekéri azt a frame-t, amelyre felraktuk ezt a panelt
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        frame.setVisible(false);
    }

}
