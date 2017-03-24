/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gombok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Crema
 */
public class GazPedal extends JButton implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = -1369478227918633372L;
    private int szelesseg = 58;
    private int magassag = 133;
    private Image gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/powerAlap.png")).getImage();

    public GazPedal() {
        this.setSize(szelesseg, magassag);
        this.setVisible(true);
        this.setEnabled(true);
        this.setBackground(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gombKep, 0, 0, gombKep.getWidth(null), gombKep.getHeight(null), null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //
    }

    @Override
    public void mousePressed(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/powerNyomva.png")).getImage();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/powerFolotte.png")).getImage();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/powerFolotte.png")).getImage();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/powerAlap.png")).getImage();
    }

}
